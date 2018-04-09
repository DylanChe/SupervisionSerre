package com.dylanchesnouard.supervisionserre;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MicrocontrollerActivity extends AppCompatActivity {

    LinearLayout layout_listeMicrocontrolleurs;
    List<CMicrocontrolleur> microcontrolleurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_microcontroller);

        // CONTROLS
        layout_listeMicrocontrolleurs = findViewById(R.id.layout_scroll_listeMicrocontrolleurs);

        // LOAD DATA
        afficherListeMicrocontrolleurs();
    }

    private void afficherListeMicrocontrolleurs() {
        try {
            microcontrolleurs = CConnexion.getMicrocontrolleurs();
            for (CMicrocontrolleur microcontrolleur : microcontrolleurs) {
                ajouterUnMicrocontrolleur(microcontrolleur);
            }
        } catch (Exception e) {
            Log.e("CustomLog","[afficherListeMicrocontrolleurs] Error : " + e.getMessage());
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

    private void ajouterUnMicrocontrolleur(final CMicrocontrolleur microcontrolleur) {
        LinearLayout layout_main = new LinearLayout(this);
        LinearLayout layout_title = new LinearLayout(this);
        LinearLayout layout_description = new LinearLayout(this);

        // LAYOUT_MAIN
        LinearLayout.LayoutParams layout_main_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_main_params.topMargin = dpToInt(15);
        layout_main_params.leftMargin = dpToInt(15);
        layout_main_params.rightMargin = dpToInt(15);
        layout_main.setOrientation(LinearLayout.VERTICAL);
        layout_main.setLayoutParams(layout_main_params);
        // LAYOUT_TITLE
        LinearLayout.LayoutParams layout_title_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_title.setOrientation(LinearLayout.HORIZONTAL);
        if (microcontrolleur.getEstFonctionnel())
        {
            layout_title.setBackgroundColor(getResources().getColor(R.color.nephritis));
        } else {
            layout_title.setBackgroundColor(getResources().getColor(R.color.pomeGranate));
        }
        layout_title.setLayoutParams(layout_title_params);
        // --- TEXT_VIEW
        LinearLayout.LayoutParams layout_lblId_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_lblId_params.leftMargin = dpToInt(15);
        layout_lblId_params.topMargin = dpToInt(6);
        layout_lblId_params.bottomMargin = dpToInt(10);
        TextView lbl_id = new TextView(this);
        lbl_id.setText("" + microcontrolleur.getId());
        lbl_id.setTextColor(getResources().getColor(R.color.clouds));
        layout_title.addView(lbl_id, layout_lblId_params);
        LinearLayout.LayoutParams layout_lblTitle_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_lblTitle_params.leftMargin = dpToInt(15);
        layout_lblTitle_params.topMargin = dpToInt(7);
        layout_lblTitle_params.bottomMargin = dpToInt(7);
        TextView lbl_title = new TextView(this);
        lbl_title.setText(microcontrolleur.getNom());
        lbl_title.setTextSize(15);
        lbl_title.setTypeface(null, Typeface.BOLD);
        lbl_title.setTextColor(getResources().getColor(R.color.clouds));
        layout_title.addView(lbl_title, layout_lblTitle_params);
        // LAYOUT_DESCRIPTION
        LinearLayout.LayoutParams layout_description_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_description.setOrientation(LinearLayout.VERTICAL);
        if (microcontrolleur.getEstFonctionnel())
        {
            layout_description.setBackgroundColor(getResources().getColor(R.color.emerald));
        } else {
            layout_description.setBackgroundColor(getResources().getColor(R.color.alizarin));
        }
        layout_description.setLayoutParams(layout_description_params);
        // --- TEXT_VIEW
        LinearLayout.LayoutParams layout_lblEtat_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_lblEtat_params.leftMargin = dpToInt(15);
        layout_lblEtat_params.topMargin = dpToInt(5);
        layout_lblEtat_params.bottomMargin = dpToInt(5);
        TextView lbl_etat = new TextView(this);
        if (microcontrolleur.getEstFonctionnel()) {
            lbl_etat.setText("Le microcontrolleur fonctionne correctement.");
        } else {
            lbl_etat.setText("Le microcontrolleur ne fonctionne pas.");
        }
        lbl_etat.setTextColor(getResources().getColor(R.color.clouds));
        layout_description.addView(lbl_etat, layout_lblEtat_params);
        layout_main.addView(layout_title);
        layout_main.addView(layout_description);

        layout_listeMicrocontrolleurs.addView(layout_main);
        Log.i("CustomLog","[ajouterUnMicrocontrolleur] Le microcontrolleur " + microcontrolleur.getNom() + " a été ajouté.");

        layout_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent(MicrocontrollerActivity.this, LogActivity.class);
                logIntent.putExtra("EXTRA_FILTRE", microcontrolleur.getNom());
                startActivity(logIntent);
            }
        });
    }
}
