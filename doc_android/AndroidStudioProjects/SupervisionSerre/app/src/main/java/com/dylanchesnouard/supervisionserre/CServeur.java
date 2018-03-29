package com.dylanchesnouard.supervisionserre;

import android.os.NetworkOnMainThreadException;
import android.util.Log;

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

public final class CServeur {

    private static String serveurURL = ""; // Adresse IP du serveur contenant les pages .php permettant de communiquer avec la BDD

    /**
     * Mutateurs pour l'attribut "serveurURL".
     * @param url
     */
    public static void setServeurURL(String url) {
        serveurURL = url;
    }

    /**
     * Accesseur pour l'attribut "serveurURL".
     * Retourne "serveurURL".
     * @return
     */
    public static String getServeurURL() {
        return serveurURL;
    }

    /**
     * Permet de tester la connexion avec le serveur via une page "testConnexion.php".
     * Retourne "true" si la connexion fonctionne, sinon "false".
     * @return
     * @throws NetworkOnMainThreadException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static boolean testConnexion() throws NetworkOnMainThreadException, InterruptedException, ExecutionException {
/*
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
*/

        try {
            InputStream inputStream = getInputStream("testConnexion.php");
            String data = readIt(inputStream).substring(0,2);
            Log.d("CUSTOMLOG", "CServeur.testConnexion : data = " + data);
            if(data.equals("OK")) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e) {
            Log.e("CUSTOMLOG", "CServeur.testConnexion : " + e.getMessage());
        }
        return false;

        //return future.get();
    }

    /**
     * Permet de transformer un InputStream en String.
     * @param is
     * @return
     * @throws IOException
     */
    private static String readIt(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null)
        {
            sb.append(line + "\n");
        }
        Log.d("CUSTOMLOG", "CServeur.readIt : " + sb.toString());
        return sb.toString();
    }

    public static InputStream getInputStream(final String pagePhp) throws NetworkOnMainThreadException, InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<InputStream> callable = new Callable<InputStream>() {
            @Override
            public InputStream call() throws Exception {
                InputStream inputStream = null;
                try {
                    String urlComplet = "http://" + serveurURL + "/" + pagePhp;
                    Log.d("CUSTOMLOG", "CServeur.getInputStream : urlComplet = " + urlComplet);
                    final HttpURLConnection conn = (HttpURLConnection) new URL(urlComplet).openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    Log.d("CUSTOMLOG", "CServeur.getInputStream : Connexion OK ");
                    inputStream = conn.getInputStream();
                    Log.d("CUSTOMLOG", "CServeur.getInputStream : GetInputStream OK ");
                    Log.d("CUSTOMLOG", "CServeur.getInputStream : FIN DU TRY CALLABLE ");
                } catch(Exception e) {
                    Log.e("CUSTOMLOG", "CServeur.getInputStream : " + e.getMessage());
                } finally {
                    if(inputStream != null) {
                        inputStream.close(); // FERMER AVANT LE RENVOI
                    }
                }
                return inputStream;
            }
        };
        Log.d("CUSTOMLOG", "CServeur.getInputStream : FIN DU CALLABLE ");
        Future<InputStream> future = executor.submit(callable);
        executor.shutdown();
        try {
            Log.d("CUSTOMLOG", "CServeur.getInputStream : future = " + readIt(future.get()));
        } catch (Exception e) {
            Log.e("CUSTOMLOG", "CServeur.getInputStream : " + e.getMessage());
        }

        return future.get();
    }

    public static String getMateriels() {
        String materiels = "";
        try {
            materiels = readIt(getInputStream("materiels.php")); // ERREUR
            Log.d("CUSTOMLOG", "CServeur.getMateriels : materiels = " + materiels);
        } catch(Exception e) {
            Log.e("CUSTOMLOG", "CServeur.getMateriels : " + e.getMessage());
        }
        return materiels;
    }
}
