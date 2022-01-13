package kütüphane.sistemii;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static kütüphane.sistemii.Bağlantı.bağlan;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author XFC
 */
public class KitapListele extends javax.swing.JFrame {

    private ResultSet rs;

    /**
     * Creates new form KitapListele
     */
    public KitapListele() {
        initComponents();
        listele();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

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

        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setText("GERİ DÖN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
        new Kitapİşlemleri().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    public void listele() {

        try {
            Bağlantı.bağlantıAç();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kitapİşlemleri.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Statement stm = (Statement) bağlan.createStatement();
            String sql = "select book_name, category_id, author_id, publisher_id, release_date from book";
            rs = stm.executeQuery(sql);

            DefaultTableModel defaulttableModel = new DefaultTableModel();
            defaulttableModel.addColumn("KİTAP ADI");
            defaulttableModel.addColumn("KATEGORİ");
            defaulttableModel.addColumn("YAZAR");
            defaulttableModel.addColumn("YAYINEVİ");
            defaulttableModel.addColumn("TESLİM TARİHİ");

            String categoryName = "";
            String authorName = "";
            String publisherName = "";

            while (rs.next()) {
                try {
                    Bağlantı.bağlantıAç();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Kitapİşlemleri.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ResultSet rsc;
                    Statement stmc = (Statement) bağlan.createStatement();
                    String cid = "select category_name from category where category_id =" + rs.getString("category_id");
                    rsc = stmc.executeQuery(cid);
                    rsc.next();
                    categoryName = rsc.getString("category_name");

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("kayıt bulunurken hata meydana geldi");
                }
                try {
                    Bağlantı.bağlantıAç();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Kitapİşlemleri.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ResultSet rsa;
                    Statement stma = (Statement) bağlan.createStatement();
                    String aid = "select author_name from author where author_id =" + rs.getString("author_id");
                    rsa = stma.executeQuery(aid);
                    rsa.next();
                    authorName = rsa.getString("author_name");

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("kayıt bulunurken hata meydana geldi");
                }
                try {
                    Bağlantı.bağlantıAç();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Kitapİşlemleri.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ResultSet rsp;
                    Statement stmp = (Statement) bağlan.createStatement();
                    String pid = "select publisher_name from publisher where publisher_id =" + rs.getString("publisher_id");
                    rsp = stmp.executeQuery(pid);
                    rsp.next();
                    publisherName = rsp.getString("publisher_name");

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("kayıt bulunurken hata meydana geldi");
                }
                defaulttableModel.addRow(new Object[]{
                    rs.getString("book_name"),
                    categoryName,
                    authorName,
                    publisherName,
                    rs.getDate("release_date")

                });
                jTable1.setModel(defaulttableModel);
            }

            Bağlantı.bağlantıKapat();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("kayıt bulunurken hata meydana geldi");
        }

    }

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
            java.util.logging.Logger.getLogger(KitapListele.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KitapListele.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KitapListele.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KitapListele.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KitapListele().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
