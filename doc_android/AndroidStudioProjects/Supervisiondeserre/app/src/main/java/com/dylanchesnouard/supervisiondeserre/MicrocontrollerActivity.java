package com.dylanchesnouard.supervisiondeserre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

public class MicrocontrollerActivity extends AppCompatActivity {

    // ATTRIBUTES
    LinearLayout layout_microcontrollers;
    List<CHardware> microcontrollers;

    // CONSTRUCTOR
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microcontroller);

        // CONTROLS
        layout_microcontrollers = findViewById(R.id.layout_microcontrollers);

        // LOAD DATA
        displayMicrocontrollers();
    }

    // METHODS

    /**
     * Affiche les microcontroleurs
     */
    private void displayMicrocontrollers() {
        try {
            microcontrollers = CConnection.getHardware(CHardware.HardwareType.Microcontroller);
            for (final CHardware microcontroller : microcontrollers) {
                LinearLayout sensorLayout = CHardware.toLayout(microcontroller, this);
                sensorLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent logActivityIntent = new Intent(MicrocontrollerActivity.this, LogActivity.class);
                        logActivityIntent.putExtra("EXTRA_FILTER",microcontroller.get_name());
                        startActivity(logActivityIntent);
                    }
                });
                layout_microcontrollers.addView(sensorLayout);
            }
        } catch (Exception e) {
            Log.i("CustomLog", e.getMessage());
        }
    }
}
