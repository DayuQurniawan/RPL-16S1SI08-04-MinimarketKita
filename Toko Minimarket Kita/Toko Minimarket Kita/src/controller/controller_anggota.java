/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.FrmAnggota;

/**
 *
 * @author tamasyake
 */
public interface controller_anggota {
    public void Tambah (FrmAnggota usr) throws SQLException;
    public void Edit (FrmAnggota usr) throws SQLException;
    public void Hapus (FrmAnggota usr) throws SQLException;
    public void Tampil (FrmAnggota usr) throws SQLException;
    public void Batal (FrmAnggota usr) throws SQLException;
    public void KlikTabel (FrmAnggota usr) throws SQLException;
    public void AutoNomor (FrmAnggota usr) throws SQLException;
}
