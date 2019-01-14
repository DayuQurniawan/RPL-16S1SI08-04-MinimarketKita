package model;

import controller.controller_suplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.Koneksi;
import view.FrmSuplier;

/**
 *
 * @author tamasyake
 */
public class model_suplier implements controller_suplier{

    public void Tampil(FrmSuplier spl)throws SQLException {
        spl.tbl.getDataVector().removeAllElements();
        spl.tbl.fireTableDataChanged();
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select * from supplier order by Kd_Sup asc";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[8];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
              
                spl.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AutoNomor(FrmSuplier spl) throws SQLException{
        try {
            Connection con = Koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "select max(Kd_Sup) from supplier";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                spl.txtKdSuplier.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    
    }

    public void Edit(FrmSuplier spl) throws SQLException {
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "update supplier set Nama_Sup = ?, "
                    + "Alamat_Sup = ?, "
                    + "Telp_Sup = ?, "    
                    + "where Kd_Sup = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, spl.txtNama.getText());
            prepare.setString(2, spl.txtAlamat.getText());
            prepare.setString(3, spl.txtNoTelp.getText());
            
            prepare.setString(6, spl.txtKdSuplier.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(spl);
            spl.setLebarKolom();
            Bersih(spl);
            AutoNomor(spl);
        }
    }
    

    public void Tambah(FrmSuplier spl) throws SQLException{
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "insert supplier values (?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, spl.txtKdSuplier.getText());
            prepare.setString(2, spl.txtNama.getText());
            prepare.setString(3, spl.txtAlamat.getText());
            prepare.setString(4, spl.txtNoTelp.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            prepare.close();
        }catch (Exception e){
            System.out.println(e);
    }finally {
            Tampil(spl);
            spl.setLebarKolom();
            Bersih(spl);
            AutoNomor(spl);
        }
    }
    
    
    public void Hapus(FrmSuplier spl) throws SQLException{
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "delete from supplier where Kd_Sup = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, spl.txtKdSuplier.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(spl);
            spl.setLebarKolom();
            Bersih(spl);
            AutoNomor(spl);
        }
    }

    public void KlikTabel(FrmSuplier spl) throws SQLException{
        try {
            int pilih = spl.tblSuplier.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            spl.txtKdSuplier.setText(spl.tbl.getValueAt(pilih, 0).toString());
            spl.txtNama.setText(spl.tbl.getValueAt(pilih, 1).toString());
            spl.txtAlamat.setText(spl.tbl.getValueAt(pilih, 2).toString());
            spl.txtNoTelp.setText(spl.tbl.getValueAt(pilih, 3).toString());
            
        } catch (Exception e) {
            
        }
    }

    public void Bersih(FrmSuplier spl) throws SQLException{
        spl.txtKdSuplier.setText(null);
        spl.txtNama.setText(null);
        spl.txtAlamat.setText(null);
        spl.txtNoTelp.setText(null);
       
        spl.txtNama.requestFocus();
    }

    @Override
    public void Simpan(FrmSuplier spl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Ubah(FrmSuplier spl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
