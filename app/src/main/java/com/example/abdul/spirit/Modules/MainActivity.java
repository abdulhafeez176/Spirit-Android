package com.example.abdul.spirit.Modules;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.abdul.spirit.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        navigationView = (BottomNavigationView)findViewById(R.id.mainWindowBottomNav);
        mToolbar = (Toolbar)findViewById(R.id.mainActivity_toolbar);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Spirit");



    }


}
