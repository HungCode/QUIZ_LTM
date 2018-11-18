package serverController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DataCommunications;
import models.User;
import static serverController.Server.threads;

/**
 *
 * @author hung
 */
public class ChatToClient extends Thread {

    ArrayList<User> listUser = DataCommunications.getListUSer();

    String username;
    String listUserOnline = null;
    String massage = null;
    BufferedReader is = null;
    PrintWriter os = null;
    public Socket clientSocket = null;
    //
    HashSet<String> hsUser = new HashSet<>();

    public ChatToClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintWriter(clientSocket.getOutputStream());

        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }

        try {
            while (true) {
                massage = is.readLine();
                switch (massage) {
                    case "/login":
                        login();
                    case "/listUserOnline":
                        userOnline();

                    case "/requestOtherPlay":
                        invitePlay();
                    case "/response":
//                        invitePlay();

                }
            }
        } catch (IOException e) {
            massage = this.getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client " + massage + " terminated abruptly");
        } catch (NullPointerException e) {
            massage = this.getName(); //reused String line for getting thread name
            System.out.println("Client " + massage + " Closed");
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (is != null) {
                    is.close();
                    System.out.println(" Socket Input Stream Closed");
                }

                if (os != null) {
                    os.close();
                    System.out.println("Socket Out Closed");
                }
                if (clientSocket != null) {
                    clientSocket.close();
                    System.out.println("Socket Closed");
                }
            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }

    public void sendMessage(String ms) {
        os.println("ms");
        os.flush();
    }

    public String getMessage() throws IOException {
        return is.readLine();
    }

    public void login() throws IOException {
        massage = is.readLine();
        System.out.println("Login");
        //kiem tra dang nhap
        String[] ms = massage.split("/");
        User user = DataCommunications.getUser(ms[0]);
        if (DataCommunications.checkUSer(ms[0], ms[1]) && !Server.threads.containsKey(ms[0])) {
            System.out.println("ok");
            Server.threads.put(ms[0], this);
            username = ms[0];
            os.println("accept");
            os.flush();
        } else {
            os.println("invalid");
            os.flush();
        }
    }

    public void userOnline() {
        os.println("/listUserOnline");
        os.flush();
        //---------------Kiem tra trang thai socket--------------//

        threads.forEach((key, value) -> {
            try {
                PrintWriter out = new PrintWriter(value.clientSocket.getOutputStream(), true);
                if (out.checkError()) {
                    threads.remove(key, value);
                }
                hsUser.add(key);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        listUserOnline = "";
        for (String u : hsUser) {
            listUserOnline += ";" + u;
        }
        os.println(listUserOnline);
        os.flush();
    }

    public void invitePlay() throws IOException {
        //client1 gui tin den server
        massage = is.readLine();
        massage = is.readLine();
        System.out.println("Nhan duoc message tu " + username + "  :" + massage);
        ChatToClient chatToClient2 = threads.get(massage);
        chatToClient2.sendMessage("/receiveOtherPlay");
        chatToClient2.sendMessage(username);
//        Room r = new Room(this,chatToClient2);
//        r.start();
    }

}
