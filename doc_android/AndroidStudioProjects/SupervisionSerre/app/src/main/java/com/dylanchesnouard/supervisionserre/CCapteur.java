package com.dylanchesnouard.supervisionserre;

public class CCapteur {

    // ATTRIBUTES
    private int id;
    private String nom;
    private boolean etat;

    // CONSTRUCTOR
    public CCapteur(int _id, String _nom, boolean _etat) {
        id = _id;
        nom = _nom;
        etat = _etat;
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
    public boolean isEtat() {
        return etat;
    }
    public void setEtat(boolean etat) {
        this.etat = etat;
    }

}
