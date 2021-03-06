package com.dylanchesnouard.supervisionserre;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LogActivity extends AppCompatActivity {

    private String filtre = null;
    LinearLayout layout_listeLogs;
    List<CLog> logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log);

        // CONTROLS
        layout_listeLogs = findViewById(R.id.layout_scroll_listeLogs);

        // FILTRE
        Intent intent = getIntent();
        if (intent != null)
        {
            if (intent.hasExtra("EXTRA_FILTRE"))
            {
                filtre = intent.getStringExtra("EXTRA_FILTRE");
                afficherFiltre();
            }
        }
        Log.i("CustomLog","filtre = " + filtre);

        // LOAD DATA
        afficherListeLogs();
    }

    private void afficherFiltre() {
        LinearLayout layout_filtre = new LinearLayout(this);
        layout_filtre.setBackgroundColor(getResources().getColor(R.color.bleuOlivier));
        LinearLayout.LayoutParams layout_filtre_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_filtre_params.topMargin = dpToInt(10);
        layout_filtre.setLayoutParams(layout_filtre_params);
        LinearLayout.LayoutParams layout_lblFiltre_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout_lblFiltre_params.topMargin = dpToInt(3);
        layout_lblFiltre_params.bottomMargin = dpToInt(3);
        TextView lbl_filtre = new TextView(this);
        lbl_filtre.setGravity(Gravity.CENTER);
        lbl_filtre.setTypeface(null, Typeface.ITALIC);
        lbl_filtre.setText("Liste des journaux associés à " + filtre);
        lbl_filtre.setTextColor(getResources().getColor(R.color.clouds));
        layout_filtre.addView(lbl_filtre, layout_lblFiltre_params);
        layout_listeLogs.addView(layout_filtre);
    }

    private void afficherAucunLog() {
        Log.i("CustomLog2", "Debut AUCUN LOG");
        try {
            LinearLayout layout_aucunLog = new LinearLayout(this);
            LinearLayout.LayoutParams layout_aucunLog_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout_aucunLog_params.topMargin = dpToInt(10);
            layout_aucunLog.setLayoutParams(layout_aucunLog_params);
            LinearLayout.LayoutParams layout_lblAucunLog_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            TextView lbl_aucunLog = new TextView(this);
            lbl_aucunLog.setGravity(Gravity.CENTER);
            lbl_aucunLog.setTypeface(null, Typeface.BOLD_ITALIC);
            lbl_aucunLog.setText("Aucun journal disponible");
            lbl_aucunLog.setTextColor(getResources().getColor(R.color.silver));
            layout_aucunLog.addView(lbl_aucunLog, layout_lblAucunLog_params);
            layout_listeLogs.addView(layout_aucunLog);
        } catch (Exception e) {
            Log.e("CustomLog2", e.getMessage());
        }
        Log.i("CustomLog2", "FIN AUCUN LOG");
    }

    private void afficherListeLogs() {
        try {
            int totalLogs = 0;
            logs = CConnexion.getLogs();

            for (CLog log : logs) {
                if (filtre != null)
                {
                    if (log.getNom_capteur().equals(filtre))
                    {
                        ajouterUnLog(log);
                        totalLogs += 1;
                    }
                } else {
                    ajouterUnLog(log);
                    totalLogs += 1;
                }
            }

            if (totalLogs == 0) {
                afficherAucunLog();
            }

        } catch (Exception e) {
            Log.e("CustomLog","[afficherListLogs] Error : " + e.getMessage());
        }
    }

    private int dpToInt(int _dpMesure) {
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                _dpMesure,
                r.getDisplayMetrics()
        );
        return px;
    }

    private void ajouterUnLog(CLog log) {
        LinearLayout layout_main = new LinearLayout(this);
        LinearLayout layout_date = new LinearLayout(this);
        LinearLayout layout_id = new LinearLayout(this);
        LinearLayout layout_space = new LinearLayout(this);
        LinearLayout layout_capteur = new LinearLayout(this);
        Log.i("CustomLog","[ajouterUnLog] Création des Layout OK");

        // LAYOUT_MAIN
        LinearLayout.LayoutParams layout_main_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_main_params.topMargin = dpToInt(15);
        layout_main_params.leftMargin = dpToInt(15);
        layout_main_params.rightMargin = dpToInt(15);
        layout_main.setOrientation(LinearLayout.HORIZONTAL);
        layout_main.setLayoutParams(layout_main_params);
        Log.i("CustomLog","[ajouterUnLog] LayoutMain OK");

        // LAYOUT_DATE
        LinearLayout.LayoutParams layout_date_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_date.setOrientation(LinearLayout.VERTICAL);
        if (log.isEstFonctionnel())
        {
            layout_date.setBackgroundColor(getResources().getColor(R.color.nephritis));
        } else {
            layout_date.setBackgroundColor(getResources().getColor(R.color.pomeGranate));
        }
        layout_date.setLayoutParams(layout_date_params);
        Log.i("CustomLog","[ajouterUnLog] LayoutDate OK");
        Log.i("CustomLog","[ajouterUnLog] Jour : " + log.getJourPanne());
        Log.i("CustomLog","[ajouterUnLog] Heure : " + log.getHeurePanne());

        // --- TEXT_VIEW
        LinearLayout.LayoutParams layout_lblDate_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_lblDate_params.leftMargin = dpToInt(10);
        layout_lblDate_params.topMargin = dpToInt(5);
        layout_lblDate_params.rightMargin = dpToInt(10);
        TextView lbl_date = new TextView(this);
        lbl_date.setText(log.getJourPanne());
        lbl_date.setTextColor(getResources().getColor(R.color.clouds));
        layout_date.addView(lbl_date, layout_lblDate_params);
        Log.i("CustomLog","[ajouterUnLog] LblDate OK");

        LinearLayout.LayoutParams layout_lblHeure_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_lblHeure_params.leftMargin = dpToInt(5);
        layout_lblHeure_params.topMargin = dpToInt(5);
        layout_lblHeure_params.rightMargin = dpToInt(5);
        TextView lbl_heure = new TextView(this);
        lbl_heure.setGravity(Gravity.CENTER);
        lbl_heure.setText(log.getHeurePanne());
        lbl_heure.setTextColor(getResources().getColor(R.color.clouds));
        layout_date.addView(lbl_heure, layout_lblHeure_params);
        Log.i("CustomLog","[ajouterUnLog] LblHeure OK");

        // LAYOUT_ID
        LinearLayout.LayoutParams layout_id_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        if (log.isEstFonctionnel())
        {
            layout_id.setBackgroundColor(getResources().getColor(R.color.emerald));
        } else {
            layout_id.setBackgroundColor(getResources().getColor(R.color.alizarin));
        }
        Log.i("CustomLog","[ajouterUnLog] LayoutId OK");
        // --- TEXT_VIEW
        LinearLayout.LayoutParams layout_lblId_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        TextView lbl_id = new TextView(this);
        layout_lblId_params.leftMargin = dpToInt(10);
        layout_lblId_params.rightMargin = dpToInt(10);
        lbl_id.setGravity(Gravity.CENTER);
        lbl_id.setText("" + log.getId());
        lbl_id.setTextColor(getResources().getColor(R.color.clouds));
        layout_id.setLayoutParams(layout_id_params);
        layout_id.addView(lbl_id, layout_lblId_params);
        Log.i("CustomLog","[ajouterUnLog] LblId OK");

        // SPACE LAYOUT
        LinearLayout.LayoutParams layout_space_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        if (log.isEstFonctionnel())
        {
            layout_space.setBackgroundColor(getResources().getColor(R.color.nephritis));
        } else {
            layout_space.setBackgroundColor(getResources().getColor(R.color.pomeGranate));
        }
        layout_space.setMinimumWidth(dpToInt(5));
        layout_space.setLayoutParams(layout_space_params);
        Log.i("CustomLog","[ajouterUnLog] SpaceLayout OK");

        // LAYOUT_CAPTEUR
        LinearLayout.LayoutParams layout_capteur_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout_capteur.setGravity(Gravity.CENTER);
        layout_capteur.setOrientation(LinearLayout.VERTICAL);
        if (log.isEstFonctionnel())
        {
            layout_capteur.setBackgroundColor(getResources().getColor(R.color.emerald));
        } else {
            layout_capteur.setBackgroundColor(getResources().getColor(R.color.alizarin));
        }
        Log.i("CustomLog","[ajouterUnLog] LayoutCapteur OK");

        // --- TEXT_VIEW
        LinearLayout.LayoutParams layout_lblCapteur_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView lbl_capteur = new TextView(this);
        lbl_capteur.setGravity(Gravity.CENTER);
        lbl_capteur.setText("" + log.getNom_capteur());
        lbl_capteur.setTypeface(null, Typeface.BOLD);
        lbl_capteur.setTextColor(getResources().getColor(R.color.clouds));
        layout_capteur.setLayoutParams(layout_capteur_params);
        layout_capteur.addView(lbl_capteur, layout_lblCapteur_params);
        Log.i("CustomLog","[ajouterUnLog] LblCapteur OK");

        LinearLayout.LayoutParams layout_lblDescription_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView lbl_description = new TextView(this);
        lbl_description.setGravity(Gravity.CENTER);
        if (log.isEstFonctionnel()) {
            lbl_description.setText("Le capteur fonctionne de nouveau.");
        } else {
            lbl_description.setText("Le capteur ne fonctionne plus.");
        }
        lbl_description.setTextColor(getResources().getColor(R.color.clouds));
        layout_capteur.addView(lbl_description, layout_lblDescription_params);
        Log.i("CustomLog","[ajouterUnLog] LblDescription OK");

        layout_main.addView(layout_date);
        layout_main.addView(layout_id);
        layout_main.addView(layout_space);
        layout_main.addView(layout_capteur);
        layout_listeLogs.addView(layout_main);
        Log.i("CustomLog","[ajouterUnLog] Le log d'id " + log.getId() + " a été ajouté.");
    }
}
