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
public class Client {
    String n_compte;
    String nom;
    String adresse;

    public Client(String n_compte, String nom, String adresse) {
        this.n_compte = n_compte;
        this.nom = nom;
        this.adresse = adresse;
    }

    public Client() {
    }
    
    

    public String getN_compte() {
        return n_compte;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setN_compte(String n_compte) {
        this.n_compte = n_compte;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
}
