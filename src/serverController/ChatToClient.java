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
import static serverController.Server.listClientSocket;

/**
 *
 * @author hung
 */
public class ChatToClient extends Thread {

    ArrayList<User> listUser = DataCommunications.getListUSer();

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
                        massage = is.readLine();
                        System.out.println("Login");
                        //kiem tra dang nhap
                        String[] ms = massage.split("/");
                        User user = DataCommunications.getUser(ms[0]);
                        if (DataCommunications.checkUSer(ms[0], ms[1]) && !Server.listClientSocket.containsKey(ms[0])) {
                            System.out.println("ok");
                            Server.listClientSocket.put(ms[0], this);
                            os.println("accept");
                            os.flush();
                        } else {
                            os.println("invalid");
                            os.flush();
                        }

                    case "/listUserOnline":
                        //---------------Kiem tra trang thai socket--------------//

                        listClientSocket.forEach((key, value) -> {
                            try {
                                PrintWriter out = new PrintWriter(value.clientSocket.getOutputStream(), true);
                                if (out.checkError()) {
                                    listClientSocket.remove(key, value);
                                }
                                hsUser.add(key);
                            } catch (IOException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        listUserOnline = Server.listClientSocket.size() + "";
//                        Server.listClientSocket.forEach((useronline, chatClient) -> {
//                            listUserOnline += ";" + useronline.getAllInfor();
//                        });
                        for(String u : hsUser){
                            listUserOnline += ";" + u;
                        }

                        os.println(listUserOnline);
                        os.flush();
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
}
