/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverController;

/**
 *
 * @author hung
 */
public class Room extends Thread {

    ChatToClient serverToUser1, serverToUser2;

    public Room() {
    }

    public Room(ChatToClient user1, ChatToClient user2) {
        this.serverToUser1 = user1;
        this.serverToUser2 = user2;
    }

    @Override
    public void run() {
        System.out.println("gui tin nhan den "+serverToUser2.username);
        serverToUser2.sendMessage("/InvitedToPlay");
        serverToUser2.sendMessage(serverToUser1.username);

    }

}
