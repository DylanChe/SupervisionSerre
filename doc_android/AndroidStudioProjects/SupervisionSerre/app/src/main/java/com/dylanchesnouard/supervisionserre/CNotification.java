package com.dylanchesnouard.supervisionserre;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public final class CNotification {

    /**
     * Affiche une fenêtre sur la vue "context" afin d'afficher un message prédéfini en fonction de "typeErreur".
     * La fenêtre possède un bouton "Ok" afin de la fermer.
     * @param context
     * @param typeErreur
     */
    public static void AfficherErreur(Context context, int typeErreur) {
        AlertDialog.Builder dlg_connexionBdd = new AlertDialog.Builder(context);
        dlg_connexionBdd.setTitle("Erreur");
        switch (typeErreur) {
            case 1: dlg_connexionBdd.setMessage("Les itinérances de données sont désactivées.\nVeuillez les activées.");
                break;
            case 2: dlg_connexionBdd.setMessage("Impossible d'établir une connexion avec la base de données.\nVeuillez vérifier que l'adresse du serveur est correcte.");
                break;
            case 3: dlg_connexionBdd.setMessage("Veuillez saisir une adresse IP valide.");
                break;
            default: dlg_connexionBdd.setMessage("Erreur.");
        }
        dlg_connexionBdd.setCancelable(true);
        dlg_connexionBdd.setNeutralButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert_connexionBdd = dlg_connexionBdd.create();
        alert_connexionBdd.show();
    }

    /**
     * Affiche une fenêtre sur la vue "context" afin d'afficher un message "message" avec pour titre "titre".
     * La fenêtre possède un bouton "Ok" afin de la fermer.
     * @param context
     * @param titre
     * @param message
     */
    public static void AfficherMessage(Context context, String titre, String message) {
        AlertDialog.Builder dlg_connexionBdd = new AlertDialog.Builder(context);
        dlg_connexionBdd.setTitle(titre);
        dlg_connexionBdd.setMessage(message);
        dlg_connexionBdd.setCancelable(true);
        dlg_connexionBdd.setNeutralButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert_connexionBdd = dlg_connexionBdd.create();
        alert_connexionBdd.show();
    }

    /**
     * Affiche un Toast sur la vue "context" afin d'afficher le message "texte" durant une durée en fonction de "duree".
     * @param context
     * @param texte
     * @param duree
     */
    public static void AfficherToast(Context context, String texte, int duree) {

        switch (duree) {
            case 1: Toast.makeText(context, texte, Toast.LENGTH_SHORT).show();
                break;
            case 2: Toast.makeText(context, texte, Toast.LENGTH_LONG).show();
                break;
        }
    }

}
