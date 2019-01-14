package model;

import controller.controller_barang;
import koneksi.Koneksi;
import view.FrmBarang;
import java.sql.*;
import java.sql.SQLException;    
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import view.FrmAnggota;
/**
 *
 * @author tamasyake
 */
public class model_barang implements controller_barang {
    public void Simpan(FrmBarang brg) throws SQLException {
        try {
            Connection con;
            con = Koneksi.getKoneksi();
            String sql = "insert barang values(?,?,?,?,?,?,?)";
            try (PreparedStatement prepare = con.prepareStatement(sql)) {
                prepare.setString(1, brg.txtkode_barang.getText());
                prepare.setString(2, brg.txtnama.getText());
                prepare.setString(3, (String) brg.cmbSatuan.getSelectedItem());
                prepare.setString(4, brg.txthargaBeli.getText());
                prepare.setString(5, brg.txtHargaJual.getText());
                prepare.setString(6, brg.txtStok.getText());
                prepare.setString(7, brg.txtKdSup.getText());
                
                
                prepare.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(brg);
            brg.setLebarKolom();
            Bersih(brg);
        } 
    }
    
    public void AutoNomor(FrmBarang usr)throws SQLException {
         try {
            Connection con = Koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "select max(Kd_Barang) from barang";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                usr.txtkode_barang.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    
    }

    
public void Ubah(FrmBarang brg) throws SQLException {
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "update barang set Nama_Barang = ?, "
                    + "Satuan = ?, "
                    + "Harga_Beli = ?, "
                    + "Harga_Jual = ?, "
                    + "Stok_Barang = ?, "
                    + "Kd_Sup = ?, "
                    + "where Kd_Barang = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, brg.txtnama.getText());
            prepare.setString(2, brg.txthargaBeli.getText());
            prepare.setString(3, brg.txtkode_barang.getText());
            
            
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(brg);
            brg.setLebarKolom();
            Bersih(brg);
        }
    }
    
   
public void Hapus(FrmBarang brg) throws SQLException {
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "delete from barang where Kd_Barang = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, brg.txtkode_barang.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasi Dihapus");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(brg);
            brg.setLebarKolom();
            Bersih(brg);
        }
    }

    
    
    
    public void Tampil(FrmBarang brg)throws SQLException {
        brg.tbl.getDataVector().removeAllElements();
        brg.tbl.fireTableDataChanged();
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select * from barang order by Kd_Barang asc";
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
                
                brg.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    
//To change body of generated methods, choose Tools | Templates.
    }

    public void Bersih(FrmBarang brg) {
        brg.txtkode_barang.setText(null);
        brg.txtnama.setText(null);
        brg.txtHargaJual.setText(null);
        brg.txtStok.setText(null);
        brg.txthargaBeli.setText(null);
        brg.txtkode_barang.requestFocus();
        brg.txtKdSup.setText(null);
    }
    public void Combo(FrmBarang brg)throws SQLException {
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select Nama_Barang";
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] ob = new Object[3];
                ob[1] = rs.getString(1);

                brg.cmbSatuan.addItem((String) ob[1]);                                    
            }
            rs.close(); st.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public void KlikTabel(FrmBarang brg)throws SQLException {
       try {
            int pilih = brg.tblBarang.getSelectedRow();
            String s = (String)brg.tblBarang.getModel().getValueAt(pilih, 4);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-M-dd");
            Date d = f.parse(s);
            if (pilih == -1) {
                return; 
            }
            brg.txtkode_barang.setText(brg.tbl.getValueAt(pilih, 0).toString());
            brg.txtnama.setText(brg.tbl.getValueAt(pilih, 1).toString());
          
            brg.txthargaBeli.setText(brg.tbl.getValueAt(pilih, 3).toString());
            
        } catch (Exception e) {
        }
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
           String sql = null;
            
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] ob = new Object[3];
                ob[1] = rs.getString(1);

                brg.cmbSatuan.setSelectedItem(rs.getString("nama"));
            }
            rs.close(); st.close();
        } catch (Exception e) {
        }
    }

    public void Combo2(FrmBarang aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Batal(FrmBarang brg) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

      
    
   
    

   

