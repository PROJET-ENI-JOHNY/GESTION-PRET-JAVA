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
public class Banque {

    String n_banque;
    String designation;
    int Taux;

    public Banque(String n_banque, String designation, int Taux) {
        this.n_banque = n_banque;
        this.designation = designation;
        this.Taux = Taux;
    }

    public Banque() {
    }

    public String getN_banque() {
        return n_banque;
    }

    public String getDesignation() {
        return designation;
    }

    public double getTaux() {
        return Taux;
    }

    public void setN_banque(String n_banque) {
        this.n_banque = n_banque;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setTaux(int Taux) {
        this.Taux = Taux;
    }
    
    

}
