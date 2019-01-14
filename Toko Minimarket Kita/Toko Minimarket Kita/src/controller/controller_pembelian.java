/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.FrmPembelian;

/**
 *
 * @author tamasyake
 */
public interface controller_pembelian {
    public void Simpan (FrmPembelian pmbl) throws SQLException;
    public void Hapus (FrmPembelian pmbl) throws SQLException;
    public void Tampil (FrmPembelian pmbl) throws SQLException;
    public void KlikTabel (FrmPembelian pmbl) throws SQLException;
    public void Bersih (FrmPembelian pmbl) throws SQLException;
    public void Combo1 (FrmPembelian pmbl) throws SQLException;
    public void Combo2 (FrmPembelian pmbl) throws SQLException;
    public void Combo3 (FrmPembelian pmbl) throws SQLException;
    public void Combo4 (FrmPembelian pmbl) throws SQLException;
    public void AutoNomor (FrmPembelian pmbl) throws SQLException;  
}
