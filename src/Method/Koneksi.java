/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author NAS
 */
public class Koneksi {
    public Connection con;
    public Statement st;
    public ResultSet rs;
    public String sql;
    
    public void Koneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/elektronik?","root","");
            st=con.createStatement();            
        }catch(Exception a){
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal, Terjadi kesalahaan Pada : \n"+a);
        }
    }
    
}
