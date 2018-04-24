package com.dylanchesnouard.supervisiondeserre;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class CAlert {

    // METHODS

    /**
     * Convertis les int en dp
     * @param _dpMesure
     * @return
     */
    private static int dpToInt(int _dpMesure, Context context) {
        Resources r = context.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                _dpMesure,
                r.getDisplayMetrics()
        );
        return px;
    }

    /**
     * Retourne un LinearLayout qui affiche une alerte avec les infos passés en paramètres
     * @param message
     * @param context
     * @return
     */
    public static LinearLayout alertToLayout (String message, final String messageAdv, final Context context) {
        // LAYOUT MAIN
        LinearLayout lyt_main = new LinearLayout(context);
        lyt_main.setBackgroundColor(context.getResources().getColor(R.color.pomeGranate));
        lyt_main.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams lytp_main = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lytp_main.topMargin = dpToInt(2, context);
        lyt_main.setLayoutParams(lytp_main);

        // --- IMAGEVIEW ALERT
        ImageView img_alert = new ImageView(context);
        img_alert.setImageDrawable(context.getResources().getDrawable(R.drawable.alert));
        LinearLayout.LayoutParams lytp_alert = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lytp_alert.setMargins(dpToInt(5,context), dpToInt(5, context), dpToInt(5, context), dpToInt(5, context));
        img_alert.setLayoutParams(lytp_alert);

        // --- TEXTVIEW ALERT
        TextView lbl_message = new TextView(context);
        lbl_message.setText(message);
        lbl_message.setTextColor(context.getResources().getColor(R.color.clouds));
        LinearLayout.LayoutParams lytp_message = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lbl_message.setLayoutParams(lytp_message);

        lyt_main.addView(img_alert);
        lyt_main.addView(lbl_message);

        lyt_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CNotification.displayError(context, messageAdv);
            }
        });

        Log.i("CustomLog","Un log a été ajouté.");

        return lyt_main;
    }

    /**
     * Retourne un LinearLayout qui affiche une alerte avec les infos passés en paramètres
     * @param message
     * @param context
     * @return
     */
    public static LinearLayout alertToLayout (String message, final Context context) {
        LinearLayout lyt_main = alertToLayout(message, message, context);

        Log.i("CustomLog","Un log a été ajouté.");

        return lyt_main;
    }
}
