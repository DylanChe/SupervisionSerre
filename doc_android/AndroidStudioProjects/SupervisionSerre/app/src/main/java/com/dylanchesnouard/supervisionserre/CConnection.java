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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class CConnection {

    /* ========== TO DO ==========
    Créer nouvelle classe CSensors pour remplacer CCapteurs
    Créer nouvelle classe CMicrocontroller pour remplacer CMicrocontrolleur
    Dans le serveur :
        - remplacer getCapteurs.php par getSensors.php
        - remplacer getMicrocontrolleurs.php par getMicrocontrollers.php
    Dans MainActivity.java :
        - remplacer testInternet par isInternetActivated
        - remplacer testConnexion par isConnectionWorking
        - remplacer getCapteurs par getSensors
    Dans CLog.java :
        - renommer les attributs en anglais

    */



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
    public static boolean isInternetActivated(Activity mainActivity) {
        ConnectivityManager cm = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public static boolean isConnectionWorking() throws InterruptedException, ExecutionException {
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
        Log.i("CustomLog","[getData] dataString = " + dataString);
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

    // --- SENSORS
    public static List<CSensor> getSensors() throws IOException, InterruptedException, ExecutionException {
        JsonReader reader = new JsonReader(new StringReader(getData("getSensors.php")));
        try {
            return readSensorsArray(reader);
        } finally {
            reader.close();
        }
    }
    private static List<CSensor> readSensorsArray(JsonReader _reader) throws IOException {
        List<CSensor> sensors = new ArrayList<CCapteur>();
        _reader.beginArray();
        while (_reader.hasNext()) {
            sensors.add(readSensor(_reader));
        }
        _reader.endArray();
        return sensors;
    }
    private static CSensor readSensor(JsonReader _reader) throws IOException {
        int id = -1;
        String name = null;
        boolean isFunctional = false;

        _reader.beginObject();
        while (_reader.hasNext()) {
            String tag = _reader.nextName();
            if(tag.equals("id")) {
                id = _reader.nextInt();
            } else if(tag.equals("nom")) {
                name = _reader.nextString();
            } else if(tag.equals("etat")) {
                isFunctional = (_reader.nextInt() > 0);
            } else {
                _reader.skipValue();
            }
        }
        _reader.endObject();
        return new CSensor(id, name, isFunctional);
    }

    // --- MICROCONTROLLERS
    public static List<CMicrocontroller> getMicrocontrollers() throws IOException, InterruptedException, ExecutionException {
        JsonReader reader = new JsonReader(new StringReader(getData("getMicrocontrollers.php")));
        try {
            return readMicrocontrollersArray(reader);
        } finally {
            reader.close();
        }
    }
    private static List<CMicrocontroller> readMicrocontrollersArray(JsonReader _reader) throws IOException {
        List<CMicrocontroller> microcontrollers = new ArrayList<CMicrocontroller>();
        _reader.beginArray();
        while (_reader.hasNext()) {
            microcontrollers.add(readMicrocontroller(_reader));
        }
        _reader.endArray();
        return microcontrollers;
    }
    private static CMicrocontroller readMicrocontroller(JsonReader _reader) throws IOException {
        int id = -1;
        String name = null;
        boolean isFunctional = false;

        _reader.beginObject();
        while (_reader.hasNext()) {
            String tag = _reader.nextName();
            if(tag.equals("id")) {
                id = _reader.nextInt();
            } else if(tag.equals("nom")) {
                name = _reader.nextString();
            } else if(tag.equals("etat")) {
                isFunctional = (_reader.nextInt() > 0);
            } else {
                _reader.skipValue();
            }
        }
        _reader.endObject();
        return new CMicrocontroller(id, name, isFunctional);
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
        boolean isFunctional = false;
        String breakdateDate = null;
        String sensorName = null;

        _reader.beginObject();
        while (_reader.hasNext()) {
            String tag = _reader.nextName();
            if(tag.equals("id")) {
                id = _reader.nextInt();
            } else if(tag.equals("est_fonctionnel")) {
                isFunctional = (_reader.nextInt() > 0);
            } else if(tag.equals("date_panne")) {
                breakdateDate = _reader.nextString();
            } else if(tag.equals("nom")) {
                sensorName = _reader.nextString();
            } else {
                _reader.skipValue();
            }
        }
        _reader.endObject();
        return new CLog(id, isFunctional, breakdateDate, sensorName);
    }


}
