package com.dylanchesnouard.supervisionserre;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class CConnexion {

    // ATTRIBUTES
    private static String url = null;

    // GETTER & SETTER
    public static void setUrl(String _url) {
        url = _url;
    }
    public static String getUrl() {
        return url;
    }

    // METHODS
    public static boolean testInternet(Activity mainActivity) {
        ConnectivityManager cm = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public static boolean testConnexion() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                InputStream inputStream = null;
                try {
                    final HttpURLConnection conn = (HttpURLConnection) new URL("http://" + url + "/testConnexion.php").openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    inputStream = conn.getInputStream();
                    String data = streamToString(inputStream);
                    data = data.substring(0,data.length()-1);
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
    public static String getData(final String _pagePhp) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                InputStream inputStream = null;
                try {
                    final HttpURLConnection conn = (HttpURLConnection) new URL("http://" + url + "/" + _pagePhp).openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    inputStream = conn.getInputStream();
                    String data = streamToString(inputStream);
                    data = data.substring(0,data.length()-1);
                    Log.i("CustomLog","[getNbCapteurs] data = " + data);
                    return data;
                } catch(Exception e) {
                    Log.d("EXECUTOR",e.getMessage());
                } finally {
                    if(inputStream != null) {
                        inputStream.close();
                    }
                }
                return "ERROR";
            }
        };
        Future<String> future = executor.submit(callable);
        executor.shutdown();
        String dataString = future.get();
        Log.i("CustomLog","dataString = " + dataString);
        return dataString;
    }
    private static String streamToString(InputStream _inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(_inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e("CustomLog","[streamToString] IOException : " + e.getMessage());
        }
        return total.toString();
    }
    // --- CAPTEURS
    public static List<CCapteur> getCapteurs() throws IOException, InterruptedException, ExecutionException {
        JsonReader reader = new JsonReader(new StringReader(getData("getCapteurs.php")));
        try {
            return readCapteursArray(reader);
        } finally {
            reader.close();
        }
    }
    private static List<CCapteur> readCapteursArray(JsonReader _reader) throws IOException {
        List<CCapteur> capteurs = new ArrayList<CCapteur>();
        _reader.beginArray();
        while (_reader.hasNext()) {
            capteurs.add(readCapteur(_reader));
        }
        _reader.endArray();
        return capteurs;
    }
    private static CCapteur readCapteur(JsonReader _reader) throws IOException {
        int id = -1;
        String nom = null;
        boolean etat = false;

        _reader.beginObject();
        while (_reader.hasNext()) {
            String tag = _reader.nextName();
            if(tag.equals("id")) {
                id = _reader.nextInt();
            } else if(tag.equals("nom")) {
                nom = _reader.nextString();
            } else if(tag.equals("etat")) {
                etat = (_reader.nextInt() > 0);
            } else {
                _reader.skipValue();
            }
        }
        _reader.endObject();
        return new CCapteur(id, nom, etat);
    }

    // --- MICROCONTROLLEURS
    public static List<CMicrocontrolleur> getMicrocontrolleurs() throws IOException, InterruptedException, ExecutionException {
        JsonReader reader = new JsonReader(new StringReader(getData("getMicrocontrolleurs.php")));
        try {
            return readMicrocontrolleursArray(reader);
        } finally {
            reader.close();
        }
    }
    private static List<CMicrocontrolleur> readMicrocontrolleursArray(JsonReader _reader) throws IOException {
        List<CMicrocontrolleur> microcontrolleurs = new ArrayList<CMicrocontrolleur>();
        _reader.beginArray();
        while (_reader.hasNext()) {
            microcontrolleurs.add(readMicrocontrolleur(_reader));
        }
        _reader.endArray();
        return microcontrolleurs;
    }
    private static CMicrocontrolleur readMicrocontrolleur(JsonReader _reader) throws IOException {
        int id = -1;
        String nom = null;
        boolean etat = false;

        _reader.beginObject();
        while (_reader.hasNext()) {
            String tag = _reader.nextName();
            if(tag.equals("id")) {
                id = _reader.nextInt();
            } else if(tag.equals("nom")) {
                nom = _reader.nextString();
            } else if(tag.equals("etat")) {
                etat = (_reader.nextInt() > 0);
            } else {
                _reader.skipValue();
            }
        }
        _reader.endObject();
        return new CMicrocontrolleur(id, nom, etat);
    }

    // --- LOGS
    public static List<CLog> getLogs() throws IOException, InterruptedException, ExecutionException {
        JsonReader reader = new JsonReader(new StringReader(getData("getLogs.php")));
        try {
            return readLogsArray(reader);
        } finally {
            reader.close();
        }
    }
    private static List<CLog> readLogsArray(JsonReader _reader) throws IOException {
        List<CLog> logs = new ArrayList<CLog>();
        _reader.beginArray();
        while (_reader.hasNext()) {
            logs.add(readLog(_reader));
        }
        _reader.endArray();
        return logs;
    }
    private static CLog readLog(JsonReader _reader) throws IOException {
        int id = -1;
        boolean etat = false;
        //Date date_panne = null;
        String date_panne = null;
        String nom_capteur = null;

        _reader.beginObject();
        while (_reader.hasNext()) {
            String tag = _reader.nextName();
            if(tag.equals("id")) {
                Log.i("CustomLog","READ ID...");
                id = _reader.nextInt();
                Log.i("CustomLog","ID = " + id);
            } else if(tag.equals("est_fonctionnel")) {
                Log.i("CustomLog","READ EST_FONCTIONNEL...");
                etat = (_reader.nextInt() > 0);
                Log.i("CustomLog","EST_FONCTIONNEL = " + etat);
            } else if(tag.equals("date_panne")) {
                Log.i("CustomLog","READ DATE...");
                date_panne = _reader.nextString();
                Log.i("CustomLog","DATE : " + date_panne);
                /*
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                try {
                    date_panne = format.parse(_reader.nextString());
                } catch (ParseException e) {
                    Log.e("CustomLog","[readLog] Error : " + e.getMessage());
                }
                */
            } else if(tag.equals("nom")) {
                Log.i("CustomLog","READ NOM...");
                nom_capteur = _reader.nextString();
                Log.i("CustomLog","NOM = " + nom_capteur);
            } else {
                _reader.skipValue();
            }
        }
        _reader.endObject();
        return new CLog(id, etat, date_panne, nom_capteur);
    }


}
