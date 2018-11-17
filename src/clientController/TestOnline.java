package clientController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author hung
 */
public class TestOnline extends javax.swing.JFrame {

    ChatToServer chatToServer;

    public TestOnline(ChatToServer chat) {
        initComponents();
        this.chatToServer = chat;
        showListUserOnline(chat);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelListUserInfor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListShow = new javax.swing.JList<>();
        jButtonUser1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DANH SÁCH NGƯỜI CHƠI ONLINE");

        jButton1.setText("CẬP NHẬT");

        jScrollPane1.setViewportView(jListShow);

        jButtonUser1.setText("user");

        javax.swing.GroupLayout jPanelListUserInforLayout = new javax.swing.GroupLayout(jPanelListUserInfor);
        jPanelListUserInfor.setLayout(jPanelListUserInforLayout);
        jPanelListUserInforLayout.setHorizontalGroup(
            jPanelListUserInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListUserInforLayout.createSequentialGroup()
                .addGroup(jPanelListUserInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelListUserInforLayout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jButton1))
                    .addGroup(jPanelListUserInforLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButtonUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(jPanelListUserInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap(256, Short.MAX_VALUE))
        );
        jPanelListUserInforLayout.setVerticalGroup(
            jPanelListUserInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListUserInforLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelListUserInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(jPanelListUserInforLayout.createSequentialGroup()
                        .addComponent(jButtonUser1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
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

    public void showListUserOnline(ChatToServer chat) {
        try {
            chatToServer.sendMessage("/listUserOnline");
            String listUO = chatToServer.getMassage();
            JOptionPane.showMessageDialog(rootPane, listUO);
            jButtonUser1.setText(listUO);
            
            String[] users = listUO.split(";");
            HashMap<String, String> hm = new HashMap<>();
            for (int i = 0; i < users.length; i++) {
                String[] userinfor = users[i].split("-");
                hm.put(userinfor[0], userinfor[1]);
            }
            Set<String> setHm = hm.keySet();
            final DefaultListModel name = new DefaultListModel();
            for (String s : setHm) {
                System.out.println(s);
                jButtonUser1.setText(s);
                name.addElement("username: " + s + "\tmax point: " + hm.get(s));
            }
            System.out.println("running...");
            name.addElement("demo");
            jListShow.setModel(name);
            jListShow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jListShow.setSelectedIndex(0);
            jListShow.setVisibleRowCount(3);

            JScrollPane listScrollPane = new JScrollPane(jListShow);
            jPanelListUserInfor.add(listScrollPane);
//            JOptionPane.showMessageDialog(rootPane, setHm.size()+"");
        } catch (Exception e) {
        }

    }
    
    public static void main(String[] args) {
                java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChatToServer chat;
                try {
                    chat = new ChatToServer("localhost", 9821);
                    TestOnline client = new TestOnline(chat);
                    client.setTitle("QUIZ ONLINE");
                    client.setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonUser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListShow;
    private javax.swing.JPanel jPanelListUserInfor;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
