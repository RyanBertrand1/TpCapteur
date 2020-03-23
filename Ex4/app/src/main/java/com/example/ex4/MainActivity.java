package com.example.ex4;

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

    float x;
    float y;

    String directionX;
    String directionY;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        assert sensorManager != null;
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float nX = x - event.values[0];
                float nY = y - event.values[1];

                x = event.values[0];
                y = event.values[1];

                if (nX > 2){
                    directionX = "DROITE";
                }
                else if (nX < -2){
                    directionX = "GAUCHE";
                } else {
                    directionX = "";
                }

                if (nY > 2){
                    directionY = "HAUT";
                }
                else if (nY < -2){
                    directionY = "BAS";
                } else {
                    directionY = "";
                }

                String res = directionX + "\n" + directionY;

                textView.setText(res);
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
