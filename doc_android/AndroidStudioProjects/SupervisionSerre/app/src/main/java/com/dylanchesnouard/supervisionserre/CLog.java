package com.dylanchesnouard.supervisionserre;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CLog {

    private int id;
    private boolean estFonctionnel;
    private String jour_panne;
    private String heure_panne;
    private Date date_panne;
    private String nom_capteur;

    public CLog(int _id, boolean _estFonctionnel, String _date_panne, String _nom_capteur) {
        id = _id;
        estFonctionnel = _estFonctionnel;
        Log.i("CustomLog","DATE CONVERT");
        date_panne = stringToDate(_date_panne);
        Log.i("CustomLog","DATE INCOMMING");
        Log.i("CustomLog","DATE : " + date_panne.toString());
        nom_capteur = _nom_capteur;
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
    public String getJourPanne() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String jourPanne = dateFormat.format(date_panne);
        return jourPanne;
    }
    public String getHeurePanne() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String heurePanne = dateFormat.format(date_panne);
        return heurePanne;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(_date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
