package com.dylanchesnouard.supervisiondeserre;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    // ATTRIBUTES
    LinearLayout layout_sensors;
    List<CHardware> sensors;

    // CONSTRUCTOR
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        // CONTROLS
        layout_sensors = findViewById(R.id.layout_sensors);

        // LOAD DATA
        displaySensors();
    }

    // METHODS

    /**
     * Affiche les capteurs
     */
    private void displaySensors() {
        try {
            sensors = CConnection.getHardware(CHardware.HardwareType.Sensor);
            for (final CHardware sensor : sensors) {
                LinearLayout sensorLayout = CHardware.toLayout(sensor, this);
                sensorLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent logActivityIntent = new Intent(SensorActivity.this, LogActivity.class);
                        logActivityIntent.putExtra("EXTRA_FILTER",sensor.get_name());
                        startActivity(logActivityIntent);
                    }
                });
                layout_sensors.addView(sensorLayout);
            }
        } catch (Exception e) {
            Log.i("CustomLog", e.getMessage());
        }
    }

}
