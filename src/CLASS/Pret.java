/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASS;

/**
 *
 * @author l'ombres
 */
public class Pret {
    
    int id_pret;
    String n_banque;
    String n_client;
    int montant;
    String date_pret;

    public Pret(int id_pret, String n_banque, String n_client, int montant, String date_pret) {
        this.id_pret = id_pret;
        this.n_banque = n_banque;
        this.n_client = n_client;
        this.montant = montant;
        this.date_pret = date_pret;
    }

    public Pret() {
    }

    public int getId_pret() {
        return id_pret;
    }

    public String getN_banque() {
        return n_banque;
    }

    public String getN_client() {
        return n_client;
    }

    public int getMontant() {
        return montant;
    }

    public String getDate_pret() {
        return date_pret;
    }

    public void setId_pret(int id_pret) {
        this.id_pret = id_pret;
    }

    public void setN_banque(String n_banque) {
        this.n_banque = n_banque;
    }

    public void setN_client(String n_client) {
        this.n_client = n_client;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public void setDate_pret(String date_pret) {
        this.date_pret = date_pret;
    }
    
    
    
    
}
