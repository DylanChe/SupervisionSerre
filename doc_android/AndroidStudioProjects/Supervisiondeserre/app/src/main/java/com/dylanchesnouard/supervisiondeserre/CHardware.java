package com.dylanchesnouard.supervisiondeserre;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CHardware {

    // ATTRIBUTES
    public enum HardwareType {
        Sensor, Microcontroller
    }
    private int _id;
    private String _name;
    private String _shortName;
    private boolean _isFunctional;
    private HardwareType _type;

    // CONSTRUCTORS
    public CHardware(int id, String name, String shortName, boolean isFunctional, HardwareType type) {
        _id = id;
        _name = name;
        _shortName = shortName;
        _isFunctional = isFunctional;
        _type = type;

        Log.i("CustomLog", "*** " + _type + " :");
        Log.i("CustomLog", " - Nom : " + _name);
        Log.i("CustomLog", " - Abréviation : " + _shortName);
        Log.i("CustomLog", " - ID : " + _id);
        Log.i("CustomLog", " - Est fonctional : " + _isFunctional);

    }

    // GETTERS & SETTERS
    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }
    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_shortName() {
        return _shortName;
    }
    public void set_shortName(String _shortName) {
        this._shortName = _shortName;
    }

    public boolean is_isFunctional() {
        return _isFunctional;
    }
    public void set_isFunctional(boolean _isFunctional) {
        this._isFunctional = _isFunctional;
    }

    public HardwareType get_type() {
        return _type;
    }
    public void set_type(HardwareType _type) {
        this._type = _type;
    }

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
     * Retourne un LinearLayout qui résume le materiel passé en paramètre
     * @param hardware
     * @param context
     * @return
     */
    public static LinearLayout toLayout(final CHardware hardware, final Context context) {
        // LAYOUT MAIN
        LinearLayout lyt_main = new LinearLayout(context);
        lyt_main.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lytp_main = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lytp_main.setMargins(dpToInt(15, context), dpToInt(15, context), dpToInt(15, context), 0);
        lyt_main.setLayoutParams(lytp_main);

        // LAYOUT TITLE
        LinearLayout lyt_title = new LinearLayout(context);
        lyt_title.setOrientation(LinearLayout.HORIZONTAL);
        if (hardware.is_isFunctional())
        {
            lyt_title.setBackgroundColor(context.getResources().getColor(R.color.nephritis));
        } else {
            lyt_title.setBackgroundColor(context.getResources().getColor(R.color.pomeGranate));
        }
        LinearLayout.LayoutParams lytp_title = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lyt_title.setLayoutParams(lytp_title);
        // --- TEXTVIEW ID
        TextView lbl_id = new TextView(context);
        lbl_id.setText("" + hardware.get_id());
        lbl_id.setTextColor(context.getResources().getColor(R.color.clouds));
        LinearLayout.LayoutParams lytp_lbl_id = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lytp_lbl_id.setMargins(dpToInt(15, context), dpToInt(6, context), dpToInt(0, context), dpToInt(10, context));
        lyt_title.addView(lbl_id, lytp_lbl_id);
        // --- TEXTVIEW TITLE
        TextView lbl_title = new TextView(context);
        lbl_title.setText("" + hardware.get_name());
        lbl_title.setTextSize(15);
        lbl_title.setTypeface(null, Typeface.BOLD);
        lbl_title.setTextColor(context.getResources().getColor(R.color.clouds));
        LinearLayout.LayoutParams lytp_lbl_title = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lytp_lbl_title.setMargins(dpToInt(15, context), dpToInt(7, context), dpToInt(0, context), dpToInt(7, context));
        lyt_title.addView(lbl_title, lytp_lbl_title);

        // LAYOUT DESCRIPTION
        LinearLayout lyt_description = new LinearLayout(context);
        lyt_description.setOrientation(LinearLayout.VERTICAL);
        if (hardware.is_isFunctional())
        {
            lyt_description.setBackgroundColor(context.getResources().getColor(R.color.emerald));
        } else {
            lyt_description.setBackgroundColor(context.getResources().getColor(R.color.alizarin));
        }
        LinearLayout.LayoutParams lytp_description = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lyt_description.setLayoutParams(lytp_description);
        // --- TEXTVIEW ISFUNCTIONAL
        TextView lbl_isFunctional = new TextView(context);
        if (hardware.is_isFunctional()) {
            lbl_isFunctional.setText(context.getResources().getString(R.string.chardware_functional));
        } else {
            lbl_isFunctional.setText(context.getResources().getString(R.string.chardware_notFunctional));
        }
        lbl_isFunctional.setTextColor(context.getResources().getColor(R.color.clouds));
        LinearLayout.LayoutParams lytp_lbl_isFunctional = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lytp_lbl_isFunctional.setMargins(dpToInt(5, context), dpToInt(5, context), dpToInt(5, context), dpToInt(5, context));
        lytp_lbl_title.setMargins(dpToInt(15, context), dpToInt(5, context), dpToInt(0, context), dpToInt(5, context));
        lyt_description.addView(lbl_isFunctional, lytp_lbl_isFunctional);

        lyt_main.addView(lyt_title);
        lyt_main.addView(lyt_description);

        Log.i("CustomLog","Le materiel " + hardware.get_name() + " a été ajouté.");

        return lyt_main;
    }

    /**
     * Retourne vrai si le materiel dispose d'un journal datant d'il y a moins d'une heure
     * @return
     */
    public boolean haveBeenRefresh() {
        boolean refreshed = false;
        try {
            Date last_log = null;
            List<CLog> logs = CConnection.getLogs(get_name());

            if (logs.size() > 0) {
                last_log = logs.get(0).get_breakdownDate();
                for (CLog log : logs) {
                    if (log.get_breakdownDate().after(last_log)) {
                        last_log = log.get_breakdownDate();
                    }
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
                String currentDateandTime = sdf.format(last_log);
                Date date = sdf.parse(currentDateandTime);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.HOUR, 1);

                Calendar now = Calendar.getInstance();

                if (last_log == null || now.getTime().after(calendar.getTime())) {
                    refreshed = false;
                } else {
                    refreshed = true;
                }
            } else {
                refreshed = false;
            }
        } catch (Exception e) {
            Log.e("CustomLog", e.getMessage());
        }
        return refreshed;
    }
}
