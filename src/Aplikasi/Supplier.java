/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Aplikasi;

/**
 *
 * @author NAS
 */
import Method.Koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class Supplier extends javax.swing.JInternalFrame {
    Koneksi xxx;

    /**
     * Creates new form Supplier
     */
    public Supplier() {
        initComponents();
        xxx = new Koneksi();
        xxx.Koneksi();
        tampildata();
        EmptTeks();
        Awal();
    }
    
    private void Awal(){
        TNama.requestFocus();
        TNama.setText("");
        TAlamat.setText("");
        TTelp.setText("");
        TEmail.setText("");
        BAdd.setEnabled(false);
        BEdit.setEnabled(false);
    }
    
    public void Add(){
        TNama.requestFocus();
        TNama.setText("");
        TAlamat.setText("");
        TTelp.setText("");
        TEmail.setText("");
        BEdit.setEnabled(false);
        BHapus.setEnabled(true);
        BSimpan.setEnabled(true);
     
    }
    
    public void Simpan(){
        try {
            xxx.Koneksi();
            xxx.con.createStatement().execute("insert into Supplier values('"+TKode.getText()+"','"+TNama.getText()+"','"+TAlamat.getText()+"','"+TTelp.getText()+"','"+TEmail.getText()+"')");
            JOptionPane.showMessageDialog(null,"Data Telah Tersimpan");
            Awal();
            tampildata();
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane,"Data Belum Lengkap!!!");
            Awal();
        }
        
    }
    
    public void Hapus(){
        try {
            xxx.Koneksi();
            if (TTelp.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane,"Maaf Data Tidak Ada !!!");
                }
            else{
                xxx.con.createStatement().execute("delete from Supplier where KodeSupplier='"+TKode.getText()+"'");
                JOptionPane.showMessageDialog(rootPane,"Data Telah Dihapus");
                Awal();
                tampildata();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,"Maaf Data Tidak Ada !!!");
        }
    }
    
    public void Edit(){
         try {
            xxx.Koneksi();
            if (TAlamat.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane,"Maaf Data Belum Lengkap !!!");
                }
            else{
            xxx.con.createStatement().execute("update Supplier set NamaToko='"+TNama.getText()+"',Alamat='"+TAlamat.getText()+"',Telp='"+TTelp.getText()+"',Email='"+TEmail.getText()+"' where KodeSupplier='"+TKode.getText()+"'");
            JOptionPane.showMessageDialog(rootPane,"Data Telah di Edit");
            tampildata();
                }
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane,"Maaf Data Anda Salah!!!");      
        }
    }
    
    private void EmptTeks(){
        try {
            String sql ="SELECT MAX(right(KodeSupplier,2)) AS no FROM Supplier";
            xxx.st=xxx.con.createStatement();
            xxx.rs=xxx.st.executeQuery(sql);
                while (xxx.rs.next()) {
                    if (xxx.rs.first()== false){
                        TKode.setText("KS001");
                        
                    }else{
                        xxx.rs.last();
                        int auto_id=xxx.rs.getInt(1)+1;
                        String no = String.valueOf(auto_id);
                        int noLong = no.length();
                                    for(int a=0;a<3-noLong;a++){
                                        no = "0"+no;
                                    }
                                    TKode.setText("KS"+no);
                            
                    }
                
            }
                TNama.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
        }
        
    }
    
    public final void tampildata(){
        DefaultTableModel tabel=new DefaultTableModel();
        tabel.addColumn("Kode");
        tabel.addColumn("Nama Toko");
        tabel.addColumn("Alamat");
        tabel.addColumn("Telepon");
        tabel.addColumn("Email");
        try {
            xxx.Koneksi();
            String viewdata= "select * from Supplier";
            xxx.rs= xxx.st.executeQuery(viewdata);
            while (xxx.rs.next()) {
                tabel.addRow(new Object[]{
                    xxx.rs.getString(1),
                    xxx.rs.getString(2),
                    xxx.rs.getString(3),
                    xxx.rs.getString(4),
                    xxx.rs.getString(5),
                });                
            }
            Table.setModel(tabel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error");
        }
    }
    
    public void tabeledit(){
        int tabel=Table.getSelectedRow();
        String a=Table.getValueAt(tabel,0).toString();
        String b=Table.getValueAt(tabel,1).toString();
        String c=Table.getValueAt(tabel,2).toString();
        String d=Table.getValueAt(tabel,3).toString();
        String e=Table.getValueAt(tabel,4).toString();
        
        TKode.setText(a);
        TNama.setText(b);
        TAlamat.setText(c);
        TTelp.setText(d);
        TEmail.setText(e);

    }
    
    private void cari(){
        try {
            String k = TKode.getText();
            //String passw = new String(TPass.getPassword());
            xxx.st=xxx.con.createStatement();
            String sql = "Select * From Supplier Where KodeSupplier = '"+k+"'";
            xxx.rs =xxx.st.executeQuery(sql);
        if (xxx.rs.next()){
                TNama.setText(xxx.rs.getString(2));
                TAlamat.setText(xxx.rs.getString(3));
                TTelp.setText(xxx.rs.getString(4));
                TEmail.setText(xxx.rs.getString(5));

        }
        else {
            JOptionPane.showMessageDialog(null, "Kode tidak ditemukan");
            //kosong();
            TKode.requestFocus();
            
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kode tidak ditemukan");
            //kosong();
            TKode.requestFocus();
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        BExit = new javax.swing.JButton();
        BAdd = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BHapus = new javax.swing.JButton();
        BCetak = new javax.swing.JButton();
        BSimpan = new javax.swing.JButton();
        TKode = new javax.swing.JTextField();
        TNama = new javax.swing.JTextField();
        TAlamat = new javax.swing.JTextField();
        TTelp = new javax.swing.JTextField();
        TEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        BCari = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setTitle("Master Supplier");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(228, 153, 33));

        jLabel2.setFont(new java.awt.Font("Vijaya", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Input Data Supplier");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Suplier.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Vijaya", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Input, Edit dan Hapus Data Supplier");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(228, 153, 33));
        jLabel1.setText("Nama Toko");

        jLabel3.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(228, 153, 33));
        jLabel3.setText("Kode Supplier");

        jLabel4.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(228, 153, 33));
        jLabel4.setText("Alamat");

        jLabel5.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(228, 153, 33));
        jLabel5.setText("No.Telp");

        jLabel7.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(228, 153, 33));
        jLabel7.setText("Email");

        jPanel3.setBackground(new java.awt.Color(228, 153, 33));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        BExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Keluar.png"))); // NOI18N
        BExit.setText("Keluar");
        BExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BExitActionPerformed(evt);
            }
        });

        BAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Add.png"))); // NOI18N
        BAdd.setText("Baru");
        BAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddActionPerformed(evt);
            }
        });

        BEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Modify.png"))); // NOI18N
        BEdit.setText("Edit");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        BHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Trash.png"))); // NOI18N
        BHapus.setText("Hapus");
        BHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BHapusActionPerformed(evt);
            }
        });

        BCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Print.png"))); // NOI18N
        BCetak.setText("Cetak");

        BSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/save.png"))); // NOI18N
        BSimpan.setText("Simpan");
        BSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BAdd)
                .addGap(18, 18, 18)
                .addComponent(BEdit)
                .addGap(18, 18, 18)
                .addComponent(BHapus)
                .addGap(18, 18, 18)
                .addComponent(BSimpan)
                .addGap(18, 18, 18)
                .addComponent(BCetak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BExit)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BAdd)
                        .addComponent(BEdit)
                        .addComponent(BHapus)
                        .addComponent(BCetak)
                        .addComponent(BSimpan))
                    .addComponent(BExit))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        TKode.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        TKode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKodeKeyPressed(evt);
            }
        });

        TNama.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N

        TAlamat.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N

        TTelp.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N

        TEmail.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N

        Table.setFont(new java.awt.Font("Vijaya", 0, 14)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        BCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Cari.png"))); // NOI18N
        BCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(TKode, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BCari))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TNama, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(TTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TEmail)
                                    .addComponent(TAlamat))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BCari)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(TKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(TTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_BExitActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        tabeledit();
        BEdit.setEnabled(true);
        BHapus.setEnabled(true);
        BAdd.setEnabled(true);
        BSimpan.setEnabled(false);
    }//GEN-LAST:event_TableMouseClicked

    private void TKodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKodeKeyPressed
    
    }//GEN-LAST:event_TKodeKeyPressed

    private void BCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCariActionPerformed
        cari();
        BEdit.setEnabled(true);
        BHapus.setEnabled(true);
        BAdd.setEnabled(true);
        BSimpan.setEnabled(false);
    }//GEN-LAST:event_BCariActionPerformed

    private void BAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAddActionPerformed
        Awal();
        EmptTeks();
        BSimpan.setEnabled(true);
        BEdit.setEnabled(false);
    }//GEN-LAST:event_BAddActionPerformed

    private void BSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanActionPerformed
        Simpan();
        EmptTeks();
    }//GEN-LAST:event_BSimpanActionPerformed

    private void BHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHapusActionPerformed
        Hapus();
        BSimpan.setEnabled(true);
    }//GEN-LAST:event_BHapusActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        Edit();
    }//GEN-LAST:event_BEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdd;
    private javax.swing.JButton BCari;
    private javax.swing.JButton BCetak;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BExit;
    private javax.swing.JButton BHapus;
    private javax.swing.JButton BSimpan;
    private javax.swing.JTextField TAlamat;
    private javax.swing.JTextField TEmail;
    private javax.swing.JTextField TKode;
    private javax.swing.JTextField TNama;
    private javax.swing.JTextField TTelp;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
