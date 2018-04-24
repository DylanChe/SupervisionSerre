package com.dylanchesnouard.supervisiondeserre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    // CONTROLS & LISTENERS
    private TextView dashboard_lbl_sensorsNumber;
    private TextView dashboard_lbl_microcontrollersNumber;
    private TextView dashboard_lbl_logsNumber;
    private LinearLayout dashboard_layout_alerts;
    private LinearLayout dashboard_layout_sensors;
    private LinearLayout dashboard_layout_microcontrollers;
    private LinearLayout dashboard_layout_logs;

    /**
     * Méthode appelée à la création de la fenêtre.
     * Permet de lier les attributs et les composants (boutons...) ainsi que d'ajouter les listeners.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // FIND CONTROLS & SET LISTENERS
        dashboard_lbl_sensorsNumber = findViewById(R.id.dashboard_lbl_sensorsNumber);
        dashboard_lbl_microcontrollersNumber = findViewById(R.id.dashboard_lbl_microcontrollersNumber);
        dashboard_lbl_logsNumber = findViewById(R.id.dashboard_lbl_logsNumber);
        dashboard_layout_alerts = findViewById(R.id.dashboard_layout_alerts);
        dashboard_layout_sensors = findViewById(R.id.dashboard_layout_sensors);
        dashboard_layout_sensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorIntent = new Intent(DashboardActivity.this, SensorActivity.class);
                startActivity(sensorIntent);
            }
        });
        dashboard_layout_microcontrollers = findViewById(R.id.dashboard_layout_microcontrollers);
        dashboard_layout_microcontrollers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorIntent = new Intent(DashboardActivity.this, MicrocontrollerActivity.class);
                startActivity(sensorIntent);
            }
        });
        dashboard_layout_logs = findViewById(R.id.dashboard_layout_logs);
        dashboard_layout_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorIntent = new Intent(DashboardActivity.this, LogActivity.class);
                startActivity(sensorIntent);
            }
        });

        // LOAD DATA
        loadElementsNumber();
    }

    /**
     * Méthode permettant d'afficher le nombre de chaque element (capteurs, microcontrolleurs, journaux)
     */
    private void loadElementsNumber() {
        displayAlerts();
        dashboard_lbl_sensorsNumber.setText("" + CConnection.getHardwareNumber(CHardware.HardwareType.Sensor));
        dashboard_lbl_microcontrollersNumber.setText("" + CConnection.getHardwareNumber((CHardware.HardwareType.Microcontroller)));
        dashboard_lbl_logsNumber.setText("" + CConnection.getLogsNumber());
    }

    /**
     * Permet de detecter differantes erreurs et d'afficher une alerte pour chacunes d'elles
     */
    private void displayAlerts() {
        try {
            if (CConnection.getHardwareNumber(CHardware.HardwareType.Microcontroller) > 0) {
                List<CHardware> microcontrollers = CConnection.getHardware(CHardware.HardwareType.Microcontroller);
                CHardware raspberry = null;
                for (CHardware controller : microcontrollers) {
                    if (controller.get_shortName().equals("raspberry")) {
                        raspberry = controller;
                    }
                    if (!controller.is_isFunctional()) {
                        dashboard_layout_alerts.addView(CAlert.alertToLayout("Le microcontroleur " + controller.get_name() + " ne fonctionne pas !", getString(R.string.cnotification_error_raspberryDown), this));
                    }
                    if (!controller.haveBeenRefresh()) {
                        dashboard_layout_alerts.addView(CAlert.alertToLayout("Manque de journaux pour le microcontroleur " + controller.get_name() + " !", this));
                    }
                }
                if (raspberry == null){
                    dashboard_layout_alerts.addView(CAlert.alertToLayout("Le microcontroleur Raspberry n\'est pas inscrit dans la base de données !", this));
                }
            }
            if (CConnection.getHardwareNumber(CHardware.HardwareType.Sensor) > 0) {
                List<CHardware> sensors = CConnection.getHardware(CHardware.HardwareType.Sensor);
                for (CHardware sensor : sensors) {
                    if (!sensor.is_isFunctional()) {
                        dashboard_layout_alerts.addView(CAlert.alertToLayout("Le capteur " + sensor.get_name() + " ne fonctionne pas !", this));
                    }
                    if (!sensor.haveBeenRefresh()) {
                        dashboard_layout_alerts.addView(CAlert.alertToLayout("Manque de journaux pour le capteur " + sensor.get_name() + " !", this));
                    }
                }
            }

        } catch (Exception e) {
            Log.i("CustomLog", e.getMessage());
        }
    }
}
