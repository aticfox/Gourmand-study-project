package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.artie.gourmand.R;
import com.artie.gourmand.adapter.UserAdapter;
import com.artie.gourmand.dao.MemberItemCollectionDao;
import com.artie.gourmand.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingActivity extends AppCompatActivity {

    private static int MOCK_MEMBER_ID = 1;

    RecyclerView mRecyclerViewFollowing;
    private UserAdapter mUserAdapter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, FollowingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);

        initInstances();
        setupData();
    }

    private void setupData() {
        Call<MemberItemCollectionDao> call = HttpManager.getInstance().getService().loadFollowing(MOCK_MEMBER_ID);

        call.enqueue(new Callback<MemberItemCollectionDao>() {
            @Override
            public void onResponse(Call<MemberItemCollectionDao> call, Response<MemberItemCollectionDao> response) {
                if (response.isSuccessful()) {
                    MemberItemCollectionDao dao = response.body();
                    mUserAdapter.setDao(dao);
                    mUserAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MemberItemCollectionDao> call, Throwable t) {
                Log.d("Search", "Fail load data: " + t.toString());
            }
        });
    }

    private void initInstances() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Followings");

        mUserAdapter = new UserAdapter(FollowingActivity.this, new MemberItemCollectionDao());

        mRecyclerViewFollowing = (RecyclerView) findViewById(R.id.recycler_view_following);
        mRecyclerViewFollowing.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewFollowing.setAdapter(mUserAdapter);
    }

}
