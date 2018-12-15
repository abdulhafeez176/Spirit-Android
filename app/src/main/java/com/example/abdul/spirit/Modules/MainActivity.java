package com.example.abdul.spirit.Modules;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.abdul.spirit.Fragments.MatchesFragment;
import com.example.abdul.spirit.Fragments.ProfileFragment;
import com.example.abdul.spirit.Fragments.TeamsFragment;
import com.example.abdul.spirit.R;
import com.example.abdul.spirit.Utils.BottomNavigationBehavior;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private Toolbar mToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        navigationView = (BottomNavigationView)findViewById(R.id.mainWindowBottomNav);
        mToolbar = (Toolbar)findViewById(R.id.mainActivity_toolbar);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Spirit");


        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());





    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_menu_matches:
                    fragment = new MatchesFragment();
                    getSupportActionBar().setTitle("Matches");
                    loadFragment(fragment);
                    return true;
                case R.id.nav_menu_teams:
                    fragment = new TeamsFragment();
                    getSupportActionBar().setTitle("Teams");
                    loadFragment(fragment);
                    return true;
                case R.id.nav_menu_profile:
                    fragment = new ProfileFragment();
                    getSupportActionBar().setTitle("Profile");
                    loadFragment(fragment);
                    return true;

            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
