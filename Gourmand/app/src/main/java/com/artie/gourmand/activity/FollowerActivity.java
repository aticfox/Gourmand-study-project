package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.artie.gourmand.R;
import com.artie.gourmand.adapter.FollowerAdapter;

public class FollowerActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewFollower;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, FollowerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);

        initInstances();
    }

    private void initInstances() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Followers");

        mRecyclerViewFollower = (RecyclerView) findViewById(R.id.recycler_view_follower);
        mRecyclerViewFollower.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewFollower.setAdapter(new FollowerAdapter());
    }

}
