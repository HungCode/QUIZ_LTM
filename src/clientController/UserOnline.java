package clientController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author hung
 */
public class UserOnline extends javax.swing.JFrame {

    ChatToServer chatToServer;
    Thread chatToOtherClient;

    public UserOnline(ChatToServer chat) {
        initComponents();
        this.chatToServer = chat;
        showListUserOnline(chat);
        receiveMassage();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelListUserInfor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelUser = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListUser = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DANH SÁCH NGƯỜI CHƠI ONLINE");

        jListUser.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jListUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListUserMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListUser);

        javax.swing.GroupLayout jPanelUserLayout = new javax.swing.GroupLayout(jPanelUser);
        jPanelUser.setLayout(jPanelUserLayout);
        jPanelUserLayout.setHorizontalGroup(
            jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelUserLayout.setVerticalGroup(
            jPanelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelListUserInforLayout = new javax.swing.GroupLayout(jPanelListUserInfor);
        jPanelListUserInfor.setLayout(jPanelListUserInforLayout);
        jPanelListUserInforLayout.setHorizontalGroup(
            jPanelListUserInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListUserInforLayout.createSequentialGroup()
                .addComponent(jPanelUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(jLabel1)
                .addContainerGap(243, Short.MAX_VALUE))
        );
        jPanelListUserInforLayout.setVerticalGroup(
            jPanelListUserInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListUserInforLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanelUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelListUserInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelListUserInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jListUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListUserMouseClicked
//        JOptionPane.showMessageDialog(rootPane, "A day roi");
//        int selected = -1;
//        selected = jListUser.getSelectedIndex();
//        String username = jListUser.getSelectedValue();
//        try {
//            chatToServer.sendMessage("/requestOtherPlay");
//            chatToServer.sendMessage(username);
//        } catch (IOException ex) {
//            Logger.getLogger(UserOnline.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jListUserMouseClicked

    public void receiveMassage() {
        chatToOtherClient = new Thread(new Runnable() {
            @Override
            public void run() {
                String message = null;
                try {
//                    chatToServer.sendMessage("/listUserOnline");
                    long time = 0;
                    while (true) {

                        message = chatToServer.getMassage();
                        switch (message) {
                            case "/play":
                                JOptionPane.showMessageDialog(rootPane, "A day roi");
                            case "/receiveOtherPlay":
                                String anotherUser = chatToServer.getMassage();
                                JOptionPane.showMessageDialog(rootPane, "Invited to Play "+anotherUser);
                                
                            case "/request":
                            //chatToServer.sendMessage("/accept");
                            case "/InvitedToPlay":
                                JOptionPane.showMessageDialog(rootPane, "Invited to Play");
                            case "/listUserOnline":
                                
                                listUO = chatToServer.getMassage();
                                String[] users = listUO.split(";");

                                DefaultListModel username = new DefaultListModel();
                                for (int i = 0; i < users.length; i++) {
                                    username.addElement(users[i]);

                                }
                                jListUser.setModel(username);
//                                time = System.currentTimeMillis();
//                                JOptionPane.showMessageDialog(rootPane,time+"");

                               
                        }
                        
//                        if((time+3000) == System.currentTimeMillis())
//                            chatToServer.sendMessage("/listUserOnline");
                    }
                } catch (Exception e) {
                }
            }
        });
        chatToOtherClient.start();
    }

    public void showListUserOnline(ChatToServer chat) {
        try {
            Thread getListUSer = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            chatToServer.sendMessage("/listUserOnline");
//                            listUO = chatToServer.getMassage();
//                            String[] users = listUO.split(";");
//
//                            DefaultListModel username = new DefaultListModel();
//                            for (int i = 0; i < users.length; i++) {
//                                username.addElement(users[i]);
//
//                            }
//                            jListUser.setModel(username);

                            Thread.sleep(3000);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(UserOnline.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UserOnline.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            getListUSer.start();
            this.add(jPanelUser);
        } catch (Exception e) {
        }

    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChatToServer chat;
                try {
                    chat = new ChatToServer("localhost", 9821);
                    UserOnline client = new UserOnline(chat);
                    client.setTitle("QUIZ ONLINE");
                    client.setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    String listUO;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListUser;
    private javax.swing.JPanel jPanelListUserInfor;
    private javax.swing.JPanel jPanelUser;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
