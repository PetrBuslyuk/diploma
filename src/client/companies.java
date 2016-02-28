package client;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class companies extends javax.swing.JFrame {
private static clientProfile c1;
private static ArrayList<company> companys=new ArrayList<>();
private static File file= new File(get_current_dir()+"companies.xml");
private final static String version = "1.0";
    private static companies comp;
   private static selectedCompany sc;

    public companies() throws Exception {
        initComponents(); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ct = new javax.swing.JTable();
        menu = new javax.swing.JMenuBar();
        companies = new javax.swing.JMenu();
        calculate = new javax.swing.JMenuItem();
        select_all_companies = new javax.swing.JMenuItem();
        separator = new javax.swing.JPopupMenu.Separator();
        save_companies = new javax.swing.JMenuItem();
        add_company = new javax.swing.JMenuItem();
        delete_company = new javax.swing.JMenuItem();
        selectCompany = new javax.swing.JMenuItem();
        profile = new javax.swing.JMenu();
        programm = new javax.swing.JMenu();
        about_programm = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Компании");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeWindow(evt);
            }
        });

        ct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Название", "Депозит", "Средний %", "Период инвестирования"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ctMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ct);
        if (ct.getColumnModel().getColumnCount() > 0) {
            ct.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        companies.setText("Компании");
        companies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                companiesMouseClicked(evt);
            }
        });

        calculate.setText("Расчитать данные");
        calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateActionPerformed(evt);
            }
        });
        companies.add(calculate);

        select_all_companies.setText("Выбрать все компании");
        select_all_companies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_all_companiesActionPerformed(evt);
            }
        });
        companies.add(select_all_companies);
        companies.add(separator);

        save_companies.setText("Сохранить компании");
        save_companies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_companiesActionPerformed(evt);
            }
        });
        companies.add(save_companies);

        add_company.setText("Добавить компанию");
        add_company.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_companyActionPerformed(evt);
            }
        });
        companies.add(add_company);

        delete_company.setText("Удалить компанию");
        delete_company.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_companyActionPerformed(evt);
            }
        });
        companies.add(delete_company);

        selectCompany.setText("Выбрать компанию");
        selectCompany.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectCompanyMouseClicked(evt);
            }
        });
        companies.add(selectCompany);

        menu.add(companies);

        profile.setText("Профиль");
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        menu.add(profile);

        programm.setText("Программа");

        about_programm.setText("О программе");
        about_programm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                about_programmActionPerformed(evt);
            }
        });
        programm.add(about_programm);

        menu.add(programm);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void select_all_companiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_all_companiesActionPerformed
        if(ct.getRowCount()==0) JOptionPane.showMessageDialog(null, "Добавьте компанию");
        else ct.selectAll();
    }//GEN-LAST:event_select_all_companiesActionPerformed

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked
        c1.setVisible(true);
    }//GEN-LAST:event_profileMouseClicked

    private void calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateActionPerformed
       if(ct.getSelectedRowCount()==0) JOptionPane.showMessageDialog(null, "Выберите компании");
       else{
       String companies_table="";
       double summary=0;double deposit=0;
       int[] sr = ct.getSelectedRows();
        for(int i=0;i<sr.length;i++){
           companies_table +=ct.getValueAt(sr[i], 0).toString()+"\n";
           deposit+=Double.parseDouble(ct.getValueAt(sr[i], 1).toString());
           summary += Double.parseDouble(ct.getValueAt(sr[i], 1).toString())
                   * (Double.parseDouble(ct.getValueAt(sr[i], 2).toString())/100)
                   * Double.parseDouble(ct.getValueAt(sr[i], 3).toString());
       }
       double all=deposit+summary;
       JOptionPane.showMessageDialog(null, "Были выбраны следующие компании:\n"
           +companies_table+"Перспективная прибыль составит: " + 
               BigDecimal.valueOf(summary).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue()+
               "\nПри первоначальном взносе:" + deposit +
               "\nКонечная перспективная прибыль: "+
               BigDecimal.valueOf(all).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue());
       }
    }//GEN-LAST:event_calculateActionPerformed

    private void add_companyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_companyActionPerformed
        DefaultTableModel model = (DefaultTableModel)ct.getModel();
        String name = JOptionPane.showInputDialog("Введите название");
        String depo = JOptionPane.showInputDialog("Введите депозит");
        String persent = JOptionPane.showInputDialog("Введите средний процент");
        String period = JOptionPane.showInputDialog("Введите длительность инвестирования");
        company c = new company(name,depo,persent,period);
        companys.add(c);
        model.addRow(c.get_company_to_object());
        ct.setModel(model);
    }//GEN-LAST:event_add_companyActionPerformed

    private void delete_companyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_companyActionPerformed
        deleteCompanies();
    }//GEN-LAST:event_delete_companyActionPerformed

    private void closeWindow(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeWindow
    try {c1.close_main_window();} catch (IOException ex) {} 
    }//GEN-LAST:event_closeWindow

    private void save_companiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_companiesActionPerformed
       StringBuilder str = new StringBuilder();
       str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
       .append("<companies>\n");
       companys.stream().forEach((c2)->{str.append(c2.get_company_to_string());});
       str.append("</companies>");
       clientProfile.write(file,str.toString());
    }//GEN-LAST:event_save_companiesActionPerformed

    private void about_programmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_about_programmActionPerformed
        JOptionPane.showMessageDialog(null, "Программа для расчета перспективной"
            + " прибыли\n от инвестиционной деятельности.");
    }//GEN-LAST:event_about_programmActionPerformed

    private void companiesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_companiesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_companiesMouseClicked

    private void selectCompanyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectCompanyMouseClicked
      showSelectedCompany();
    }//GEN-LAST:event_selectCompanyMouseClicked

    private void ctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctMouseClicked
       if (evt.getClickCount() == 2) {
           sc.setVisible(true);
        } 
    }//GEN-LAST:event_ctMouseClicked
   
    protected static void deleteCompanies(){
    if(ct.getSelectedRowCount()>0){
            DefaultTableModel model = (DefaultTableModel)ct.getModel();
            int[] row = ct.getSelectedRows();
            for(int row1 : row){
                companys.remove(row1);
                model.removeRow(row1);
            }
            ct.setModel(model);
        }
    }
    protected static String get_current_dir(){
        String path = companies.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	return path.substring(1,path.length());
     }
    private static void get_companies() throws ParserConfigurationException, SAXException, IOException{
        Node doc = DocumentBuilderFactory.newInstance()
            .newDocumentBuilder().parse(file);
        String name,depo,persent,period;
        Node n = doc.getFirstChild();
        System.out.println("1");
        if(n.getNodeName().equals("companies")){
            while(n!=null){
            Node m = n.getFirstChild();
                while(m != null){ 
                    if(m.getNodeName().equals("company")) {
                        name=depo=persent=period="";
                        Node comp=m.getFirstChild();    
                        while(comp!=null){
                            switch (comp.getNodeName()){
                            case "name":name=comp.getTextContent().trim();break;
                            case "depo":depo=comp.getTextContent().trim();break;
                            case "persent":persent=comp.getTextContent().trim();break;
                            case "period":period=comp.getTextContent().trim();break; 
                            default:break;
                            }                     
                          comp=comp.getNextSibling(); 
                        }
                        company c = new company(name,depo,persent,period);
                        companys.add(c); 
                    }
                    m = m.getNextSibling();
                }
               n = n.getNextSibling();
            }
        }
        show_companies();
    }
    protected static void show_companies(){
        for(int i=0;i<companys.size();i++){
            System.out.println(companys.get(i).get_name());
            ((DefaultTableModel)ct.getModel()).addRow(
                new Object[]{companys.get(i).get_name(), 
                    companys.get(i).get_depo(),
                    companys.get(i).get_persent(),
                    companys.get(i).get_period()
                });}
        System.out.println("8");
    }    
    void showSelectedCompany(){
        System.out.println(ct.getSelectedRow());
    }
    public static void main(String args[]) throws Exception {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(companies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(companies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(companies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(companies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                   sc = new selectedCompany();
                   sc.setVisible(false);
                   comp = new companies();
                   comp.setVisible(true);
                } catch (Exception ex) {} 
            }
        });
        c1 = new clientProfile();
        c1.set_comp(comp);
        System.out.println("2");
        c1.setVisible(false);
        get_companies();
        System.out.println("7");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about_programm;
    private javax.swing.JMenuItem add_company;
    private javax.swing.JMenuItem calculate;
    private javax.swing.JMenu companies;
    private static javax.swing.JTable ct;
    private javax.swing.JMenuItem delete_company;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu profile;
    private javax.swing.JMenu programm;
    private javax.swing.JMenuItem save_companies;
    private javax.swing.JMenuItem selectCompany;
    private javax.swing.JMenuItem select_all_companies;
    private javax.swing.JPopupMenu.Separator separator;
    // End of variables declaration//GEN-END:variables
}
