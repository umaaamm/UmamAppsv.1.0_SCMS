package com.fun_corp.umamappsv10;

import android.os.Bundle;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    Intent i;
    int delay = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        Timer waktu = new Timer();
        TimerTask show = new TimerTask() {
            @Override
            public void run() {
                finish();
                i = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
            }
        };
        waktu.schedule(show,delay);
    }
}