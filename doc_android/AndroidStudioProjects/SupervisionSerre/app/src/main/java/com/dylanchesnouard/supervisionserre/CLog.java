package com.dylanchesnouard.supervisionserre;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CLog {

    private int id;
    private boolean estFonctionnel;
    private Date date_panne;
    private String nom_capteur;

    public CLog(int _id, boolean _estFonctionnel, String _date_panne, String _nom_capteur) {
        id = _id;
        estFonctionnel = _estFonctionnel;
        nom_capteur = _nom_capteur;
        date_panne = stringToDate(_date_panne);

        Log.i("CustomLog","[CLog] Nouveau log, date = " + _date_panne);
    }

    // GETTERS & SETTERS
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isEstFonctionnel() {
        return estFonctionnel;
    }
    public void setEstFonctionnel(boolean estFonctionnel) {
        this.estFonctionnel = estFonctionnel;
    }
    public Date getDate_panne() {
        return date_panne;
    }

    public void setDate_panne(Date date_panne) {
        this.date_panne = date_panne;
    }
    public String getNom_capteur() {
        return nom_capteur;
    }
    public void setNom_capteur(String nom_capteur) {
        this.nom_capteur = nom_capteur;
    }
    private Date stringToDate(String _date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
