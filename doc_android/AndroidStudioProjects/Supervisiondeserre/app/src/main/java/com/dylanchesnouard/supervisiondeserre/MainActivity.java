package com.dylanchesnouard.supervisiondeserre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // ATTRIBUTES
    private Button main_bt_connect;
    private EditText main_txt_serverIp;

    /**
     * Méthode appelée à la création de la fenêtre.
     * Permet de lier les attributs et les composants (boutons...) ainsi que d'ajouter les listeners.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FIND CONTROLS & SET LISTENERS
        main_bt_connect = findViewById(R.id.main_bt_connect);
        main_bt_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnectionPossible()) {
                    openDashboardActivity();
                }
            }
        });
        main_txt_serverIp = findViewById(R.id.main_txt_serverIp);
    }

    /**
     * Fonction permettant de tester la connexion avec le serveur.
     * Retourne 'true' si la connexion avec le serveur fonctionne, sinon 'false'.
     */
    private boolean isConnectionPossible() {
        CConnection.setIp(main_txt_serverIp.getText().toString());
        if (CConnection.getIp().length() == 0) {
            CNotification.displayError(this,3);
        } else {
            try {
                if (!CConnection.isInternetActivated(this)) { // Vérifie que les itinérances de données sont activées
                    CNotification.displayError(this, 1);
                } else if (!CConnection.isConnectionWorking()) { // Vérifie que la connexion avec le serveur fonctionne
                    CNotification.displayError(this, 2);
                } else { // Aucun problème, donc la connexion fonctionne
                    return true;
                }
            } catch (Exception e) {
                CNotification.displayError(this, 0);
                Log.e("CustomLog", e.getMessage());
            }
        }
        return false;
    }

    /**
     * Méthode permettant d'accéder à l'activité DashboardActivity.
     */
    private void openDashboardActivity() {
        CNotification.displayToast(this, "Connexion réussie !", 1);
        Intent dashboardIntent = new Intent(MainActivity.this, DashboardActivity.class);
        startActivity(dashboardIntent);
    }
}
