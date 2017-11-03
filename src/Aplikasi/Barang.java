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
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;


public class Barang extends javax.swing.JInternalFrame {
    Koneksi xxx;
    

    /**
     * Creates new form Supplier
     */
    public Barang() {
        initComponents();
        xxx = new Koneksi();
        xxx.Koneksi();
        tampildata();
        EmptTeks();
        TNama.requestFocus();
        Awal();
    }
    
    private void Awal(){
        TNama.requestFocus();
        TNama.setText("");
        THBeli.setText("");
        THJual.setText("");
        TStok.setText("0");
        CKat.setSelectedItem("Pilih");
        CSat.setSelectedItem("Pilih");
        BAdd.setEnabled(false);
        BEdit.setEnabled(false);
        TStok.setEnabled(false);
    }
    
    public void Add(){
        TNama.requestFocus();
        TNama.setText("");
        THBeli.setText("");
        THJual.setText("");
        TStok.setText("0");
        CKat.setSelectedItem("Pilih");
        CSat.setSelectedItem("Pilih");
        BEdit.setEnabled(false);
        BHapus.setEnabled(true);
        BSimpan.setEnabled(true);
        TStok.setEnabled(false);
     
    }
    
    public void Simpan(){
        try {
            xxx.Koneksi();
            xxx.con.createStatement().execute("insert into Barang values('"+TKode.getText()+"','"+TNama.getText()+"','"+THBeli.getText()+"','"+THJual.getText()+"','"+TStok.getText()+"','"+CKat.getSelectedItem()+"','"+CSat.getSelectedItem()+"')");
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
            if (CKat.getSelectedItem().equals("Pilih")){
                JOptionPane.showMessageDialog(rootPane,"Maaf Data Tidak Ada !!!");
                }
            else{
                xxx.con.createStatement().execute("delete from Barang where KodeBarang='"+TKode.getText()+"'");
                JOptionPane.showMessageDialog(rootPane,"Data Telah Dihapus");
                Awal();
                tampildata();
            }
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane,"Maaf Data Tidak Ada !!!");
        }
    }
    
    public void Edit(){
         try {
            xxx.Koneksi();
            if (THBeli.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane,"Maaf Data Belum Lengkap !!!");
                }
            else{
            xxx.con.createStatement().execute("update Barang set NamaBarang='"+TNama.getText()+"',HargaBeli='"+THBeli.getText()+"',HargaJual='"+THJual.getText()+"',Stok='"+TStok.getText()+"',KatId='"+CKat.getSelectedItem()+"',Satuan='"+CSat.getSelectedItem()+"' where KodeBarang='"+TKode.getText()+"'");
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
            String sql ="SELECT MAX(right(KodeBarang,2)) AS no FROM Barang";
            xxx.st=xxx.con.createStatement();
            xxx.rs=xxx.st.executeQuery(sql);
                while (xxx.rs.next()) {
                    if (xxx.rs.first()== false){
                        TKode.setText("KB001");
                        
                    }else{
                        xxx.rs.last();
                        int auto_id=xxx.rs.getInt(1)+1;
                        String no = String.valueOf(auto_id);
                        int noLong = no.length();
                                    for(int a=0;a<3-noLong;a++){
                                        no = "0"+no;
                                    }
                                    TKode.setText("KB"+no);
                            
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
        tabel.addColumn("Nama");
        tabel.addColumn("H.Beli");
        tabel.addColumn("H.Jual");
        tabel.addColumn("Stok");
        tabel.addColumn("Kategori");
        tabel.addColumn("Satuan");
        try {
            xxx.Koneksi();
            String viewdata= "select * from Barang";
            xxx.rs= xxx.st.executeQuery(viewdata);
            while (xxx.rs.next()) {
                tabel.addRow(new Object[]{
                    xxx.rs.getString(1),
                    xxx.rs.getString(2),
                    xxx.rs.getString(3),
                    xxx.rs.getString(4),
                    xxx.rs.getString(5),
                    xxx.rs.getString(6),
                    xxx.rs.getString(7),
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
        String f=Table.getValueAt(tabel,5).toString();
        String g=Table.getValueAt(tabel,6).toString();
        
        TKode.setText(a);
        TNama.setText(b);
        THBeli.setText(c);
        THJual.setText(d);
        TStok.setText(e);
        CKat.setSelectedItem(f);        
        CSat.setSelectedItem(g);
        
    }
    
    private void cari(){
        try {
            String k = TKode.getText();
            //String passw = new String(TPass.getPassword());
            xxx.st=xxx.con.createStatement();
            String sql = "Select * From Barang Where KodeBarang = '"+k+"'";
            xxx.rs =xxx.st.executeQuery(sql);
        if (xxx.rs.next()){
                TNama.setText(xxx.rs.getString(2));
                THBeli.setText(xxx.rs.getString(3));
                THJual.setText(xxx.rs.getString(4));
                TStok.setText(xxx.rs.getString(5));
                CKat.setSelectedItem(xxx.rs.getString(6));
                CSat.setSelectedItem(xxx.rs.getString(7));
            
        }
        else {
            JOptionPane.showMessageDialog(null, "Kode tidak ditemukan");
   
            
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kode tidak ditemukan");
            
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
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
        THBeli = new javax.swing.JTextField();
        THJual = new javax.swing.JTextField();
        TStok = new javax.swing.JTextField();
        CKat = new javax.swing.JComboBox();
        CSat = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        BCari = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setTitle("Master Barang");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(228, 153, 33));

        jLabel2.setFont(new java.awt.Font("Vijaya", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Input Data Barang");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Barang.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Vijaya", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Input, Edit dan Hapus Data Barang");

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
        jLabel1.setText("Nama Barang");

        jLabel3.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(228, 153, 33));
        jLabel3.setText("Kode Barang");

        jLabel4.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(228, 153, 33));
        jLabel4.setText("Harga Beli");

        jLabel5.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(228, 153, 33));
        jLabel5.setText("Harga Jual");

        jLabel6.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(228, 153, 33));
        jLabel6.setText("Kategori");

        jLabel7.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(228, 153, 33));
        jLabel7.setText("Stok");

        jLabel8.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(228, 153, 33));
        jLabel8.setText("Satuan");

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
        BCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCetakActionPerformed(evt);
            }
        });

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
                .addContainerGap(20, Short.MAX_VALUE))
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

        THBeli.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N

        THJual.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N

        TStok.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N

        CKat.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        CKat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih", "ADAPTOR", "LAPTOP", "SPEAKER" }));
        CKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CKatActionPerformed(evt);
            }
        });

        CSat.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        CSat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih", "UNIT", "SET" }));

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
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TNama, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(CSat, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(THBeli, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(THJual, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TStok, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(CKat, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
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
                            .addComponent(THBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(THJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CKat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(CSat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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

    private void CKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CKatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CKatActionPerformed

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

    private void BCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCetakActionPerformed
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
    }//GEN-LAST:event_BCetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdd;
    private javax.swing.JButton BCari;
    private javax.swing.JButton BCetak;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BExit;
    private javax.swing.JButton BHapus;
    private javax.swing.JButton BSimpan;
    private javax.swing.JComboBox CKat;
    private javax.swing.JComboBox CSat;
    private javax.swing.JTextField THBeli;
    private javax.swing.JTextField THJual;
    private javax.swing.JTextField TKode;
    private javax.swing.JTextField TNama;
    private javax.swing.JTextField TStok;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
