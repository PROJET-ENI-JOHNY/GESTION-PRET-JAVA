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
public class pret_par_banque {
    String nom;
    String date_pret;
    int montant;
    double montant_payer;

    public pret_par_banque(String nom, String date_pret, int montant, double montant_payer) {
        this.nom = nom;
        this.date_pret = date_pret;
        this.montant = montant;
        this.montant_payer = montant_payer;
    }

    public pret_par_banque() {
    }

    public String getNom() {
        return nom;
    }

    public String getDate_pret() {
        return date_pret;
    }

    public int getMontant() {
        return montant;
    }

    public double getMontant_payer() {
        return montant_payer;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate_pret(String date_pret) {
        this.date_pret = date_pret;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public void setMontant_payer(double montant_payer) {
        this.montant_payer = montant_payer;
    }
    
    
}
