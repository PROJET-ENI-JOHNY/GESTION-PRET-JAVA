/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CLASS.Client;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author l'ombres
 */
public class DaoClient {

    private Connexion Con = new Connexion();
    
    private Client client;

    public DaoClient() {
    }

    public DaoClient(Client client) {
        this.client = client;
    }

    public Connexion getCon() {
        return Con;
    }

    public Client getClient() {
        return client;
    }

    public void setCon(Connexion Con) {
        this.Con = Con;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public ArrayList<Client> ListerClient(String Condition) {
        ArrayList<Client> ArrayResultat = new ArrayList<>();
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                if (Condition == null) {
                    Requete = "SELECT *from client";
                } else {
                    Requete = "SELECT*from client where " + Condition;
                }
                Statement stat = Con.con.createStatement();
                ResultSet Resultat = stat.executeQuery(Requete);
                if (Resultat != null) {
                    while (Resultat.next()) {
                        Client client = new Client(Resultat.getString(1), Resultat.getString(2), Resultat.getString(3));
                        ArrayResultat.add(client);
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
    
     public int AjouterClient() {
        int Ajouter = 0;
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                Requete = String.format("INSERT INTO client  VALUES ('%s', '%s', '%s')", client.getN_compte(), client.getNom(), client.getAdresse());
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
     
    public int EditerClient(Client client) {
        int Editer = 0;
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                Requete = String.format("UPDATE client set nom='%s', adresse='%s' where n_compte='%s' ", client.getNom(), client.getAdresse(), client.getN_compte());
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
    
     public int SupprimerClient() {
        int Supprimer = 0;
        String Requete;
        if (Con.OuvrirConnection() == 1) {
            try {
                Requete = String.format("DELETE from  client where N_compte='%s' ", client.getN_compte());
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
     
     
    public Client GetOneClient(String Condition) {
        String Requete;
        Client client = null;
        if (Con.OuvrirConnection() == 1) {
            try {
                Requete = "SELECT*from client where " + Condition;
                Statement stat = Con.con.createStatement();
                ResultSet Resultat = stat.executeQuery(Requete);
                if (Resultat != null) {
                    while (Resultat.next()) {
                        client = new Client(Resultat.getString(1), Resultat.getString(2), Resultat.getString(3));
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {
                Con.FermerConnection();
            }
        }
        return client;
    }
}
