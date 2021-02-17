/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CLASS.Banque;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author l'ombres
 */
public class DaoBanque {

    private Connexion Con = new Connexion();

    private Banque banque;

    public DaoBanque() {
    }

    public DaoBanque(Banque banque) {
        this.banque = banque;
    }

    public Connexion getCon() {
        return Con;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setCon(Connexion Con) {
        this.Con = Con;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }
    
    public ArrayList<Banque> Lister(String Condition) {
        ArrayList<Banque> ArrayResultat = new ArrayList<>();
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                if (Condition == null) {
                    Requete = "SELECT *from banque";
                } else {
                    Requete = "SELECT*from banque where " + Condition;
                }
                Statement stat = Con.con.createStatement();
                ResultSet Resultat = stat.executeQuery(Requete);
                if (Resultat != null) {
                    while (Resultat.next()) {
                        Banque banque = new Banque(Resultat.getString(1), Resultat.getString(2), Resultat.getInt(3));
                        ArrayResultat.add(banque);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {
                Con.FermerConnection();
            }
        }
        return ArrayResultat;
    }
    
    
     public int Ajouter() {
        int Ajouter = 0;
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                Requete = String.format("INSERT INTO banque  VALUES ('%s', '%s', '%s')", banque.getN_banque(), banque.getDesignation(), banque.getTaux());
                Statement Stat = Con.con.createStatement();
                Ajouter = Stat.executeUpdate(Requete);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {
                Con.FermerConnection();
            }
        }
        return Ajouter;
    }
     
     public int Editer(Banque banque) {
        int Editer = 0;
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                Requete = String.format("UPDATE banque set designation='%s', taux='%s' where n_banque='%s' ", banque.getDesignation(), banque.getTaux(), banque.getN_banque());
                Statement Stat = Con.con.createStatement();
                Editer = Stat.executeUpdate(Requete);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {
                Con.FermerConnection();
            }
        }
        return Editer;
    }
    
     public int Supprimer() {
        int Supprimer = 0;
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                Requete = String.format("DELETE from  banque where n_banque='%s' ", banque.getN_banque());
                Statement Stat = Con.con.createStatement();
                Supprimer = Stat.executeUpdate(Requete);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, ">>>" + e.getMessage());
            } finally {
                Con.FermerConnection();
            }
        }
        return Supprimer;
    }
     
    

}
