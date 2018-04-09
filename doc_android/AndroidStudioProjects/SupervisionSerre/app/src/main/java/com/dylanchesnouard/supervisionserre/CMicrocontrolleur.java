package com.dylanchesnouard.supervisionserre;

public class CMicrocontrolleur {

    // ATTRIBUTES
    private int id;
    private String nom;
    private boolean estFonctionnel;

    // CONSTRUCTOR
    public CMicrocontrolleur(int _id, String _nom, boolean _estFonctionnel) {
        id = _id;
        nom = _nom;
        estFonctionnel = _estFonctionnel;
    }

    // GETTERS & SETTERS
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public boolean getEstFonctionnel() {
        return estFonctionnel;
    }
    public void setEstFonctionnel(boolean estFonctionnel) {
        this.estFonctionnel = estFonctionnel;
    }

}
