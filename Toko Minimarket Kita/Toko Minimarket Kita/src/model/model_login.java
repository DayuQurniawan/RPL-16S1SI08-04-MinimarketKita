/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import controller.controller_login;
import koneksi.UserID;
import koneksi.Koneksi;
import view.FrmLogin;
import view.FrmUtama;
import view.FrmTransaksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author aliya
 */
public class model_login implements controller_login{
    public void Login(FrmLogin lgn) throws SQLException {
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM kasir where Username='"+lgn.txtusername.getText()+"' and Passwords = '"+lgn.txtpassword.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()) {
                if(rs.getString(4).equals("Admin")){
                    new FrmUtama().show();
                    lgn.dispose();
                
                }else if(rs.getString(4).equals("Kasir")){
                    new FrmTransaksi().show();
                    lgn.dispose();
                } else {
                    JOptionPane.showMessageDialog(lgn, "Password Salah");
                    Bersih(lgn);
                }
            } else {
                JOptionPane.showMessageDialog(lgn, "Username Belum Terdaftar");
                Bersih(lgn);
                lgn.txtusername.requestFocus();
            }
        } catch (Exception e) {
        }
    }
    

    public void Bersih(FrmLogin lgn) throws SQLException {
        lgn.txtusername.setText(null);
        lgn.txtpassword.setText(null);
        lgn.txtusername.requestFocus();
    }

    @Override
    public void Batal(FrmLogin lgn) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}