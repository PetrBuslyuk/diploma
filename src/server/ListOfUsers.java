package server;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ListOfUsers extends javax.swing.JFrame {

    private ArrayList<User> buf_u = new ArrayList<>();
    private final Database db;

    public ListOfUsers(Database db1) throws ParserConfigurationException, SAXException, IOException, SQLException {
        initComponents();
        this.db = db1;
        getAllUsers();
        refresh_list();
    }

    protected void set_online(String email, boolean flag) {
        buf_u.stream().filter((u) -> (email.equals(u.get_email()))).forEach((u) -> {
            u.set_online(flag);
        });
        refresh_list();
    }

    public final void getAllUsers() throws SQLException {
        //buf_u.clear();
        ResultSet rs = db.getAllUsers();
        while (rs.next()) {
            User c = new User(false, rs.getString("name"),
                    rs.getString("secondname"), rs.getString("tel"), rs.getString("email"));
            buf_u.add(c);
        }
    }

    public final void refresh_list() {
        clear_table(tcl);
        buf_u.stream().forEach((item) -> {
            ((DefaultTableModel) tcl.getModel()).addRow(item.get_obj());
        });
    }

    protected boolean check_user_in_list(String email) {
        for (int i = 0; i < buf_u.size(); i++) {
            if (buf_u.get(i).get_email().equals(email)) {
                return true;
            }
        }
        return false;
    }

    protected void clear_table(JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tcl = new javax.swing.JTable();
        choiseMade = new javax.swing.JComboBox<>();
        made = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(600, 350));

        tcl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Online", "Name", "Secondname", "Telephone", "Email"
            }
        ));
        jScrollPane1.setViewportView(tcl);

        choiseMade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Нет действий", "Обновить список", "Выслать сообщение", "Выделить всех" }));
        choiseMade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choiseMadeActionPerformed(evt);
            }
        });

        made.setText("Выбрать");
        made.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                madeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(choiseMade, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(made, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(choiseMade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(made))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void madeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_madeActionPerformed
        switch (choiseMade.getSelectedItem().toString()) {
            case "Обновить список": {
                refresh_list();
            }; break;
            case "Выслать сообщение": {
                int[] selectedUsers = tcl.getSelectedRows();
                if (selectedUsers.length == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Вы не выбрали клиентов для отправки сообщения");
                } else {
                    boolean inetConn = EmailSending.checkInternetConnection();
                    if (inetConn) {
                        String subject = JOptionPane.showInputDialog("Введите заголовок");
                        if (subject==null){
                            return ;
                        }
                        if (subject.isEmpty()) {
                            JOptionPane.showMessageDialog(rootPane, "Введите заголовок сообщения! Заголовок пуст!");
                            return;
                        }
                        String message = JOptionPane.showInputDialog("Введите сообщение");
                        if (message==null){
                            return ;
                        }
                        if (message.isEmpty()) {
                            JOptionPane.showMessageDialog(rootPane, "Введите сообщение! Сообщение отправляемое клиентам пусто!");
                            return;
                        }
                        EmailSending sending = new EmailSending();
                        for (int i : selectedUsers) {
                            sending.sendEmail(tcl.getValueAt(i, 4).toString(), "skyliner270594@gmail.com", "Petr Bulsyuk", subject, message);
                        }
                        JOptionPane.showMessageDialog(rootPane, "Сообщения успешно отправлены");
                    } else {
                        JOptionPane.showMessageDialog(null, "Интернет соединение отсутствует");
                    }

                }
            }; break;
            case "Выделить всех": {
                tcl.selectAll();
            }; break;
            default:
                break;
        }
    }//GEN-LAST:event_madeActionPerformed

    private void choiseMadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choiseMadeActionPerformed

    }//GEN-LAST:event_choiseMadeActionPerformed

    void add_new_user(User u) {
        try {
            db.insertUser(u);
        } catch (SQLException ex) {
            Logger.getLogger(ListOfUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> choiseMade;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton made;
    private javax.swing.JTable tcl;
    // End of variables declaration//GEN-END:variables
}
