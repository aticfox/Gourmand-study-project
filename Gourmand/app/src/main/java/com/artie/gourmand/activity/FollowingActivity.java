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
import com.artie.gourmand.model.User;

import java.util.ArrayList;
import java.util.List;

public class FollowingActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewFollowing;

    private List<User> mUsers = new ArrayList<>();

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, FollowingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);

        setupData();
        initInstances();
    }

    private void setupData() {
        String[] userNames = getResources().getStringArray(R.array.user_names);
        int[] userImageIDs = new int[]{
                R.drawable.image_profile,
                R.drawable.image_profile2,
                R.drawable.image_profile3,
                R.drawable.image_profile4,
                R.drawable.image_profile5,
                R.drawable.image_profile6,
                R.drawable.image_profile7,
                R.drawable.image_profile8,
                R.drawable.image_profile9,
                R.drawable.image_profile10,
                R.drawable.image_profile11
        };
        Boolean[] userFollowings = new Boolean[]{
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true
        };

        for(int i = 0; i< userNames.length; i++) {
            User user = new User(userImageIDs[i], userNames[i], userFollowings[i]);
            mUsers.add(user);
        }
    }

    private void initInstances() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Followings");

        mRecyclerViewFollowing = (RecyclerView) findViewById(R.id.recycler_view_following);
        mRecyclerViewFollowing.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewFollowing.setAdapter(new FollowingAdapter(mUsers));
    }

}
