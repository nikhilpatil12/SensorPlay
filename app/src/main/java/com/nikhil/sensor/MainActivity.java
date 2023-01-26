package com.nikhil.sensor;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_GRAVITY;
import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.Sensor.TYPE_LIGHT;
import static android.hardware.Sensor.TYPE_PROXIMITY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lSensor, gSensor, aSensor, gyroSensor, proximitySensor;
    TextView lightTextView, gravityTextView, accelerometerTextView, gyroTextView, proximityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);
        gSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        aSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }
    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==TYPE_LIGHT){
            float lux = event.values[0];
            Log.d("Light",lux+"");
            lightTextView = findViewById(R.id.txtLightSensor);
            lightTextView.setText(lux+"");
        }
        if(event.sensor.getType()==TYPE_PROXIMITY){
            float distance = event.values[0];
            Log.d("Proximity",distance+"");
            proximityTextView = findViewById(R.id.txtProximity);
            proximityTextView.setText(distance+"");
        }
        if(event.sensor.getType()==TYPE_GRAVITY){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            Log.d("Gravity","x: "+x+", y: "+ y+ ", z: "+z);
            gravityTextView = findViewById(R.id.txtGravitySensor);
            gravityTextView.setText("x: "+x+", y: "+ y+ ", z: "+z);
        }
        if(event.sensor.getType()==TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            Log.d("Accelerometer","x: "+x+", y: "+ y+ ", z: "+z);
            accelerometerTextView = findViewById(R.id.txtAccelerometer);
            accelerometerTextView.setText("x: "+x+", y: "+ y+ ", z: "+z);
        }
        if(event.sensor.getType()==TYPE_GYROSCOPE){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            Log.d("Gyroscope","x: "+x+", y: "+ y+ ", z: "+z);
            gyroTextView = findViewById(R.id.txtGyro);
            gyroTextView.setText("x: "+x+", y: "+ y+ ", z: "+z);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, aSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}