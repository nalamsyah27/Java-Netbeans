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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
public class Penjualan extends javax.swing.JInternalFrame {
    Koneksi xxx;
    private DefaultTableModel model;
    public String kode,nama,stok,harga;
    

    /**
     * Creates new form Supplier
     */
    public Penjualan() {
        initComponents();
        xxx = new Koneksi();
        xxx.Koneksi();
        EmptTeks();
        bersih();
        siapIsi(false);
        tombolNormal();

        
        DateFormat tgl=new SimpleDateFormat("dd/MMMM/yyyy");
        String htgl=tgl.format(Calendar.getInstance().getTime());
        LTgl.setText(htgl);
    }
    
     public String getkode() {
        return kode;
    }

    public String getnama() {
        return nama;
    }
    
    public String getharga() {
        return harga;
    }
    
    public String getstok() {
        return stok;
    }
    
    public void itemterpilih(){
        TKode.setText(kode);
        TNama.setText(nama);
        THarga.setText(harga);
        LStok.setText(stok);
        TQty.requestFocus();
    }
    
    private void bersih(){
        TNo.setText(null);
        LKode.setText(null);
        LStok.setText(null);
        TNama.setText(null);
        TKode.setText(null);
        THarga.setText(null);
        TQty.setText(null);
        TTotal.setText(null);
        TBayar.setText(null);
        TKembali.setText(null);
    }
      
    private void siapIsi(boolean a){
        LTgl.setEnabled(a);
        TNo.setEnabled(a);
        TKode.setEnabled(a);
        TNama.setEnabled(a);
        THarga.setEnabled(a);
        TQty.setEnabled(a);
        TTotal.setEnabled(a);
        TBayar.setEnabled(a);
        TKembali.setEnabled(a);
    }
    
    private void tombolNormal(){
        BAdd.setEnabled(true);
        BSimpan.setEnabled(false);
        BAddBarang.setEnabled(false);
        BBatal.setEnabled(false);
        BCari.setEnabled(false);
    }
    
    
    

    
    private void EmptTeks(){
        try {
            String sql ="SELECT MAX(right(NoTransaksi,2)) AS no FROM DetailJual";
            xxx.st=xxx.con.createStatement();
            xxx.rs=xxx.st.executeQuery(sql);
                while (xxx.rs.next()) {
                    if (xxx.rs.first()== false){
                        TNo.setText("NT001");
                        
                    }else{
                        xxx.rs.last();
                        int auto_id=xxx.rs.getInt(1)+1;
                        String no = String.valueOf(auto_id);
                        int noLong = no.length();
                                    for(int a=0;a<3-noLong;a++){
                                        no = "0"+no;
                                    }
                                    TNo.setText("NT"+no);
                            
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
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Harga");
        tabel.addColumn("Qty");
        tabel.addColumn("Total");
        try {
            xxx.Koneksi();
            String viewdata= "select * from DetailJualTemp";
            xxx.rs= xxx.st.executeQuery(viewdata);
            while (xxx.rs.next()) {
                tabel.addRow(new Object[]{
                    xxx.rs.getString(2),
                    xxx.rs.getString(3),
                    xxx.rs.getString(4),
                    xxx.rs.getString(5),
                    xxx.rs.getString(6),
                });                
            }
            Table.setModel(tabel);
            int jumlahBaris = tabel.getRowCount();
            int totalBiaya = 0;
            int jumlahBarang, hargaBarang;
            TableModel tabelModel;
            tabelModel = Table.getModel();
                 for (int i=0; i<jumlahBaris; i++){
                    jumlahBarang = Integer.parseInt(tabelModel.getValueAt(i, 3).toString());
                    hargaBarang = Integer.parseInt(tabelModel.getValueAt(i, 2).toString());
                    totalBiaya = totalBiaya + (jumlahBarang*hargaBarang);
                }
                  TTotal.setText(String.valueOf(totalBiaya));
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
        
        LKode.setText(a);
        
    }
    
    public void batal(){
        try {
            xxx.Koneksi();
            xxx.con.createStatement().execute("Delete from detailjualtemp");
        } catch (Exception e) {
        }
    }
    
    public void simpan(){
        try {
            if (TTotal.getText().equals("") || TBayar.getText().equals("") || TBayar.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Data Belum Lengkap");
                TBayar.requestFocus();
            }else{
                xxx.Koneksi();
                xxx.con.createStatement().execute("insert into detailjual (NoTransaksi, KodeBarang, NamaBarang, HargaJual, Qty, Total) select NoTransaksi, KodeBarang, NamaBarang, HargaJual, Qty, Total from detailjualtemp");
                xxx.con.createStatement().execute("Delete from detailjualtemp");
                xxx.con.createStatement().execute("insert into jual values('"+TNo.getText()+"','"+LTgl.getText()+"','"+TTotal.getText()+"','"+TBayar.getText()+"','"+TKembali.getText()+"')");
                JOptionPane.showMessageDialog(rootPane, "Cetak Struk");
                    java.io.File namaFile=new java.io.File("src/Laporan/Struk.jasper");
                    HashMap parameter = new HashMap();
                    parameter.put("No",TNo.getText());
                    try {
                        net.sf.jasperreports.engine.JasperReport jasper;
                        jasper=(net.sf.jasperreports.engine.JasperReport)
                        net.sf.jasperreports.engine.util.JRLoader.loadObject(namaFile.getPath());
                        net.sf.jasperreports.engine.JasperPrint jp;
                        jp=net.sf.jasperreports.engine.JasperFillManager.fillReport(jasper, parameter,xxx.con);
                        net.sf.jasperreports.view.JasperViewer.viewReport(jp,false);
                    } catch (JRException ex) {
                        System.out.println(ex);
                    }
                bersih();
                siapIsi(false);
                batal();
                tampildata();
                BAdd.setText("Tambah");
                tombolNormal();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRROORRR");
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

        DesktopPane = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        LTgl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        BExit = new javax.swing.JButton();
        BAdd = new javax.swing.JButton();
        BSimpan = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TTotal = new javax.swing.JTextField();
        TBayar = new javax.swing.JTextField();
        TKembali = new javax.swing.JTextField();
        TNo = new javax.swing.JTextField();
        TNama = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        BCari = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TKode = new javax.swing.JTextField();
        THarga = new javax.swing.JTextField();
        TQty = new javax.swing.JTextField();
        BAddBarang = new javax.swing.JButton();
        BBatal = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LStok = new javax.swing.JLabel();
        LKode = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setTitle("Transaksi Penjualan");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(228, 153, 33));

        jLabel2.setFont(new java.awt.Font("Vijaya", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Transaksi Penjualan");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Penjualan.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Vijaya", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Input Data Penjualan");

        LTgl.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        LTgl.setForeground(new java.awt.Color(255, 255, 255));
        LTgl.setText("Tgl");

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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                        .addComponent(LTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel10)))
                    .addComponent(LTgl))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(228, 153, 33));
        jLabel1.setText("Nama Barang");

        jPanel3.setBackground(new java.awt.Color(228, 153, 33));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        BExit.setFont(new java.awt.Font("Vijaya", 0, 14)); // NOI18N
        BExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Keluar.png"))); // NOI18N
        BExit.setText("Keluar");
        BExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BExitActionPerformed(evt);
            }
        });

        BAdd.setFont(new java.awt.Font("Vijaya", 0, 14)); // NOI18N
        BAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Add.png"))); // NOI18N
        BAdd.setText("Tambah");
        BAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddActionPerformed(evt);
            }
        });

        BSimpan.setFont(new java.awt.Font("Vijaya", 0, 14)); // NOI18N
        BSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/save.png"))); // NOI18N
        BSimpan.setText("Simpan");
        BSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSimpanActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(228, 153, 33));
        jLabel6.setText("Total");

        jLabel7.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(228, 153, 33));
        jLabel7.setText("Bayar");

        jLabel8.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(228, 153, 33));
        jLabel8.setText("Kembali");

        TTotal.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        TTotal.setText("jTextField1");

        TBayar.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        TBayar.setText("jTextField2");
        TBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBayarKeyPressed(evt);
            }
        });

        TKembali.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        TKembali.setText("jTextField2");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BExit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TTotal)
                    .addComponent(TBayar)
                    .addComponent(TKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BAdd)
                    .addComponent(BSimpan)
                    .addComponent(BExit))
                .addGap(44, 44, 44))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(TKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TNo.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        TNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoKeyPressed(evt);
            }
        });

        TNama.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        TNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNamaActionPerformed(evt);
            }
        });

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

        jLabel12.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(228, 153, 33));
        jLabel12.setText("No Transaksi");

        jLabel3.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(228, 153, 33));
        jLabel3.setText("Harga");

        TKode.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N

        THarga.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        THarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THargaKeyPressed(evt);
            }
        });

        TQty.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        TQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TQtyKeyPressed(evt);
            }
        });

        BAddBarang.setFont(new java.awt.Font("Vijaya", 0, 14)); // NOI18N
        BAddBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/O.png"))); // NOI18N
        BAddBarang.setText("Add");
        BAddBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddBarangActionPerformed(evt);
            }
        });

        BBatal.setFont(new java.awt.Font("Vijaya", 0, 14)); // NOI18N
        BBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/C.png"))); // NOI18N
        BBatal.setText("No");
        BBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBatalActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(228, 153, 33));
        jLabel4.setText("Kode Barang");

        jLabel5.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(228, 153, 33));
        jLabel5.setText("Qty");

        LStok.setForeground(new java.awt.Color(255, 255, 255));
        LStok.setText("jLabel11");

        LKode.setFont(new java.awt.Font("Vijaya", 0, 12)); // NOI18N
        LKode.setForeground(new java.awt.Color(255, 255, 255));
        LKode.setText("jLabel11");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TNo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(LKode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LStok)
                        .addGap(237, 237, 237))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TKode, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BCari))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TNama)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(THarga)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(TQty))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BAddBarang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BBatal)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(LStok)
                    .addComponent(LKode))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BAddBarang)
                    .addComponent(BBatal)
                    .addComponent(TKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(THarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BCari))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DesktopPaneLayout = new javax.swing.GroupLayout(DesktopPane);
        DesktopPane.setLayout(DesktopPaneLayout);
        DesktopPaneLayout.setHorizontalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DesktopPaneLayout.setVerticalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DesktopPane.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_BExitActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
       tabeledit();
    }//GEN-LAST:event_TableMouseClicked

    private void TNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoKeyPressed
    
    }//GEN-LAST:event_TNoKeyPressed

    private void BCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCariActionPerformed
        ListBarangPenjualan s=new ListBarangPenjualan();
        DesktopPane.add(s);
        s.fAB=this;       
        s.setVisible(true);
        TKode.setText(kode);
        TNama.setText(nama);
        THarga.setText(harga);
        LStok.setText(stok);
    }//GEN-LAST:event_BCariActionPerformed

    private void BAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAddActionPerformed
        if(BAdd.getText().equalsIgnoreCase("tambah")){
            BAdd.setText("Batal");
            siapIsi(true);
            bersih();
            EmptTeks();
            tampildata();
            TKode.requestFocus();
            BSimpan.setEnabled(true);
            BAddBarang.setEnabled(true);
            BBatal.setEnabled(true);
            BCari.setEnabled(true);
        }else{
            bersih();
            siapIsi(false);
            batal();
            EmptTeks();
            tampildata();
            BAdd.setText("Tambah");
            tombolNormal();
        }
        
    }//GEN-LAST:event_BAddActionPerformed

    private void BSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanActionPerformed
        simpan();
        
            
    }//GEN-LAST:event_BSimpanActionPerformed

    private void THargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THargaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_THargaKeyPressed

    private void TQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TQtyKeyPressed
        if(evt.getKeyCode()==10){
            if(TKode.getText().equals("") || TNo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Data Belum Lengkap");
        } else{
             int jml=Integer.parseInt(TQty.getText());
             int st=Integer.parseInt(LStok.getText());
             int h=Integer.parseInt(THarga.getText());
             int t=jml*h;
             if(jml>st){
                JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
                TQty.requestFocus();
             }else{        
                    try {
                        xxx.Koneksi();
                        xxx.con.createStatement().execute("insert into detailjualtemp values('"+TNo.getText()+"','"+TKode.getText()+"','"+TNama.getText()+"','"+THarga.getText()+"','"+TQty.getText()+"','"+t+"')");
                        tampildata();
                        TKode.requestFocus();
                        TKode.setText("");
                        TNama.setText("");
                        THarga.setText("");
                        TQty.setText("");
                        LStok.setText("");
            
                        } catch (Exception e) {
                        System.out.println(e);
                    
                        }                         
                    }   
             }
        }
    }//GEN-LAST:event_TQtyKeyPressed

    private void TNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNamaActionPerformed

    private void BAddBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAddBarangActionPerformed
        if(TKode.getText().equals("") || TNo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Data Belum Lengkap");
        } else{
             int jml=Integer.parseInt(TQty.getText());
             int st=Integer.parseInt(LStok.getText());
             int h=Integer.parseInt(THarga.getText());
             int t=jml*h;
             if(jml>st){
                JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
                TQty.requestFocus();
             }else{        
                    try {
                        xxx.Koneksi();
                        xxx.con.createStatement().execute("insert into detailjualtemp values('"+TNo.getText()+"','"+TKode.getText()+"','"+TNama.getText()+"','"+THarga.getText()+"','"+TQty.getText()+"','"+t+"')");
                        tampildata();
                        TKode.requestFocus();
                        TKode.setText("");
                        TNama.setText("");
                        THarga.setText("");
                        TQty.setText("");
                        LStok.setText("");
            
                        } catch (Exception e) {
                        System.out.println(e);
                    
                        }
                
                                                
                    }
                
             }
        

    }//GEN-LAST:event_BAddBarangActionPerformed

    private void TBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBayarKeyPressed
        if(evt.getKeyCode()==10){
        int t=Integer.parseInt(TTotal.getText());
        int b=Integer.parseInt(TBayar.getText());
            if (t>b){
            JOptionPane.showMessageDialog(null, "Nominal tidak mencukupi");
            }else{
            int k=b-t;
            TKembali.setText(String.valueOf(k));
            }
        }
    }//GEN-LAST:event_TBayarKeyPressed

    private void BBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBatalActionPerformed
        try {
            xxx.Koneksi();
            xxx.con.createStatement().execute("delete from detailjualtemp where KodeBarang='"+LKode.getText()+"'");
            tampildata();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_BBatalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdd;
    private javax.swing.JButton BAddBarang;
    private javax.swing.JButton BBatal;
    private javax.swing.JButton BCari;
    private javax.swing.JButton BExit;
    private javax.swing.JButton BSimpan;
    private javax.swing.JDesktopPane DesktopPane;
    private javax.swing.JLabel LKode;
    private javax.swing.JLabel LStok;
    private javax.swing.JLabel LTgl;
    private javax.swing.JTextField TBayar;
    private javax.swing.JTextField THarga;
    private javax.swing.JTextField TKembali;
    private javax.swing.JTextField TKode;
    private javax.swing.JTextField TNama;
    private javax.swing.JTextField TNo;
    private javax.swing.JTextField TQty;
    private javax.swing.JTextField TTotal;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
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
