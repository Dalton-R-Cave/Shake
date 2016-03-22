package com.cave.r.dalton.shakepractice;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView myText = null;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                handleShakeEvent(count);
            }
        });
        super.onCreate(savedInstanceState);

        view = new View(this);
        setContentView(view);
    }

    @Override
    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onPause(){
        mSensorManager.unregisterListener((mShakeDetector));
        super.onPause();
    }

    public void handleShakeEvent(int count){
        view.update(count + " Shakes");
    }

}
