package kütüphane.sistemii;

import com.mysql.jdbc.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static kütüphane.sistemii.Bağlantı.bağlan;

public class ÖdünçListele extends javax.swing.JFrame {

    private static String tc ;

    public ÖdünçListele(String abc) {
        initComponents();
        tc = abc;
        listele();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ÖdünçListele.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ÖdünçListele.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ÖdünçListele.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ÖdünçListele.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ÖdünçListele(tc).setVisible(true);
            }
        });
    }

    public void listele() {
        try {
            Bağlantı.bağlantıAç();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ÖdünçListele.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs;
        int memberId = -1;
        try {
            ResultSet rs2;
            Statement stm = (Statement) bağlan.createStatement();
            String sql = "select member_id from librarymenagement.member where national_id = '" + tc + "';";
            rs2 = stm.executeQuery(sql);
            rs2.next();
            memberId = rs2.getInt("member_id");

            Bağlantı.bağlantıKapat();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("kayıt yapılırken hata meydana geldi");
        }
        
        try {
            Bağlantı.bağlantıAç();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kitapİşlemleri.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (memberId == -1) {
                JOptionPane.showMessageDialog(null, "Hata meydana geldi, lütfen bilgileri kontrol ediniz");
            } else {
                Statement stm = (Statement) bağlan.createStatement();
                String sql = "select book_id, purchase_date, return_date from barrow where member_id=" + memberId;
                rs = stm.executeQuery(sql);

                DefaultTableModel defaulttableModel = new DefaultTableModel();
                defaulttableModel.addColumn("Book");
                defaulttableModel.addColumn("Purchase Date");
                defaulttableModel.addColumn("Return Date");

                String bookName = "";
                while (rs.next()) {
                    try {
                        Bağlantı.bağlantıAç();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ÖdünçListele.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        ResultSet rsb;
                        Statement stmb = (Statement) bağlan.createStatement();
                        String cib = "select book_name from book where book_id =" + rs.getInt("book_id");
                        rsb = stmb.executeQuery(cib);
                        if(rsb.next()){
                         bookName = rsb.getString("book_name");
                        }
                        

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("kayıt bulunurken hata meydana geldi");
                    }
                    defaulttableModel.addRow(new Object[]{
                        bookName,
                        rs.getString("purchase_date"),
                        rs.getString("return_date")
                    });

                    
                }
                jTable1.setModel(defaulttableModel);
            }

            Bağlantı.bağlantıKapat();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("kayıt bulunurken ha meydana geldi");
        }

    }


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
