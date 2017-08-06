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
import com.artie.gourmand.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowerActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewFollower;
    private UserAdapter mUserAdapter;
    private int mMemberID;

    public static Intent getStartIntent(Context context, int memberID) {
        Intent intent = new Intent(context, FollowerActivity.class);
        intent.putExtra("member_id", memberID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);

        mMemberID = getIntent().getIntExtra("member_id", 0);

        initInstances();
        setupData();
    }

    private void setupData() {
        Call<MemberItemCollectionDao> call = HttpManager.getInstance().getService().loadFollowers(mMemberID, User.getInstance().getDao().getId());

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
        actionBar.setTitle("Followers");

        mUserAdapter = new UserAdapter(FollowerActivity.this, new MemberItemCollectionDao());

        mRecyclerViewFollower = (RecyclerView) findViewById(R.id.recycler_view_follower);
        mRecyclerViewFollower.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewFollower.setAdapter(mUserAdapter);
    }

}
