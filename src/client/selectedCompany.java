package client;

import javax.swing.table.DefaultTableModel;

public class selectedCompany extends javax.swing.JFrame {
    company c;
    public selectedCompany() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        sct = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        sct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Период", "Довложения", "Снятие", "Реинвестирование", "Депозит на начало месяца", "Депозит на конец месяца"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(sct);
        if (sct.getColumnModel().getColumnCount() > 0) {
            sct.getColumnModel().getColumn(0).setMaxWidth(110);
            sct.getColumnModel().getColumn(1).setMaxWidth(130);
            sct.getColumnModel().getColumn(2).setMaxWidth(70);
            sct.getColumnModel().getColumn(4).setMinWidth(90);
            sct.getColumnModel().getColumn(5).setMinWidth(90);
        }

        jButton1.setText("Сохранить");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Назад");

        jButton3.setText("Расчитать прибыль");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("Создать отчет");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(6, 6, 6))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        showSelectedCompany();
    }//GEN-LAST:event_jButton3MouseClicked
    company calculate(){
       return c.getCalculateCompany();
    }
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
       //Save company
       for(int i=0;i<sct.getRowCount();i++){
           c.set_plus(i, sct.getValueAt(i, 1).toString()); 
           c.set_minus(i, sct.getValueAt(i, 2).toString());
           c.set_reinvesting(i, sct.getValueAt(i, 3).toString());
       }
       companies.set_updated_company(c);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
      createPDFReport.createReport(false, null, c); // неполный отчет
    }//GEN-LAST:event_jButton4MouseClicked
        
    
    void setSelectedCompany(company c) {
        this.c = c;
        showSelectedCompany();
    }
    protected  void showSelectedCompany(){
        c.getCalculateCompany();
        ((DefaultTableModel)sct.getModel()).setNumRows(0);
        int sizeOfExisting = c.get_reinvesting().size();
        for(int i=0;i<Integer.parseInt(c.get_period());i++){
            if(i >= sizeOfExisting){
               c.set_plus("0");
               c.set_minus("0");
               c.set_reinvesting("false");
            }
            ((DefaultTableModel)sct.getModel()).addRow(
                new Object[]{Integer.toString(i), 
                    c.get_plus(i),
                    c.get_minus(i),
                    c.get_reinvesting(i),
                    c.get_depofirst(i),
                    c.get_depolast(i)
            });
        }
    }
    
    static void log(Object o){
        System.out.println(o);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new selectedCompany().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable sct;
    // End of variables declaration//GEN-END:variables
}
