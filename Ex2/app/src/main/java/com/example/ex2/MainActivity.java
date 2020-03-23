package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button ProximiteButton;
    Button AccelerometreButton;
    Button GyroscopeButton;
    Button MagnetometreButton;

    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);


        ProximiteButton = findViewById(R.id.button_proximite);
        AccelerometreButton = findViewById(R.id.button_accelerometre);
        GyroscopeButton = findViewById(R.id.button_gyroscope);
        MagnetometreButton = findViewById(R.id.button_magnetometre);

        ProximiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectCapteur(Sensor.TYPE_PROXIMITY);
            }
        });

        AccelerometreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectCapteur(Sensor.TYPE_ACCELEROMETER);
            }
        });

        GyroscopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectCapteur(Sensor.TYPE_GYROSCOPE);
            }
        });

        MagnetometreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectCapteur(Sensor.TYPE_MAGNETIC_FIELD);
            }
        });
    }

    private void detectCapteur(int type) {
        Sensor sensor = sensorManager.getDefaultSensor(type);
        if(sensor != null) {
            Toast.makeText(this, "Capteur présent",
                    Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Capteur absent",
                    Toast.LENGTH_LONG).show();
        }
    }
}
