package com.dylanchesnouard.supervisiondeserre;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public final class CNotification {

    /**
     * Affiche une fenêtre sur la vue "context" afin d'afficher un message prédéfini en fonction de "errorType".
     * La fenêtre possède un bouton "Ok" afin de la fermer.
     * @param context
     * @param errorType
     */
    public static void displayError(Context context, int errorType) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Erreur");
        switch (errorType) {
            case 1: alert.setMessage("Les itinérances de données sont désactivées.\nVeuillez les activées.");
                break;
            case 2: alert.setMessage("Impossible d'établir une connexion avec la base de données.\nVeuillez vérifier que l'adresse du serveur est correcte.");
                break;
            case 3: alert.setMessage("Veuillez saisir une adresse IP valide.");
                break;
            default: alert.setMessage("Erreur.");
        }
        alert.setCancelable(true);
        alert.setNeutralButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert_connexionBdd = alert.create();
        alert_connexionBdd.show();
    }

    /**
     * Affiche un Toast sur la vue "context" afin d'afficher le message "texte" durant une durée en fonction de "duree".
     * @param context
     * @param message
     * @param duration
     */
    public static void displayToast(Context context, String message, int duration) {

        switch (duration) {
            case 1: Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                break;
            case 2: Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
