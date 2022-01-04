package com.mad.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.mad.miniproject.firstAid.FirstaidActivity;
import com.mad.miniproject.selfDefence.SelfdefenceActivity;
import com.mad.miniproject.lawsActs.LawsactsActivity;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bundle extras = getIntent().getExtras();
//        String V1 = extras.getString(Intent.EXTRA_TEXT);
//        Log.d("NumberMainActivity", V1);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = getIntent().getExtras();
        String V1 = extras.getString(Intent.EXTRA_TEXT);
        Log.d("NumberMainActivity", V1);
    }

    //    public void myProfile(View v){
//        Intent i = new Intent(getApplicationContext(), MyProfile.class);
//        startActivity(i);
//    }

    public void addRelative(View v){
        Intent i = new Intent(MainActivity.this, AddRelative.class);
        startActivity(i);
    }

    public void helplineNumbers(View v){
        Intent i = new Intent(MainActivity.this, helplineCall.class);
        startActivity(i);
    }

    public void triggers(View v){
        Intent i = new Intent(MainActivity.this, TrigActivity.class);
        startActivity(i);
    }

    public void firstAid(View v){
        Intent i = new Intent(MainActivity.this, FirstaidActivity.class);
        startActivity(i);
    }

    public void selfDefence(View v){
        Intent i = new Intent(MainActivity.this, SelfdefenceActivity.class);
        startActivity(i);
    }

    public void lawsActs(View v){
        Intent i = new Intent(MainActivity.this, LawsactsActivity.class);
        startActivity(i);
    }

    public void LogOut(View v){
        Intent i = new Intent(MainActivity.this, Login.class);
        startActivity(i);
    }


//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//
//        int action, keycode;
//
//        action = event.getAction();
//        keycode = event.getKeyCode();
//
//        switch (keycode)
//        {
//            case KeyEvent.KEYCODE_VOLUME_UP:
//                {
//                if(KeyEvent.ACTION_UP == action){
//                    count++;
//                    String S1 = String.valueOf(count);
//                    Log.d("upButton", S1);
//                }
//            }
//            case KeyEvent.KEYCODE_VOLUME_DOWN:
//                if(KeyEvent.ACTION_DOWN == action){
//                    count = 0;
//                    String S2 = String.valueOf(count);
//                    Log.d("downButton", S2);
//                }
//        }
//        return super.dispatchKeyEvent(event);
//    }
}