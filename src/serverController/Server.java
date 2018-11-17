package serverController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author hung
 */
public class Server {

    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    public static HashMap<String, ChatToClient> listClientSocket = new HashMap<>();

    public static void main(String args[]) throws InterruptedException {
        int portNumber = 9821;
        //Khai bao
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("connection Established");
                ChatToClient clientThread = new ChatToClient(clientSocket);
                Thread.sleep(3000);
                clientThread.start();

                

            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }
}
