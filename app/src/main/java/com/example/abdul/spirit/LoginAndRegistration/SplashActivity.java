package com.example.abdul.spirit.LoginAndRegistration;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abdul.spirit.Modules.MainActivity;
import com.example.abdul.spirit.R;

public class SplashActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent gotoMainActivity = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(gotoMainActivity);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.postDelayed(runnable,2000);
    }
}
