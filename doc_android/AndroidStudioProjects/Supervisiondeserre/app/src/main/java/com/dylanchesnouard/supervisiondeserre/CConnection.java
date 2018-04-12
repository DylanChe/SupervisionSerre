package com.dylanchesnouard.supervisiondeserre;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public final class CConnection {

    // ATTRIBUTES
    private static String ip = null;

    // GETTER & SETTER
    public static void setIp(String _ip) {
        ip = _ip;
    }
    public static String getIp() {
        return ip;
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
                    final HttpURLConnection conn = (HttpURLConnection) new URL("http://" + ip + "/testConnexion.php").openConnection();
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
                    Log.e("CustomLog", e.getMessage());
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
    private static String streamToString(InputStream _inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(_inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e("CustomLog", e.getMessage());
        }
        return total.toString();
    }

}
