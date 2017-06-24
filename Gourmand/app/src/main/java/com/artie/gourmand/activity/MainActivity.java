package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    static final String EXTRA_KEY_LAUNCH_SCREEN = "LaunchScreen";

    public static final int LAUNCH_SCREEN_FEED = 0;
    public static final int LAUNCH_SCREEN_PROFILE = 1;

    private int mLaunchScreen;

    public static Intent getStartIntent(Context context, int launchScreen) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_KEY_LAUNCH_SCREEN, launchScreen);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindData();
        initInstances();

        if(savedInstanceState == null) {
            bindFragment();
        }
    }

    private void bindData() {
        mLaunchScreen = getIntent().getIntExtra(EXTRA_KEY_LAUNCH_SCREEN, LAUNCH_SCREEN_FEED);
    }

    private void initInstances() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_camera);
    }

    private void bindFragment() {
        ProfileFragment profileFragment = ProfileFragment.newInstance();
        FeedFragment feedFragment = FeedFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_container,
                        feedFragment,
                        FRAGMENT_FEED)
                .add(R.id.content_container,
                        profileFragment,
                        FRAGMENT_PROFILE)
                .commit();

        Fragment detachFragment;
        switch (mLaunchScreen) {
            case LAUNCH_SCREEN_PROFILE:
                detachFragment = feedFragment;
                break;
            default:
                detachFragment = profileFragment;
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .detach(detachFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
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
            case R.id.menu_search:
                intent = SearchActivity.getStartIntent(MainActivity.this);
                startActivity(intent);
                break;
            case android.R.id.home:
                intent = TakePhotoActivity.getStartIntent(MainActivity.this);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
