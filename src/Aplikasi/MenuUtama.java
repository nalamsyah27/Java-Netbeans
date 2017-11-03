/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Aplikasi;

import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import Method.Koneksi;
import java.awt.Toolkit;

/**
 *
 * @author NAS
 */
public class MenuUtama extends javax.swing.JFrame {
    Koneksi xxx;
            
    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        //this.setResizable(false);
        initComponents();
        xxx = new Koneksi();
        xxx.Koneksi();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize=(int) tk.getScreenSize().getWidth();
        int ysize=(int) tk.getScreenSize().getHeight();
        this.setSize(xsize, ysize);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        BLogout = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        BBarang = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        BSupplier = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        BPenjualan = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        BPembelian = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        BAbout = new javax.swing.JButton();
        DesktopPane = new javax.swing.JDesktopPane();
        panelWarnaTransparan1 = new Method.PanelWarnaTransparan();
        gambarMenuUtama1 = new Method.GambarMenuUtama();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MENU UTAMA - JNF.ELEKTRONIK KOMPUTER");
        setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(228, 153, 33));
        jToolBar1.setBorder(null);
        jToolBar1.setRollover(true);
        jToolBar1.add(jSeparator1);

        BLogout.setBackground(new java.awt.Color(228, 153, 33));
        BLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Hapus.png"))); // NOI18N
        BLogout.setText("Logout");
        BLogout.setBorder(null);
        BLogout.setFocusable(false);
        BLogout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BLogout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLogoutActionPerformed(evt);
            }
        });
        jToolBar1.add(BLogout);
        jToolBar1.add(jSeparator7);

        BBarang.setBackground(new java.awt.Color(228, 153, 33));
        BBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Barang.png"))); // NOI18N
        BBarang.setText("Barang");
        BBarang.setBorder(null);
        BBarang.setFocusable(false);
        BBarang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BBarang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBarangActionPerformed(evt);
            }
        });
        jToolBar1.add(BBarang);
        jToolBar1.add(jSeparator2);

        BSupplier.setBackground(new java.awt.Color(228, 153, 33));
        BSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Suplier.png"))); // NOI18N
        BSupplier.setText("Supplier");
        BSupplier.setBorder(null);
        BSupplier.setFocusable(false);
        BSupplier.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BSupplier.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSupplierActionPerformed(evt);
            }
        });
        jToolBar1.add(BSupplier);
        jToolBar1.add(jSeparator3);

        BPenjualan.setBackground(new java.awt.Color(228, 153, 33));
        BPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Penjualan.png"))); // NOI18N
        BPenjualan.setText("Penjualan");
        BPenjualan.setBorder(null);
        BPenjualan.setFocusable(false);
        BPenjualan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BPenjualan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPenjualanActionPerformed(evt);
            }
        });
        jToolBar1.add(BPenjualan);
        jToolBar1.add(jSeparator4);

        BPembelian.setBackground(new java.awt.Color(228, 153, 33));
        BPembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Pembelian.png"))); // NOI18N
        BPembelian.setText("Pembelian");
        BPembelian.setBorder(null);
        BPembelian.setFocusable(false);
        BPembelian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BPembelian.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPembelianActionPerformed(evt);
            }
        });
        jToolBar1.add(BPembelian);
        jToolBar1.add(jSeparator5);

        BAbout.setBackground(new java.awt.Color(228, 153, 33));
        BAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/About.png"))); // NOI18N
        BAbout.setText("About");
        BAbout.setBorder(null);
        BAbout.setFocusable(false);
        BAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(BAbout);

        javax.swing.GroupLayout gambarMenuUtama1Layout = new javax.swing.GroupLayout(gambarMenuUtama1);
        gambarMenuUtama1.setLayout(gambarMenuUtama1Layout);
        gambarMenuUtama1Layout.setHorizontalGroup(
            gambarMenuUtama1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gambarMenuUtama1Layout.setVerticalGroup(
            gambarMenuUtama1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelWarnaTransparan1Layout = new javax.swing.GroupLayout(panelWarnaTransparan1);
        panelWarnaTransparan1.setLayout(panelWarnaTransparan1Layout);
        panelWarnaTransparan1Layout.setHorizontalGroup(
            panelWarnaTransparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gambarMenuUtama1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelWarnaTransparan1Layout.setVerticalGroup(
            panelWarnaTransparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gambarMenuUtama1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        DesktopPane.setLayer(panelWarnaTransparan1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopPaneLayout = new javax.swing.GroupLayout(DesktopPane);
        DesktopPane.setLayout(DesktopPaneLayout);
        DesktopPaneLayout.setHorizontalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelWarnaTransparan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DesktopPaneLayout.setVerticalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelWarnaTransparan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("Master");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Barang1.png"))); // NOI18N
        jMenuItem1.setText("Barang");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Supplier1.png"))); // NOI18N
        jMenuItem2.setText("Supplier");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Transaksi");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Penjualan1.png"))); // NOI18N
        jMenuItem3.setText("Penjualan");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Pembelian1.png"))); // NOI18N
        jMenuItem4.setText("Pembelian");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Laporan");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Barang1.png"))); // NOI18N
        jMenuItem5.setText("Data Barang");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Penjualan1.png"))); // NOI18N
        jMenuItem6.setText("Rekap Data Penjualan");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Pembelian1.png"))); // NOI18N
        jMenuItem7.setText("Rekap Data Pembelian");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Bantuan");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
            .addComponent(DesktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DesktopPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBarangActionPerformed
        ListBarang s=new ListBarang();
        DesktopPane.add(s);
        s.setVisible(true);
    }//GEN-LAST:event_BBarangActionPerformed

    private void BLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLogoutActionPerformed
        int selectedOption = JOptionPane.showConfirmDialog(null,"Apakah anda akan menutup Program?", "Tutup Aplikasi", JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            new Login().show();
            this.dispose();
        }
    }//GEN-LAST:event_BLogoutActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Barang s=new Barang();
        DesktopPane.add(s);
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Supplier s=new Supplier();
        DesktopPane.add(s);
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void BSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSupplierActionPerformed
        ListSupplier s=new ListSupplier();
        DesktopPane.add(s);
        s.setVisible(true);
    }//GEN-LAST:event_BSupplierActionPerformed

    private void BPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPenjualanActionPerformed
        Penjualan s=new Penjualan();
        DesktopPane.add(s);
        s.setVisible(true);
    }//GEN-LAST:event_BPenjualanActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Penjualan s=new Penjualan();
        DesktopPane.add(s);
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void BPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPembelianActionPerformed
        Pembelian s=new Pembelian();
        DesktopPane.add(s);
        s.setVisible(true);
    }//GEN-LAST:event_BPembelianActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Pembelian s=new Pembelian();
        DesktopPane.add(s);
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        xxx.Koneksi();
        java.io.File namaFile=new java.io.File("src/Laporan/Barang.jasper");
        try {
        net.sf.jasperreports.engine.JasperReport jasper;
        jasper=(net.sf.jasperreports.engine.JasperReport)
        net.sf.jasperreports.engine.util.JRLoader.loadObject(namaFile.getPath());
        net.sf.jasperreports.engine.JasperPrint jp;
        jp=net.sf.jasperreports.engine.JasperFillManager.fillReport(jasper, null,xxx.con);
        net.sf.jasperreports.view.JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
        System.out.println(ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        xxx.Koneksi();
        java.io.File namaFile=new java.io.File("src/Laporan/RekapJual.jasper");
        try {
        net.sf.jasperreports.engine.JasperReport jasper;
        jasper=(net.sf.jasperreports.engine.JasperReport)
        net.sf.jasperreports.engine.util.JRLoader.loadObject(namaFile.getPath());
        net.sf.jasperreports.engine.JasperPrint jp;
        jp=net.sf.jasperreports.engine.JasperFillManager.fillReport(jasper, null,xxx.con);
        net.sf.jasperreports.view.JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
        System.out.println(ex);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        xxx.Koneksi();
        java.io.File namaFile=new java.io.File("src/Laporan/RekapBeli.jasper");
        try {
        net.sf.jasperreports.engine.JasperReport jasper;
        jasper=(net.sf.jasperreports.engine.JasperReport)
        net.sf.jasperreports.engine.util.JRLoader.loadObject(namaFile.getPath());
        net.sf.jasperreports.engine.JasperPrint jp;
        jp=net.sf.jasperreports.engine.JasperFillManager.fillReport(jasper, null,xxx.con);
        net.sf.jasperreports.view.JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
        System.out.println(ex);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAbout;
    private javax.swing.JButton BBarang;
    private javax.swing.JButton BLogout;
    private javax.swing.JButton BPembelian;
    private javax.swing.JButton BPenjualan;
    private javax.swing.JButton BSupplier;
    private javax.swing.JDesktopPane DesktopPane;
    private Method.GambarMenuUtama gambarMenuUtama1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar jToolBar1;
    private Method.PanelWarnaTransparan panelWarnaTransparan1;
    // End of variables declaration//GEN-END:variables

    
}