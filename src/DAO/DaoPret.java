/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CLASS.Banque;
import CLASS.Pret;
import CLASS.effectif_par_banque;
import CLASS.pret_par_banque;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author l'ombres
 */
public class DaoPret {

    private Connexion Con = new Connexion();

    private Pret pret;

    public DaoPret() {
    }

    public DaoPret(Pret pret) {
        this.pret = pret;
    }

    public Connexion getCon() {
        return Con;
    }

    public Pret getPret() {
        return pret;
    }

    public void setCon(Connexion Con) {
        this.Con = Con;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public ArrayList<Pret> Lister(String Condition) {
        ArrayList<Pret> ArrayResultat = new ArrayList<>();
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                if (Condition == null) {
                    Requete = "SELECT *from pret";
                } else {
                    Requete = "SELECT*from pret where " + Condition;
                }
                Statement stat = Con.con.createStatement();
                ResultSet Resultat = stat.executeQuery(Requete);
                if (Resultat != null) {
                    while (Resultat.next()) {
                        Pret pret = new Pret(Resultat.getInt(1), Resultat.getString(2), Resultat.getString(3), Resultat.getInt(4), Resultat.getString(5));
                        ArrayResultat.add(pret);
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
                Requete = String.format("INSERT INTO pret  VALUES (null, '%s', '%s', '%s','%s')", pret.getN_banque(), pret.getN_client(), pret.getMontant(), pret.getDate_pret());
                Statement Stat = Con.con.createStatement();
                Ajouter = Stat.executeUpdate(Requete);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {
                Con.FermerConnection();
                //   INSERT INTO `gestion_pret`.`pret` (`id_pret`, `n_banque`, `n_client`, `montant`, `date_pret`) VALUES (NULL, '12', 'C01', '2000', '2021-02-14');
            }
        }
        return Ajouter;
    }

    public ArrayList<pret_par_banque> Lister_par_banque(String Condition) {
        ArrayList<pret_par_banque> ArrayResultat = new ArrayList<>();
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                Requete = "SELECT nom, date_pret, montant , (montant+(montant/Taux)) from client, banque, pret where client.n_compte=pret.n_client and banque.n_banque=pret.n_banque and pret.n_banque =  " + Condition;

                Statement stat = Con.con.createStatement();
                ResultSet Resultat = stat.executeQuery(Requete);
                if (Resultat != null) {
                    while (Resultat.next()) {
                        pret_par_banque pret_banque = new pret_par_banque(Resultat.getString(1), Resultat.getString(2), Resultat.getInt(3), Resultat.getDouble(4));
                        ArrayResultat.add(pret_banque);
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

    public int get_taux(String id_banque) {
        String Requete;
        int taux = 0;
        if (Con.OuvrirConnection() == 1) {
            try {
                Requete = "SELECT Taux from banque where n_banque = " + id_banque;
                Statement stat = Con.con.createStatement();
                ResultSet Resultat = stat.executeQuery(Requete);
                if (Resultat != null) {
                    while (Resultat.next()) {
                        taux = Resultat.getInt(1);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {
                Con.FermerConnection();
            }
        }
        return taux;
    }

    public ArrayList<effectif_par_banque> Lister_effectif_par_banque(String Condition) {
        ArrayList<effectif_par_banque> ArrayResultat = new ArrayList<>();
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                if (Condition == null) {
                    Requete = "SELECT designation, taux, COUNT(id_pret) ,SUM(montant),SUM(montant+(montant/Taux)) from client, banque, pret where client.n_compte=pret.n_client and banque.n_banque=pret.n_banque GROUP by designation";

                } else {
                    Requete = "SELECT designation, taux, COUNT(id_pret) ,SUM(montant),SUM(montant+(montant/Taux)) from client, banque, pret where client.n_compte=pret.n_client and banque.n_banque=pret.n_banque " + Condition + " GROUP by designation";

                }
                System.out.println(Requete);
                Statement stat = Con.con.createStatement();
                ResultSet Resultat = stat.executeQuery(Requete);
                if (Resultat != null) {
                    while (Resultat.next()) {
                        effectif_par_banque effectif = new effectif_par_banque(Resultat.getString(1), Resultat.getInt(2), Resultat.getInt(3), Resultat.getInt(4), Resultat.getDouble(5));
                        ArrayResultat.add(effectif);
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

}
