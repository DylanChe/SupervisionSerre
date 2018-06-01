package com.dylanchesnouard.supervisiondeserre;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CLog {

    // ATTRIBUTES
    private int _id;
    private boolean _isFunctional;
    private Date _breakdownDate;
    private String _hardwareName;

    // CONSTRUCTOR
    public CLog(int id, boolean isFunctional, String breakdownDate, String hardwareName) {
        _id = id;
        _isFunctional = isFunctional;
        _hardwareName = hardwareName;
        _breakdownDate = stringToDate(breakdownDate);

        Log.i("CustomLog", "*** Journal :");
        Log.i("CustomLog", " - Nom du materiel : " + _hardwareName);
        Log.i("CustomLog", " - ID : " + _id);
        Log.i("CustomLog", " - Est fonctional : " + _isFunctional);
        Log.i("CustomLog", " - Date de la panne : " + _breakdownDate);
    }

    // GETTERS & SETTERS
    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
    public boolean is_isFunctional() {
        return _isFunctional;
    }
    public void set_isFunctional(boolean _isFunctional) {
        this._isFunctional = _isFunctional;
    }
    public Date get_breakdownDate() {
        return _breakdownDate;
    }
    public void set_breakdownDate(Date _breakdownDate) {
        this._breakdownDate = _breakdownDate;
    }
    public String get_hardwareName() {
        return _hardwareName;
    }
    public String getBreakdownDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String jourPanne = dateFormat.format(_breakdownDate);
        return jourPanne;
    }
    public String getBreakdownHour() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String heurePanne = dateFormat.format(_breakdownDate);
        return heurePanne;
    }
    public void set_hardwareName(String _hardwareName) {
        this._hardwareName = _hardwareName;
    }

    // METHODS

    /**
     * Converti un string en date
     * @param _date
     * @return
     */
    private Date stringToDate(String _date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

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
     * Retourne un LinearLayout qui résume le journal passé en paramètre
     * @param log
     * @param context
     * @return
     */
    public static LinearLayout toLayout(final CLog log, final Context context) {
        // LAYOUT_MAIN
        LinearLayout lyt_main = new LinearLayout(context);
        lyt_main.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lytp_main = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lytp_main.topMargin = dpToInt(15, context);
        lytp_main.leftMargin = dpToInt(15, context);
        lytp_main.rightMargin = dpToInt(15, context);
        lyt_main.setLayoutParams(lytp_main);

        // LAYOUT_DATE
        LinearLayout lyt_date = new LinearLayout(context);
        lyt_date.setOrientation(LinearLayout.VERTICAL);
        if (log.is_isFunctional())
        {
            lyt_date.setBackgroundColor(context.getResources().getColor(R.color.nephritis));
        } else {
            lyt_date.setBackgroundColor(context.getResources().getColor(R.color.pomeGranate));
        }
        LinearLayout.LayoutParams lytp_date = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lyt_date.setLayoutParams(lytp_date);

        // --- TEXTVIEW DAY
        TextView lbl_day = new TextView(context);
        lbl_day.setText(log.getBreakdownDay());
        lbl_day.setTextColor(context.getResources().getColor(R.color.clouds));
        LinearLayout.LayoutParams lytp_lbl_day = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lytp_lbl_day.leftMargin = dpToInt(10, context);
        lytp_lbl_day.topMargin = dpToInt(5, context);
        lytp_lbl_day.rightMargin = dpToInt(10, context);
        lyt_date.addView(lbl_day, lytp_lbl_day);

        // --- TEXTVIEW HOUR
        TextView lbl_hour = new TextView(context);
        lbl_hour.setGravity(Gravity.CENTER);
        lbl_hour.setText(log.getBreakdownHour());
        lbl_hour.setTextColor(context.getResources().getColor(R.color.clouds));
        LinearLayout.LayoutParams lytp_lbl_hour = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lytp_lbl_hour.leftMargin = dpToInt(5, context);
        lytp_lbl_hour.topMargin = dpToInt(5, context);
        lytp_lbl_hour.rightMargin = dpToInt(5, context);
        lyt_date.addView(lbl_hour, lytp_lbl_hour);

        // LAYOUT_ID
        LinearLayout lyt_id = new LinearLayout(context);
        if (log.is_isFunctional())
        {
            lyt_id.setBackgroundColor(context.getResources().getColor(R.color.emerald));
        } else {
            lyt_id.setBackgroundColor(context.getResources().getColor(R.color.alizarin));
        }
        LinearLayout.LayoutParams lytp_id = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lyt_id.setLayoutParams(lytp_id);

        // --- TEXTVIEW ID
        TextView lbl_id = new TextView(context);
        lbl_id.setGravity(Gravity.CENTER);
        lbl_id.setText("" + log.get_id());
        lbl_id.setTextColor(context.getResources().getColor(R.color.clouds));
        LinearLayout.LayoutParams lytp_lbl_id = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lytp_lbl_id.leftMargin = dpToInt(10, context);
        lytp_lbl_id.rightMargin = dpToInt(10, context);
        lyt_id.addView(lbl_id, lytp_lbl_id);

        // LAYOUT_SPACE
        LinearLayout lyt_space = new LinearLayout(context);
        if (log.is_isFunctional())
        {
            lyt_space.setBackgroundColor(context.getResources().getColor(R.color.nephritis));
        } else {
            lyt_space.setBackgroundColor(context.getResources().getColor(R.color.pomeGranate));
        }
        lyt_space.setMinimumWidth(dpToInt(5, context));
        LinearLayout.LayoutParams lytp_space = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lyt_space.setLayoutParams(lytp_space);

        // LAYOUT_SENSOR
        LinearLayout lyt_sensor = new LinearLayout(context);
        lyt_sensor.setGravity(Gravity.CENTER);
        lyt_sensor.setOrientation(LinearLayout.VERTICAL);
        if (log.is_isFunctional())
        {
            lyt_sensor.setBackgroundColor(context.getResources().getColor(R.color.emerald));
        } else {
            lyt_sensor.setBackgroundColor(context.getResources().getColor(R.color.alizarin));
        }
        LinearLayout.LayoutParams lytp_sensor = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lyt_sensor.setLayoutParams(lytp_sensor);

        // --- TEXTVIEW NAME
        TextView lbl_sensorName = new TextView(context);
        lbl_sensorName.setGravity(Gravity.CENTER);
        lbl_sensorName.setText("" + log.get_hardwareName());
        lbl_sensorName.setTypeface(null, Typeface.BOLD);
        lbl_sensorName.setTextColor(context.getResources().getColor(R.color.clouds));
        LinearLayout.LayoutParams lytp_lbl_sensorName = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lyt_sensor.addView(lbl_sensorName, lytp_lbl_sensorName);

        // --- TEXTVIEW FUNCTIONAL
        TextView lbl_functional = new TextView(context);
        lbl_functional.setGravity(Gravity.CENTER);
        if (log.is_isFunctional()) {
            lbl_functional.setText(context.getResources().getString(R.string.clog_functional));
        } else {
            lbl_functional.setText(context.getResources().getString(R.string.clog_notFunctional));
        }
        lbl_functional.setTextColor(context.getResources().getColor(R.color.clouds));
        LinearLayout.LayoutParams lytp_lbl_functional = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lyt_sensor.addView(lbl_functional, lytp_lbl_functional);

        lyt_main.addView(lyt_date);
        lyt_main.addView(lyt_id);
        lyt_main.addView(lyt_space);
        lyt_main.addView(lyt_sensor);

        Log.i("CustomLog","Le journal numéro " + log.get_id() + " a été ajouté.");

        return lyt_main;
    }
}
