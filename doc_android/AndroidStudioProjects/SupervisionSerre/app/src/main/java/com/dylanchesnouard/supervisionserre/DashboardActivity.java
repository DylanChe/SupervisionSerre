package com.dylanchesnouard.supervisionserre;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
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

public class DashboardActivity extends AppCompatActivity {

    private TextView lbl_nbCapteurs;
    private TextView lbl_nbMicrocontrolleurs;
    private TextView lbl_nbLogs;
    private LinearLayout layout_capteurs;
    private LinearLayout layout_microcontrolleurs;
    private LinearLayout layout_logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // CONTROLS
        lbl_nbCapteurs = findViewById(R.id.lbl_nbCapteurs);
        lbl_nbMicrocontrolleurs = findViewById(R.id.lbl_nbMicrocontrolleurs);
        lbl_nbLogs = findViewById(R.id.lbl_nbLogs);
        layout_capteurs = findViewById(R.id.layout_capteurs);
        layout_capteurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorIntent = new Intent(DashboardActivity.this, SensorActivity.class);
                startActivity(sensorIntent);
            }
        });
        layout_microcontrolleurs = findViewById(R.id.layout_microcontroller);
        layout_microcontrolleurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent microcontrolleurIntent = new Intent(DashboardActivity.this, MicrocontrollerActivity.class);
                startActivity(microcontrolleurIntent);
            }
        });
        layout_logs = findViewById(R.id.layout_logs);
        layout_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent(DashboardActivity.this, LogActivity.class);
                startActivity(logIntent);
            }
        });

        // LOAD DATA
        try {
            chargementDonnees();
        } catch (Exception e) {
            Log.e("CustomLog", "[onCreate] ERROR : " + e.getMessage());
        }
    }

    private void chargementDonnees() {
        try {
            int nbCapteur = CConnexion.getCapteurs().size();
            lbl_nbCapteurs.setText("" + nbCapteur);
            Log.i("CustomLog", "[chargementDonnees] nbCapteur : " + nbCapteur);
            int nbMicrocontrolleur = CConnexion.getMicrocontrolleurs().size();
            lbl_nbMicrocontrolleurs.setText("" + nbMicrocontrolleur);
            Log.i("CustomLog", "[chargementDonnees] nbMicrocontrolleur : " + nbMicrocontrolleur);
            int nbLogs = CConnexion.getLogs().size();
            lbl_nbLogs.setText("" + nbLogs);
            Log.i("CustomLog", "[chargementDonnees] nbLogs : " + nbLogs);
        } catch (Exception e) {
            Log.e("CustomLog", "[chargementDonnees] ERROR : " + e.getMessage());
        }
    }

}
