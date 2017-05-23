package com.artie.gourmand.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.artie.gourmand.R;
import com.artie.gourmand.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        if(savedInstanceState == null) {
            ProfileFragment profileFragment = ProfileFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,
                            profileFragment,
                            "ProfileFragment")
                    .detach(profileFragment)
                    .commit();
        }
    }

    private void initInstances() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_camera);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_profile) {
            ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager().findFragmentByTag("ProfileFragment");
            getSupportFragmentManager().beginTransaction()
                    .attach(profileFragment)
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
