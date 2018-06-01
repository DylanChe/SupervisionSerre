package com.dylanchesnouard.supervisionserre;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.NetworkOnMainThreadException;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    // ATTRIBUTES
    private Button bt_connection;
    private EditText txt_serverUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CONTROLS & LISTENERS
        bt_connection = findViewById(R.id.bt_connexion);
        bt_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();
            }
        });
        txt_serverUrl = findViewById(R.id.txt_serverUrl);
    }

    /**
     * Permet de tester la connexion Internet puis d'établir une connexion aves le serveur contenant la BDD
     */
    private void connect() {
        CConnexion.setUrl(txt_serverUrl.getText().toString());
        if(CConnexion.getUrl().length() == 0) {
            CNotification.AfficherErreur(this, 3);
        } else {
            try {
                Log.i("CustomLog","[connect] testInternet = " + CConnexion.testInternet(this));
                Log.i("CustomLog","[connect] testConnexion = " + CConnexion.testConnexion());
                if(!CConnexion.testInternet(this)) { // Vérifie que les itinérances de données sont activées
                    CNotification.AfficherErreur(this, 1);
                } else if(!CConnexion.testConnexion()) { // Vérifie la connexion avec le serveur
                    CNotification.AfficherErreur(this, 2);
                } else { // Aucun problème donc la connexion fonctionne
                    CNotification.AfficherToast(this, "Connexion réussie !", 1);
                    Intent dashboardIntent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(dashboardIntent);
                }
            } catch (Exception e) {
                Log.e("CustomLog", "[connect] ERROR : " + e.getMessage());
                CNotification.AfficherErreur(this, 0);
            }
        }
    }
}
