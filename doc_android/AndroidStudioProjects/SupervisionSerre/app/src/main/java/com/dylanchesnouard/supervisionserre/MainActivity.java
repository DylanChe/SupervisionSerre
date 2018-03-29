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

    private Button bt_connexion;
    private EditText txt_serveurURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CONTROLS
        bt_connexion = findViewById(R.id.bt_connexion);
        bt_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connexion(v);
            }
        });
        txt_serveurURL = findViewById(R.id.txt_serveurURL);
    }

    private void connexion(View v) {
        CServeur.setServeurURL(txt_serveurURL.getText().toString());
        if((CServeur.getServeurURL().length() == 0)) {
            CNotification.AfficherErreur(this, 3);
        } else {
            try {
                Log.d("CUSTOMLOG", "MainActivity.connexion : CServeur.testConnexion = " + CServeur.testConnexion());
                if (!testInternet()) {
                    CNotification.AfficherErreur(this, 1);
                } else if (!CServeur.testConnexion()) {
                    CNotification.AfficherErreur(this, 2);
                } else {
                    CNotification.AfficherToast(this, "Connexion réussie !", 1);
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            } catch (Exception e) {
                CNotification.AfficherErreur(this, 0);
            }
        }
    }

    private boolean testInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
