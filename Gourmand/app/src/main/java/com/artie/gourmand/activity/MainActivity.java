package com.artie.gourmand.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.artie.gourmand.R;
import com.artie.gourmand.fragment.FeedFragment;
import com.artie.gourmand.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    static final String FRAGMENT_FEED = "FeedFragment";
    static final String FRAGMENT_PROFILE = "ProfileFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        if(savedInstanceState == null) {
            ProfileFragment profileFragment = ProfileFragment.newInstance();
            FeedFragment feedFragment = FeedFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,
                            feedFragment,
                            FRAGMENT_FEED)
                    .add(R.id.contentContainer,
                            profileFragment,
                            FRAGMENT_PROFILE)
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
        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager()
                .findFragmentByTag(FRAGMENT_PROFILE);
        FeedFragment feedFragment = (FeedFragment) getSupportFragmentManager()
                .findFragmentByTag(FRAGMENT_FEED);

        switch (item.getItemId()) {
            case R.id.menu_profile:
               getSupportFragmentManager().beginTransaction()
                        .detach(feedFragment)
                        .attach(profileFragment)
                        .commit();
                break;
            case R.id.menu_feed:
                getSupportFragmentManager().beginTransaction()
                        .detach(profileFragment)
                        .attach(feedFragment)
                        .commit();
                break;
            case android.R.id.home:
                Intent intent = TakePhotoActivity.getStartIntent(MainActivity.this);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
