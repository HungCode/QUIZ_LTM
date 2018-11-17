package clientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hung
 */
public class ChatToServer {

    public static void main(String[] args) throws IOException {
        ChatToServer c = new ChatToServer("localhost", 9821);
    }

    Socket socketClient = null;
    BufferedReader br = null;
    BufferedReader is = null;
    PrintWriter os = null;

    public ChatToServer(String server, int port) throws IOException {
        try {
            socketClient = new Socket(server, port); // You can use static final constant PORT_NUM
            br = new BufferedReader(new InputStreamReader(System.in));
            is = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            os = new PrintWriter(socketClient.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("IO Exception");
        }

        System.out.println("Client Address : " + server);
        System.out.println("Enter Data to echo Server ( Enter QUIT to end):");
    }

    public void sendMessage(String massage) throws IOException {
        os.println(massage);
        os.flush();

        /*
        try {
//            massage = br.readLine();
            while (massage.compareTo("QUIT") != 0) {
                os.println(massage);
                os.flush();
                response = is.readLine();
                System.out.println("Server Response : " + response);
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Socket read Error");
        } finally {

            is.close();
            os.close();
            br.close();
            socketClient.close();
            System.out.println("Connection Closed");
        }
         */
    }

    public String getMassage() throws IOException {
        return is.readLine();
    }

    public void closeIO() throws IOException {
        is.close();
        os.close();
        br.close();
        socketClient.close();
        System.out.println("Connection Closed");
    }

}
