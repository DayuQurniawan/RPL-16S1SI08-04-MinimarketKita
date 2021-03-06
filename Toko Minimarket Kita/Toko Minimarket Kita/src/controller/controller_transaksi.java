
package controller;

import java.sql.SQLException;
import view.FrmTransaksi;

/**
 *
 * @author tamasyake
 */
public interface controller_transaksi {
   public void Simpan (FrmTransaksi ksr) throws SQLException;
    public void Ubah (FrmTransaksi ksr) throws SQLException;
    public void Hapus (FrmTransaksi ksr) throws SQLException;
    public void Tampil (FrmTransaksi ksr) throws SQLException;
    public void KlikTabel (FrmTransaksi ksr) throws SQLException;
    public void Bersih (FrmTransaksi ksr) throws SQLException;
    public void Tanggal (FrmTransaksi ksr) throws SQLException;
    public void ScanBarcode (FrmTransaksi ksr) throws SQLException;
    public void AutoNomor (FrmTransaksi ksr) throws SQLException;
    public void AutoNomorKode (FrmTransaksi ksr) throws SQLException;
    public void Total (FrmTransaksi ksr) throws SQLException;
    public void Kembalian (FrmTransaksi ksr) throws SQLException;
    public void UbahJumlah (FrmTransaksi ksr) throws SQLException;
    public void CetakStruk (FrmTransaksi ksr) throws SQLException; 
}
