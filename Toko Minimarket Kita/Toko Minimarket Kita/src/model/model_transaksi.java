
package model;
import controller.controller_transaksi;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.TableModel;
import koneksi.Koneksi;
import view.FrmTransaksi;

/**
 *
 * @author tamasyake
 */
public class model_transaksi implements controller_transaksi{
    
 Map<String, Object> parameter = new HashMap<String, Object>();
 
    public void Simpan(FrmTransaksi ksr) throws SQLException{
         try {
            Connection con = Koneksi.getKoneksi();
            String sql = "insert penjualan values (?,?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, ksr.txtTanggal.getText());
            prepare.setString(2, ksr.txtKdBarang.getText());
            prepare.setString(3, ksr.txtNamaBarang.getText());
            prepare.setString(4, ksr.txtQty.getText());
            prepare.setString(5, ksr.txtHarga.getText());
            prepare.setString(6, ksr.txtIdTransaksi.getText());
            prepare.executeUpdate();
            
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        }
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "insert Kd_Nota values (?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, ksr.txtIdTransaksi.getText());
            prepare.setString(2, ksr.txtKdBarang.getText());
            prepare.executeUpdate();
           
            prepare.close();
        } catch (Exception e) {
        }
        finally {
            Tampil(ksr);
            AutoNomor(ksr);
            Total(ksr);
            Bersih(ksr);
            ksr.setLebarKolom();
        }
    }

    public void ScanBarcode(FrmTransaksi ksr) throws SQLException{
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select * from barang where Kd_Barang = '"+ksr.txtKdBarang.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ksr.txtNamaBarang.setText(rs.getString("Nama_Barang"));
                ksr.txtHarga.setText(rs.getString("Harga_Jual"));
                ksr.txtQty.setText("1");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            ksr.txtHarga.requestFocus();
        }
    }

    public void Kembalian(FrmTransaksi ksr)throws SQLException {
        double total = Double.valueOf(ksr.txtTotalBayar.getText());
        double bayar = Double.valueOf(ksr.txtBayar.getText());
        double kembalian = (bayar-total);
        ksr.txtKembali.setText(String.valueOf(kembalian));
        ksr.txtTotalBayar.setText("-" +String.valueOf(kembalian));
        
        
    }

    public void CetakStruk(FrmTransaksi ksr)throws SQLException {
        try {
            parameter.put("Kd_Nota", ksr.txtIdTransaksi.getText().toString());
            parameter.put("total", ksr.txtTotalBayar.getText());
            parameter.put("bayar", ksr.txtBayar.getText());
            parameter.put("kembali", ksr.txtKembali.getText());
            File file = new File("src/com/report/struk.jrxml");
           
            //JasperPrintManager.printReport(jasperPrint, false);
        } catch (Exception e) {
        }
    }

    public void KlikTabel(FrmTransaksi ksr)throws SQLException {
        try {
            int pilih = ksr.tblTransaksi.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            ksr.txtIdTransaksi.setText(ksr.tbl.getValueAt(pilih, 0).toString());
            ksr.txtKdBarang.setText(ksr.tbl.getValueAt(pilih, 1).toString());
            ksr.txtNamaBarang.setText(ksr.tbl.getValueAt(pilih, 2).toString());
            ksr.txtHarga.setText(ksr.tbl.getValueAt(pilih, 3).toString());
            ksr.txtQty.setText(ksr.tbl.getValueAt(pilih, 4).toString());
        } catch (Exception e) {
        } finally {
            ksr.txtQty.requestFocus();
            ksr.txtQty.setText(null);
        }
    }

    public void Hapus(FrmTransaksi ksr) throws SQLException {
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "delete from penjualan where Kd_Nota = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, ksr.txtIdTransaksi.getText());
            prepare.executeUpdate();
            
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(ksr);
            AutoNomor(ksr);
            Bersih(ksr);
            ksr.setLebarKolom();
            Total(ksr);
        }
    }

    public void Tampil(FrmTransaksi ksr) throws SQLException{
        ksr.tbl.getDataVector().removeAllElements();
        ksr.tbl.fireTableDataChanged();
        try {
            Connection con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select a.IdTransaksi, c.kode_barang, c.nama, c.harga, a.jumlah_barang, a.harga_barang "
                    + "from transaksi a join detail_transaksi b on b.id_transaksi=a.id_transaksi "
                    + "join barang c on c.kode_barang=b.kode_barang "
                    + "where a.kode_transaksi='"+ksr.txtIdTransaksi.getText()+"' order by a.id_transaksi asc";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[8];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                ob[5] = rs.getString(6);
                ksr.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AutoNomor(FrmTransaksi ksr) throws SQLException{
        try {
            Connection con = Koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "select max(Kd_Nota) from penjualan";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                ksr.txtIdTransaksi.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    }

    public void Total(FrmTransaksi ksr) {
       int JumlahBaris = ksr.tblTransaksi.getRowCount();
        int Total = 0;
        int Harga_Barang;
        TableModel tableModel;
        tableModel = ksr.tblTransaksi.getModel();
        for (int i = 0; i < JumlahBaris; i++) {
            Harga_Barang = Integer.parseInt(tableModel.getValueAt(i, 5).toString());
            Total = (Total + Harga_Barang);
            ksr.txtTotalBayar.setText(String.valueOf(Total));
            
        }
    }

    public void Bersih(FrmTransaksi ksr) throws SQLException{
        ksr.txtKdBarang.setText(null);
        ksr.txtNamaBarang.setText(null);
        ksr.txtHarga.setText(null);
        ksr.txtQty.setText(null);
        ksr.txtKdBarang.requestFocus();
    }

    @Override
    public void Ubah(FrmTransaksi ksr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Tanggal(FrmTransaksi ksr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AutoNomorKode(FrmTransaksi ksr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void UbahJumlah(FrmTransaksi ksr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
