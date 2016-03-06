package client;

import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class clientProfile extends javax.swing.JFrame {
    private boolean connected;
    private final client c;
    private  companies comp;
    private  File profile= new File(get_current_dir()+"profile.txt");
    private String firstname,secondname,telephone,email;
    String get_current_dir(){
        String path = clientProfile.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	return path.substring(1,path.length());
    }
    public clientProfile() throws Exception {
        super("ClientProfile");
        initComponents();
        System.out.println("5");
        if(profile.exists()){
            BufferedReader in = new BufferedReader(new FileReader(profile));
            String [] mas = in.readLine().split(";");
            fn.setText(mas[0]);
            sn.setText(mas[1]);
            tn.setText(mas[2]);
            em.setText(mas[3]);
            fill_client_all(mas[0], mas[1], mas[2], mas[3]);
        } else  check_and_send();
        System.out.println("6");
        c = new client("localhost", 7777, this);
        //c.set_comp(comp);
        c.start();
    }
    protected void fill_client_all(String firstname,String secondname,
            String telephone,String email) throws Exception{
            this.firstname=firstname;
            this.secondname=secondname;
            this.telephone = telephone;
            this.email = email;
    }
    
    protected boolean check(JTextField tf,String tfn){
        if(tf.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Заполните поле "+tfn);
            return false;
        }else{return true;}
    }
    protected void check_and_send(){
        if(check(fn,"имени") && check(sn,"фамилии") && check(tn,"телефона") && check(em,"емейла")){
            try {
                connected=true;
                String str=fn.getText()+";"+sn.getText()+";"+tn.getText()+";"+em.getText();
                profile.createNewFile();
                write(profile,str);
            } catch (IOException ex) {}
        }else  connected=false;
    }
    protected static void write(File file, String text) {
    try {
        if(!file.exists()){file.createNewFile();}
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        try {out.print(text);
        } finally {out.close();}
        } catch(IOException e){throw new RuntimeException(e);}
    }
    protected String get_firstname() {return this.firstname;}
    protected String get_secondname() {return this.secondname;}
    protected String get_telephone() {return this.telephone;}
    protected String get_email() {return this.email;}
    void append(String string) {JOptionPane.showMessageDialog(null, string);}
    void connectionFailed(){connected = false; append("Соединение с сервером закрыто.");}
    void close_main_window() throws IOException {c.close_main_window();}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        save_client = new javax.swing.JButton();
        fn = new javax.swing.JTextField();
        sn = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        em = new javax.swing.JTextField();

        setTitle("Профиль клиента");
        setResizable(false);

        save_client.setText("Сохранить");
        save_client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_clientActionPerformed(evt);
            }
        });

        jLabel1.setText("Имя");

        jLabel2.setText("Фамилия");

        jLabel4.setText("Телефон");

        jLabel7.setText("Е-мейл");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(save_client)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addComponent(jLabel4))
                            .addComponent(jLabel7))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sn)
                            .addComponent(fn)
                            .addComponent(tn)
                            .addComponent(em, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                        .addGap(3, 3, 3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(save_client)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void save_clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_clientActionPerformed
        check_and_send();
        this.setVisible(false);
    }//GEN-LAST:event_save_clientActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField em;
    private javax.swing.JTextField fn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton save_client;
    private javax.swing.JTextField sn;
    private javax.swing.JTextField tn;
    // End of variables declaration//GEN-END:variables

    void set_comp(companies comp) {
        this.comp = comp;
    }
}
