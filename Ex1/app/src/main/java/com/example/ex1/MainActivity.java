package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);


        List<Sensor> liste = sensorManager.getSensorList(Sensor.TYPE_ALL);

        test = findViewById(R.id.test);

        String res = "";

        for (Sensor sensor : liste) {
            res += "- " + sensor.getName() + "\n";
        }

        test.setText(res);
    }
}
