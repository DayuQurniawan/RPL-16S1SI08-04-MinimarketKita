
package model;

import controller.controller_pembelian;
import view.FrmPembelian;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.Koneksi;

/**
 *
 * @author tamasyake
 */
public class model_pembelian implements controller_pembelian {

    public void Tampil(FrmPembelian pmbl) throws SQLException{
        pmbl.tbl.getDataVector().removeAllElements();
        pmbl.tbl.fireTableDataChanged();
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
           String sql = "select * from pembelian order by Kd_Pembelian asc";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[8];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                pmbl.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AutoNomor(FrmPembelian pmbl)throws SQLException {
         try {
            Connection con = Koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "select max(Kd_Pembelian) from pembelian";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                pmbl.txtid_pembelian.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    }

    public void Combo1(FrmPembelian pmbl)throws SQLException {
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select Kd_Pembelian from pembelian";
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] ob = new Object[3];
                ob[1] = rs.getString(1);

                                               
            }
            rs.close(); st.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public void Combo2(FrmPembelian pmbl) throws SQLException{
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select Nama_Sup from supplier";
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] ob = new Object[3];
                ob[1] = rs.getString(1);

                pmbl.cmbSuplier.addItem((String) ob[1]);                                    
            }
            rs.close(); st.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public void Combo3(FrmPembelian pmbl)throws SQLException {
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = null;
            
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] ob = new Object[3];
                ob[1] = rs.getString(1);

              
            }
            rs.close(); st.close();
        } catch (Exception e) {
        }
    }

    public void Combo4(FrmPembelian aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Hapus(FrmPembelian pmbl) throws SQLException{
         try {
            Connection con = Koneksi.getKoneksi();
            String sql = "delete from pembelian where Kd_Pembelian = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, pmbl.txtid_pembelian.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasi Dihapus");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(pmbl);
            pmbl.setLebarKolom();
            Bersih(pmbl);
            AutoNomor(pmbl);
        }
    }

    public void Simpan(FrmPembelian aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void KlikTabel(FrmPembelian pmbl)throws SQLException {
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "insert pembelian values(?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, pmbl.txtid_pembelian.getText());
            prepare.setString(3, pmbl.txtid_suplier.getText());
            prepare.setString(4, pmbl.txtTanggal.getText());
            prepare.setString(5, pmbl.txtJumlah.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(pmbl);
            pmbl.setLebarKolom();
            Bersih(pmbl);
            AutoNomor(pmbl);
        } 
    }

   
    
    

    @Override
    public void Bersih(FrmPembelian pmbl)throws SQLException {
        pmbl.txtid_pembelian.setText(null);
        pmbl.cmbSuplier.setSelectedItem("Suplier");
        pmbl.txtTanggal.setText(null);
        pmbl.txtJumlah.setText(null);
    }
    
}
