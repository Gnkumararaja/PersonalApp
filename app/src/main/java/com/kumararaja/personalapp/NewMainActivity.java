package com.kumararaja.personalapp;

import android.drm.DrmStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class NewMainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmain);

        toolbar=getSupportActionBar();

        BottomNavigationView nav=(BottomNavigationView)findViewById(R.id.navigationn);
        nav.setOnNavigationItemSelectedListener(mOnNavigation);
        toolbar.setTitle("Dashboard");

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigation
            =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Dashboard");
                    return true;

                case R.id.nav_ministatement:
                    toolbar.setTitle("Ministatement");
                    return true;

                case R.id.nav_credit:
                    toolbar.setTitle("Credit");
                    return true;

                case R.id.nav_debit:
                    toolbar.setTitle("Debit");
                    return true;

                case R.id.nav_sharee:
                    toolbar.setTitle("Share");
                    return true;
            }


            return false;
        }
    };
}
