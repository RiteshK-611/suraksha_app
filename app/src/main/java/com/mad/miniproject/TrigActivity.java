package com.mad.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import android.location.LocationManager;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
public class TrigActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    private LocationRequest locationRequest;
    DatabaseHelper myDB;
    String str_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trig);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }



    private SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(TrigActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    if (isGPSEnabled()) {
                        LocationServices.getFusedLocationProviderClient(TrigActivity.this)
                                .requestLocationUpdates(locationRequest, new LocationCallback() {
                                    @Override
                                    public void onLocationResult(@NonNull LocationResult locationResult) {
                                        super.onLocationResult(locationResult);
                                        LocationServices.getFusedLocationProviderClient(TrigActivity.this)
                                                .removeLocationUpdates(this);

                                        double latitude;
                                        double longitude;

                                        if(locationResult != null && locationResult.getLocations().size()>0){
                                            int index =  locationResult.getLocations().size()-1;
                                             latitude = locationResult.getLocations().get(index).getLatitude();
                                             longitude = locationResult.getLocations().get(index).getLongitude();

                                            if(ContextCompat.checkSelfPermission(TrigActivity.this, Manifest.permission.SEND_SMS)
                                                    == PackageManager.PERMISSION_GRANTED){
                                                sendSMS(latitude, longitude);
                                            } else{
                                                ActivityCompat.requestPermissions(TrigActivity.this,
                                                        new String[]{Manifest.permission.SEND_SMS}
                                                        ,100);
                                            }
                                            if(ContextCompat.checkSelfPermission(TrigActivity.this, Manifest.permission.CALL_PHONE)
                                                    == PackageManager.PERMISSION_GRANTED){
                                                callPolice();
                                            } else{
                                                ActivityCompat.requestPermissions(TrigActivity.this,
                                                        new String[]{Manifest.permission.CALL_PHONE}
                                                        ,100);
                                            }



                                        }
                                    }
                                }, null);

                    } else {
                        turnOnGPS();
                    }


                } else {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
                mSensorManager.unregisterListener(mSensorListener);
                mSensorListener = null;
            }




        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);




    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorListener);
        mSensorListener = null;


    }



    private void sendSMS(double sPhone, double smsText){

        if(sPhone!=0 && smsText!=0){
            myDB = new DatabaseHelper(this);

            //populate an ArrayList<String> from the database and then view it
            //ArrayList<String> contacts = new ArrayList<>();
            Cursor data = myDB.getListContents();
            if(data.getCount() == 0){
                Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
            }else{
                SmsManager smsManager = SmsManager.getDefault();
                while(data.moveToNext())
                {
                    String num = data.getString(1);
                    smsManager.sendTextMessage(num, null, sPhone+" "+smsText+" ", null, null);
                }
                data.close();

            }

        } else{
            Toast.makeText(getApplicationContext(), "Enter value first", Toast.LENGTH_SHORT).show();

        }


    }

    private void turnOnGPS() {


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(TrigActivity.this, "GPS is already turned on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(TrigActivity.this, 2);

                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });
    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;
        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }

    public void callPolice(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:100"));
        startActivity(intent);
    }


}