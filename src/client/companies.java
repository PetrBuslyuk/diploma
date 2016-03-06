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

    public companies(){
        initComponents(); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ct = new javax.swing.JTable();
        menu = new javax.swing.JMenuBar();
        companies = new javax.swing.JMenu();
        select_all_companies = new javax.swing.JMenuItem();
        separator = new javax.swing.JPopupMenu.Separator();
        save_companies_action = new javax.swing.JMenuItem();
        add_company = new javax.swing.JMenuItem();
        delete_company = new javax.swing.JMenuItem();
        selectCompany = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        calculate = new javax.swing.JMenuItem();
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

        ct.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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
        companies.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        select_all_companies.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        select_all_companies.setText("Выбрать все компании");
        select_all_companies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_all_companiesActionPerformed(evt);
            }
        });
        companies.add(select_all_companies);
        companies.add(separator);

        save_companies_action.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save_companies_action.setText("Сохранить компании");
        save_companies_action.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_companies_action(evt);
            }
        });
        companies.add(save_companies_action);

        add_company.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        add_company.setText("Добавить компанию");
        add_company.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_companyActionPerformed(evt);
            }
        });
        companies.add(add_company);

        delete_company.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        delete_company.setText("Удалить компанию");
        delete_company.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_companyActionPerformed(evt);
            }
        });
        companies.add(delete_company);

        selectCompany.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        selectCompany.setText("Выбрать компанию");
        selectCompany.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectCompanyMouseClicked(evt);
            }
        });
        companies.add(selectCompany);

        menu.add(companies);

        jMenu1.setText("Действия");

        calculate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        calculate.setText("Расчитать данные");
        calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateActionPerformed(evt);
            }
        });
        jMenu1.add(calculate);

        menu.add(jMenu1);

        profile.setText("Профиль");
        profile.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        menu.add(profile);

        programm.setText("Программа");
        programm.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
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
        company c = new company(name,depo,persent,period,
               new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
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

    private void save_companies_action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_companies_action
        save_companies();
    }//GEN-LAST:event_save_companies_action

    private void about_programmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_about_programmActionPerformed
        JOptionPane.showMessageDialog(null, "Программа для расчета перспективной"
            + " прибыли\n от инвестиционной деятельности.");
    }//GEN-LAST:event_about_programmActionPerformed

    private void selectCompanyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectCompanyMouseClicked
      showSelectedCompany();
    }//GEN-LAST:event_selectCompanyMouseClicked

    private void ctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctMouseClicked
       if (evt.getClickCount() == 2) {
           sc.setVisible(true);
           String selectedCName = ct.getValueAt(ct.getSelectedRow(), 0).toString();
           companys.stream().forEach((company c)->{
               if(c.get_name().equals(selectedCName)){
                   sc.setSelectedCompany(c);
               }
           });
        }
    }//GEN-LAST:event_ctMouseClicked
    
    static void log(Object o){
        System.out.println(o);
    }
    
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
        ArrayList<String> plus,minus,reinvesting;
        Node n = doc.getFirstChild();
        System.out.println("1");
        if(n.getNodeName().equals("companies")){
            while(n!=null){
            Node m = n.getFirstChild();
                while(m != null){ 
                    if(m.getNodeName().equals("company")) {
                        name=depo=persent=period="";
                        plus=new ArrayList<>();
                        minus=new ArrayList<>();
                        reinvesting=new ArrayList<>();
                        Node comp=m.getFirstChild();    
                        while(comp!=null){
                            switch (comp.getNodeName()){
                            case "name":name=comp.getTextContent().trim();break;
                            case "depo":depo=comp.getTextContent().trim();break;
                            case "persent":persent=comp.getTextContent().trim();break;
                            case "period":period=comp.getTextContent().trim();break;
                            case "intervals":{
                                Node i=comp.getFirstChild();
                                   while(i!=null){
                                        if(i.getNodeName().equals("i")){
                                            Node t = i.getFirstChild();
                                            while(t!=null){     
                                                switch (t.getNodeName()){
                                                    case "plus":{
                                                    plus.add(t.getTextContent());
                                                    };break;
                                                    case "minus":{
                                                    minus.add(t.getTextContent());
                                                    };break;
                                                    case "reinvesting":{
                                                    reinvesting.add(t.getTextContent());
                                                    };break;
                                                    default:;break;
                                                }
                                              t = t.getNextSibling();
                                            }
                                        }
                                    i = i.getNextSibling();
                                    } 
                            };break;
                            default:break;
                            }                     
                          comp=comp.getNextSibling(); 
                        }
                        company c = new company(name,depo,persent,period,plus,minus,reinvesting);
                        companys.add(c); 
                    }
                    m = m.getNextSibling();
                }
               n = n.getNextSibling();
            }
        }
        show_companies();
    }
    static void save_companies(){
       StringBuilder str = new StringBuilder();
       str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
       .append("<companies>\n");
       companys.stream().forEach((c2)->{str.append(c2.get_company_to_string());});
       str.append("</companies>");
       clientProfile.write(file,str.toString());
    }
    static void set_updated_company(company c1){
        for(int i=0 ; i< companys.size();i++){
           if(companys.get(i).get_name().equals(c1.get_name())){
                companys.set(i, c1);
                break;
            }
        }
        save_companies();
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
    }    
    void showSelectedCompany(){
        System.out.println(ct.getSelectedRow());
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                comp = new companies();
                comp.setVisible(true);
            } catch (Exception ex) {
                log("fer");
                log(ex); 
            }
        });
        try{
        sc = new selectedCompany();
        sc.setVisible(false);
        c1 = new clientProfile();
        c1.set_comp(comp);
        c1.setVisible(false);
        get_companies();
        }catch(Exception ex){
            log(ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about_programm;
    private javax.swing.JMenuItem add_company;
    private javax.swing.JMenuItem calculate;
    private javax.swing.JMenu companies;
    private static javax.swing.JTable ct;
    private javax.swing.JMenuItem delete_company;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu profile;
    private javax.swing.JMenu programm;
    private static javax.swing.JMenuItem save_companies_action;
    private javax.swing.JMenuItem selectCompany;
    private javax.swing.JMenuItem select_all_companies;
    private javax.swing.JPopupMenu.Separator separator;
    // End of variables declaration//GEN-END:variables
}
