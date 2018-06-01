package com.dylanchesnouard.supervisiondeserre;

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

    // ATTRIBUTES
    private static String _ip = null;
    private static final String _LOG_TAG = "CustomLog";
    private static final String _TEST_CONNECTION = "testConnexion.php";
    private static final String _GET_SENSORS = "getSensors.php";
    private static final String _GET_MICROCONTROLLERS = "getMicrocontrollers.php";
    private static final String _GET_LOGS = "getLogs.php";

    // GETTER & SETTER
    public static void setIp(String ip) {
        _ip = ip;
    }
    public static String getIp() {
        return _ip;
    }

    // METHODS

    /**
     * Renvoie vrai si l'itinérance de données du téléphone est activée
     * @param mainActivity
     * @return
     */
    public static boolean isInternetActivated(Activity mainActivity) {
        ConnectivityManager cm = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Renvoi vrai si il est possible de récuperer le contenu d'une page du serveur
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static boolean isConnectionWorking() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                InputStream inputStream = null;
                try {
                    final HttpURLConnection conn = (HttpURLConnection) new URL("http://" + _ip + "/" + _TEST_CONNECTION).openConnection();
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
                    Log.e(_LOG_TAG, e.getMessage());
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

    /**
     * Convertit un InputStream en String
     * @param _inputStream
     * @return
     */
    private static String streamToString(InputStream _inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(_inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(_LOG_TAG, e.getMessage());
        }
        return total.toString();
    }

    /**
     * Récupere les données d'une page php
     * @param pagePhp
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static String getDataFromPhp(final String pagePhp) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                InputStream inputStream = null;
                try {
                    final HttpURLConnection conn = (HttpURLConnection) new URL("http://" + _ip + "/" + pagePhp).openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    inputStream = conn.getInputStream();
                    String data = streamToString(inputStream);
                    data = data.substring(0, data.length() - 1);
                    return data;
                } catch(Exception e) {
                    Log.d(_LOG_TAG, e.getMessage());
                } finally {
                    if(inputStream != null) {
                        inputStream.close();
                    }
                }
                return null;
            }
        };
        Future<String> future = executor.submit(callable);
        executor.shutdown();
        String dataString = future.get();
        Log.i("CustomLog","[getData] dataString = " + dataString);
        return dataString;
    }

    // HARDWARE METHODS

    /**
     * Retourne le nombre de materiels inscrit dans la base de données du serveur
     * @param type
     * @return
     */
    public static int getHardwareNumber(CHardware.HardwareType type) {
        try {
            return getHardware(type).size();
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * Retourne une liste des materiels du type préciser en paramètre qui sont présents dans la base de données du serveur
     * @param type
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static List<CHardware> getHardware(CHardware.HardwareType type) throws IOException, InterruptedException, ExecutionException {
        JsonReader reader;
        switch (type) {
            case Sensor:
                reader = new JsonReader(new StringReader(getDataFromPhp(_GET_SENSORS)));
                break;
            case Microcontroller:
                reader = new JsonReader(new StringReader(getDataFromPhp(_GET_MICROCONTROLLERS)));
                break;
            default:
                return null;
        }
        try {
            return readHardwareArray(reader);
        } finally {
            reader.close();
        }
    }

    /**
     * Retourne une liste des materiels lu par le JsonReader
     * @param _reader
     * @return
     * @throws IOException
     */
    private static List<CHardware> readHardwareArray(JsonReader _reader) throws IOException {
        List<CHardware> hardwareList = new ArrayList<>();
        _reader.beginArray();
        while (_reader.hasNext()) {
            hardwareList.add(readHardware(_reader));
        }
        _reader.endArray();
        return hardwareList;
    }

    /**
     * Retourne un materiel en fonction de ce que lis le JsonReader
     * @param _reader
     * @return
     * @throws IOException
     */
    private static CHardware readHardware(JsonReader _reader) throws IOException {
        int id = -1;
        String name = null;
        String shortName = null;
        boolean isFunctional = false;
        CHardware.HardwareType type = null;

        _reader.beginObject();
        while (_reader.hasNext()) {
            String tag = _reader.nextName();
            if(tag.equals("id")) {
                id = _reader.nextInt();
            } else if(tag.equals("nom")) {
                name = _reader.nextString();
            } else if(tag.equals("abreviation")) {
                shortName = _reader.nextString();
            } else if(tag.equals("est_fonctionnel")) {
                isFunctional = (_reader.nextInt() > 0);
            } else if (tag.equals("id_type_materiel")) {
                type = CHardware.HardwareType.values()[_reader.nextInt()-1];
            } else {
                _reader.skipValue();
            }
        }
        _reader.endObject();
        return new CHardware(id, name, shortName, isFunctional, type);
    }

    // LOGS METHODS

    /**
     * Retourne le nombre de journaux présent dans la base de données
     * @return
     */
    public static int getLogsNumber() {
        try {
            return getLogs().size();
        } catch (Exception e) {
            Log.e(_LOG_TAG, e.getMessage());
        }
        return 0;
    }

    /**
     * Retourne une liste des journaux présent dans la base de données
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static List<CLog> getLogs() throws IOException, InterruptedException, ExecutionException {
        JsonReader reader = new JsonReader(new StringReader(getDataFromPhp(_GET_LOGS)));
        try {
            return readLogsArray(reader);
        } finally {
            reader.close();
        }
    }

    /**
     * Retourne une liste des journaux présent dans la base de données correspondant à un materiel
     * @param hardwareName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static List<CLog> getLogs(String hardwareName) throws IOException, InterruptedException, ExecutionException {
        List<CLog> logs = new ArrayList<>();
        for (CLog log : getLogs()) {
            if (log.get_hardwareName().equals(hardwareName)) {
                logs.add(log);
            }
        }
        return logs;
    }

    /**
     * Retourne une liste des journaux lu par le JsonReader
     * @param _reader
     * @return
     * @throws IOException
     */
    private static List<CLog> readLogsArray(JsonReader _reader) throws IOException {
        List<CLog> logs = new ArrayList<>();
        _reader.beginArray();
        while (_reader.hasNext()) {
            logs.add(readLog(_reader));
        }
        _reader.endArray();
        return logs;
    }

    /**
     * Retourne un journal en fonction de que lis le JsonReader
     * @param _reader
     * @return
     * @throws IOException
     */
    private static CLog readLog(JsonReader _reader) throws IOException {
        int id = -1;
        boolean isFunctional = false;
        String breakdownDate = null;
        String hardwareName = null;

        _reader.beginObject();
        while (_reader.hasNext()) {
            String tag = _reader.nextName();
            if(tag.equals("id")) {
                id = _reader.nextInt();
            } else if(tag.equals("est_fonctionnel")) {
                isFunctional = (_reader.nextInt() > 0);
            } else if(tag.equals("date_panne")) {
                breakdownDate = _reader.nextString();
            } else if(tag.equals("nom")) {
                hardwareName = _reader.nextString();
            } else {
                _reader.skipValue();
            }
        }
        _reader.endObject();
        return new CLog(id, isFunctional, breakdownDate, hardwareName);
    }

}
