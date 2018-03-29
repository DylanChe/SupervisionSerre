package com.dylanchesnouard.supervisionserre;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        try {
            chargementDonnees();
        } catch (Exception e) {
            Log.e("CUSTOMLOG", "DashboardActivity.onCreate : " + e.getMessage());
        }
    }

    private void chargementDonnees() throws NetworkOnMainThreadException, InterruptedException, ExecutionException, JSONException{
        AlertDialog.Builder dlg_connexionBdd = new AlertDialog.Builder(this);
        dlg_connexionBdd.setTitle("Chargement");
        dlg_connexionBdd.setMessage("Chargement des données, veuillez patienter...");
        dlg_connexionBdd.setCancelable(false);
        AlertDialog alert_connexionBdd = dlg_connexionBdd.create();
        alert_connexionBdd.show();
        /*
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                InputStream inputStream = null;
                String data = "";
                try {
                    final HttpURLConnection conn = (HttpURLConnection) new URL("http://" + serveurURL + "/materiels.php").openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    inputStream = conn.getInputStream();
                    data = readIt(inputStream);
                } catch(Exception e) {
                    Log.d("EXECUTOR",e.getMessage());
                } finally {
                    if(inputStream != null) {
                        inputStream.close();
                    }
                }
                return data;
            }
        };
        Future<String> future = executor.submit(callable);
        executor.shutdown();
        */

        String materiels = CServeur.getMateriels();

        alert_connexionBdd.hide();

        Log.d("CUSTOMLOG", "DashboardActivity.chargementDonnees : materiels = " + materiels);
    }

}
