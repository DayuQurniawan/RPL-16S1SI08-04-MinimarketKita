
package model;

import controller.controller_anggota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.Koneksi;
import view.FrmAnggota;


/**
 *
 * @author tamasyake
 */
public class model_anggota implements controller_anggota {

    

    public void AutoNomor(FrmAnggota usr)throws SQLException {
         try {
            Connection con = Koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "select max(Kd_Kasir) from kasir";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                usr.txtKode.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    
    }

    public void Simpan(FrmAnggota usr)throws SQLException {
         try {
            Connection con = Koneksi.getKoneksi();
            String sql = "insert kasir values (?,?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, usr.txtKode.getText());
            prepare.setString(2, usr.txtNama.getText());
            prepare.setString(3, usr.txtNoTelp.getText());
            prepare.setString(4, usr.txtAlamat.getText());
            prepare.setString(5, usr.txtUsername.getText());
            prepare.setString(6, usr.txtPassword.getText());
            prepare.setString(7, usr.cmblevel.getSelectedItem().toString());
            
            
            
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(usr);
            usr.setLebarKolom();
            Bersih(usr);
            AutoNomor(usr);
        }
    }

    public void Ubah(FrmAnggota usr) throws SQLException{
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "update kasir set Username = ?, "
                    + "Passwords = ?, "
                    + "Hak_Akses = ? "
                    + "Nama_Kasir = ? "
                    + "Telp = ? "
                    + "Alamat = ? "
                    + "Where Kd_Kasir = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, usr.txtNama.getText());
            prepare.setString(2, usr.txtNoTelp.getText());
            prepare.setString(3, usr.txtAlamat.getText());
            prepare.setString(4, usr.txtUsername.getText());
            prepare.setString(5, usr.txtPassword.getText());
            prepare.setString(6, usr.cmblevel.getSelectedItem().toString());
            prepare.setString(7, usr.txtKode.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(usr);
            usr.setLebarKolom();
            Bersih(usr);
            AutoNomor(usr);
        }
    }

    public void Hapus(FrmAnggota usr) throws SQLException{
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "delete from kasir where Kd_Kasir = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, usr.txtKode.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(usr);
            usr.setLebarKolom();
            Bersih(usr);
            AutoNomor(usr);
        }
    }

    public void KlikTabel(FrmAnggota usr) throws SQLException {
        try {
            int pilih = usr.tblDataAnggota.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            usr.txtKode.setText(usr.tbl.getValueAt(pilih, 0).toString());
            usr.txtNama.setText(usr.tbl.getValueAt(pilih, 1).toString());
            usr.txtNoTelp.setText(usr.tbl.getValueAt(pilih, 2).toString());
            usr.txtAlamat.setText(usr.tbl.getValueAt(pilih, 3).toString());
            usr.txtUsername.setText(usr.tbl.getValueAt(pilih, 4).toString());
            usr.txtPassword.setText(usr.tbl.getValueAt(pilih, 5).toString());
            usr.cmblevel.setSelectedItem(usr.tbl.getValueAt(pilih, 6).toString());
        } catch (Exception e) {
            
        }
    }

    @Override
    public void Tambah(FrmAnggota usr) throws SQLException {
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "insert kasir values (?,?,?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, usr.txtKode.getText());
            prepare.setString(2, usr.txtNama.getText());
            prepare.setString(3, usr.txtNoTelp.getText());
            prepare.setString(4, usr.txtAlamat.getText());
            prepare.setString(5, usr.txtUsername.getText());
            prepare.setString(6, usr.txtPassword.getText());
            prepare.setString(7, usr.cmblevel.getSelectedItem().toString());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tambah(usr);
            usr.setLebarKolom();
            Bersih(usr);
            AutoNomor(usr);
        }
    }
    
    @Override
    public void Tampil(FrmAnggota usr) throws SQLException {
        usr.tbl.getDataVector().removeAllElements();
        usr.tbl.fireTableDataChanged();
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select * from kasir order by Kd_Kasir asc";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[8];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                 ob[4] = rs.getString(5);
                  ob[5] = rs.getString(6);
                   ob[6] = rs.getString(7);
                usr.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public void Edit(FrmAnggota usr) throws SQLException {
         try {
            Connection con = Koneksi.getKoneksi();
            String sql = "update kasir set Username = ?, "
                    + "Password = ?, "
                    + "Hak_Akses = ? "
                    + "where Kd_Kasir = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, usr.txtKode.getText());
            prepare.setString(2, usr.txtPassword.getText());
            prepare.setString(3, usr.cmblevel.getSelectedItem().toString());
            prepare.setString(4, usr.txtKode.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(usr);
            usr.setLebarKolom();
            Bersih(usr);
            AutoNomor(usr);
        }
    }



    private void Bersih(FrmAnggota usr) {
        usr.txtKode.setText(null);
        usr.txtUsername.setText(null);
        usr.txtPassword.setText(null);
        usr.cmblevel.setSelectedItem("Level");
        usr.txtUsername.requestFocus();
        usr.txtNoTelp.setText(null);
        usr.txtNama.setText(null);
        
        
    }

    @Override
    public void Batal(FrmAnggota usr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}