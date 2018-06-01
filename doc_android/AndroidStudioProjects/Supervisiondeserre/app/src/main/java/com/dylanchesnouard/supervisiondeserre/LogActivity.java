package com.dylanchesnouard.supervisiondeserre;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class LogActivity extends AppCompatActivity {

    // ATTRIBUTES
    String filter = null;
    LinearLayout layout_logs;
    List<CLog> logs;

    // CONSTRUCTOR
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        // CONTROLS
        layout_logs = findViewById(R.id.layout_logs);

        // FILTRE
        Intent intent = getIntent();
        if (intent != null)
        {
            if (intent.hasExtra("EXTRA_FILTER"))
            {
                filter = intent.getStringExtra("EXTRA_FILTER");
                displayFilter();
            }
        }
        Log.i("CustomLog","filtre = " + filter);

        // LOAD DATA
        displayLogs();
    }

    // METHODS

    /**
     * Affiche les journaux
     */
    private void displayLogs() {
        int logsNumber = 0;
        try {
            logs = CConnection.getLogs();
            for (final CLog log : logs) {
                if (filter != null && log.get_hardwareName().equals(filter)) {
                    logsNumber++;
                    LinearLayout logLayout = CLog.toLayout(log, this);
                    layout_logs.addView(logLayout);
                } else if (filter == null){
                    LinearLayout logLayout = CLog.toLayout(log, this);
                    layout_logs.addView(logLayout);
                }
            }
            if (filter != null && logsNumber == 0) {
                displayNoLogs();
            }
        } catch (Exception e) {
            Log.i("CustomLog", e.getMessage());
        }
    }

    /**
     * Convertis les int en dp
     * @param _dpMesure
     * @return
     */
    private int dpToInt(int _dpMesure) {
        Resources r = this.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                _dpMesure,
                r.getDisplayMetrics()
        );
        return px;
    }

    /**
     * Affiche le filtre
     */
    private void displayFilter() {
        // LAYOUT FILTER
        LinearLayout lyt_filter = new LinearLayout(this);
        lyt_filter.setBackgroundColor(getResources().getColor(R.color.bleuOlivier));
        LinearLayout.LayoutParams lytp_filter = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lytp_filter.topMargin = dpToInt(10);
        lyt_filter.setLayoutParams(lytp_filter);

        // --- TEXTVIEW FILTER
        TextView lbl_filter = new TextView(this);
        lbl_filter.setGravity(Gravity.CENTER);
        lbl_filter.setTypeface(null, Typeface.ITALIC);
        lbl_filter.setText(getResources().getString(R.string.log_lbl_associatedLogs) + " " + filter);
        lbl_filter.setTextColor(getResources().getColor(R.color.clouds));
        LinearLayout.LayoutParams lytp_lbl_filter = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lytp_lbl_filter.topMargin = dpToInt(3);
        lytp_lbl_filter.bottomMargin = dpToInt(3);

        lyt_filter.addView(lbl_filter, lytp_lbl_filter);
        layout_logs.addView(lyt_filter);
    }

    /**
     * Affiche un message qui informe qu'il n'y a pas de journaux
     */
    private void displayNoLogs() {
        try {
            // LAYOUT_MAIN
            LinearLayout lyt_main = new LinearLayout(this);
            LinearLayout.LayoutParams lytp_main = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lytp_main.topMargin = dpToInt(10);
            lyt_main.setLayoutParams(lytp_main);

            // --- TEXTVIEW NOLOG
            TextView lbl_noLog = new TextView(this);
            lbl_noLog.setGravity(Gravity.CENTER);
            lbl_noLog.setTypeface(null, Typeface.BOLD_ITALIC);
            lbl_noLog.setText(getResources().getString(R.string.log_lbl_noLog));
            lbl_noLog.setTextColor(getResources().getColor(R.color.silver));
            LinearLayout.LayoutParams lytp_noLog = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lyt_main.addView(lbl_noLog, lytp_noLog);
            layout_logs.addView(lyt_main);
        } catch (Exception e) {
            Log.e("CustomLog", e.getMessage());
        }
    }
}
