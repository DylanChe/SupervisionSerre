package com.dylanchesnouard.supervisionserre;

import android.content.Context;
import android.content.DialogInterface;
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

    private String serveurURL = null;

    private Button bt_connexion;
    private EditText txt_serveurURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // *** CONTROLS
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
        serveurURL = txt_serveurURL.getText().toString();
        if((txt_serveurURL.getText().toString().length() == 0)) {
            notifErreur(3);
        }
        try {
            if(!testInternet()){
                notifErreur(1);
            } else if(!testConnexionBdd()) {
                notifErreur(2);
            } else {
                Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                //notif("Connexion réussie", "OK");
            }
        } catch (Exception e) {
            notifErreur(0);
        }
    }

    private void notifErreur(int typeError) {
        AlertDialog.Builder dlg_connexionBdd = new AlertDialog.Builder(this);
        dlg_connexionBdd.setTitle("Erreur");
        switch (typeError) {
            case 1: dlg_connexionBdd.setMessage("Les itinérances de données sont désactivées.\nVeuillez les activées.");
                break;
            case 2: dlg_connexionBdd.setMessage("Impossible d'établir une connexion avec la base de données.\nVeulliez vérifier que l'adresse du serveur est correcte.");
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

    private void notif(String title, String message) {
        AlertDialog.Builder dlg_connexionBdd = new AlertDialog.Builder(this);
        dlg_connexionBdd.setTitle(title);
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

    private boolean testInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private boolean testConnexionBdd() throws NetworkOnMainThreadException, InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                InputStream inputStream = null;
                try {
                    final HttpURLConnection conn = (HttpURLConnection) new URL("http://" + serveurURL + "/testConnexion.php").openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    inputStream = conn.getInputStream();
                    String data = readIt(inputStream).substring(0,2);
                    if(data.equals("OK")) {
                        return true;
                    } else {
                        return false;
                    }
                } catch(Exception e) {
                    Log.d("EXECUTOR",e.getMessage());
                } finally {
                    if(inputStream != null) {
                        inputStream.close();
                    }
                }
                return false;
            }
        };
        Future<Boolean> future = executor.submit(callable);
        executor.shutdown();
        return future.get();
    }

    private String readIt(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder reponse = new StringBuilder();
        String line;
        while((line = r.readLine()) != null) {
            reponse.append(line).append('\n');
        }
        return reponse.toString();
    }
}
