/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
    import view.FrmLogin;
    import java.sql.SQLException;
/**
 *
 * @author tamasyake
 */
public interface controller_login {
     public void Login (FrmLogin lgn) throws SQLException;
     public void Batal (FrmLogin lgn) throws SQLException;
}
