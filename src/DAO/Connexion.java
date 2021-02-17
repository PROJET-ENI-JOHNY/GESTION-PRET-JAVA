/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author LINO
 */
public class Connexion {

    public Connection con;

    public Connexion() {
    }

    public int OuvrirConnection() {
        int Connecter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/gestion_pret", "root", "");
            Connecter = 1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("DAO.Connexion.OuvrirConnection()");
        }
        return Connecter;
    }

    public void FermerConnection() {
        try {
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
