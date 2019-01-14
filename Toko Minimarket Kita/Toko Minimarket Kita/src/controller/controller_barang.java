/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.FrmBarang;

/**
 *
 * @author tamasyake
 */
public interface controller_barang {
    public void Simpan(FrmBarang brg) throws SQLException;
    public void Ubah (FrmBarang brg) throws SQLException;
    public void Hapus(FrmBarang brg) throws SQLException;
    public void Batal (FrmBarang brg) throws SQLException;
    public void KlikTabel (FrmBarang brg) throws SQLException;
    public void Combo (FrmBarang brg) throws SQLException;

}
