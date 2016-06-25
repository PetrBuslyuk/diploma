package server;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ServerForm extends javax.swing.JFrame {
    public static Database db;
    private static final int port = 7777;
    private static Server serv;
    private static ListOfUsers lu;
    public ServerForm(){
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        start = new javax.swing.JButton();
        stop = new javax.swing.JButton();
        clientList = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Сервер");

        log.setColumns(20);
        log.setRows(5);
        jScrollPane1.setViewportView(log);

        start.setText("Старт");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        stop.setText("Стоп");
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });

        clientList.setText("Пользователи");
        clientList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clientList, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(start)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientList)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        new ServerRunning().start();
    }//GEN-LAST:event_startActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        try {
            serv.stop();
        } catch (IOException ex) {}
    }//GEN-LAST:event_stopActionPerformed

    private void clientListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientListActionPerformed
        lu.setVisible(true);
        lu.refresh_list();
    }//GEN-LAST:event_clientListActionPerformed
    
    class ServerRunning extends Thread {
        public void run() {
            try {serv.start();
            } catch (Exception ex) {}
        }
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ServerForm s = new ServerForm();
                s.setVisible(true);
                serv = new Server(port, s);
                try{
                    db = new Database();
                    lu=new ListOfUsers(db);
                    serv.set_list_user(lu);
                    lu.setVisible(false);
                } catch (ParserConfigurationException | SAXException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Вы уже открыли один сервер. Работайте с ним.");
                    System.exit(0);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    JOptionPane.showMessageDialog(null, "Нельзя создать второе подключение к БД.\n"
                            + "Это ограничено функционалом H2.\n"
                            + "Закройте сервис Н2 или не запускайте второе приложение сервера.");
                    System.exit(0);
                }
            }
        });
        
    }
    void appendEvent(String string) {
       log.append(string);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clientList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea log;
    private javax.swing.JButton start;
    private javax.swing.JButton stop;
    // End of variables declaration//GEN-END:variables

    
}
