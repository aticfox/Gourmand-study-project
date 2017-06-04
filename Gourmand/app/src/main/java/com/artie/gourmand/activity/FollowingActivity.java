package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.artie.gourmand.R;
import com.artie.gourmand.adapter.FollowingAdapter;

public class FollowingActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewFollowing;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, FollowingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);

        initInstances();
    }

    private void initInstances() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Followings");

        mRecyclerViewFollowing = (RecyclerView) findViewById(R.id.recycler_view_following);
        mRecyclerViewFollowing.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewFollowing.setAdapter(new FollowingAdapter());
    }

}
