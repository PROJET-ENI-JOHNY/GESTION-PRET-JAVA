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
public class effectif_par_banque {

    String designation;
    int taux;
    int effectif;
    int totale;
    double totale_payer;

    public effectif_par_banque(String designation, int taux, int effectif, int totale, double totale_payer) {
        this.designation = designation;
        this.taux = taux;
        this.effectif = effectif;
        this.totale = totale;
        this.totale_payer = totale_payer;
    }

    public effectif_par_banque() {
    }

    public String getDesignation() {
        return designation;
    }

    public int getTaux() {
        return taux;
    }

    public int getEffectif() {
        return effectif;
    }

    public int getTotale() {
        return totale;
    }

    public double getTotale_payer() {
        return totale_payer;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }

    public void setTotale_payer(double totale_payer) {
        this.totale_payer = totale_payer;
    }
    
    

}
