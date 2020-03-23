package com.example.ex3;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    SensorEventListener sensorEventListener;
    Sensor accelerometre;

    TextView test;
    LinearLayout linearLayout;

    float lx = 0;
    float ly = 0;
    float lz = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = findViewById(R.id.test);
        linearLayout = findViewById(R.id.layout);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        assert sensorManager != null;
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                float dx = x - lx;
                float dy = x - lx;
                float dz = x - lx;

                lx = x;
                ly = y;
                lz = z;

                float res = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);

                if (res < 30) {
                    linearLayout.setBackgroundColor(Color.GREEN);
                } else if (res >= 30 && res < 60) {
                    linearLayout.setBackgroundColor(Color.BLACK);
                } else {
                    linearLayout.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, accelerometre, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }
}
