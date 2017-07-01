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
import com.artie.gourmand.adapter.SearchAdapter;
import com.artie.gourmand.dao.MemberItemCollectionDao;
import com.artie.gourmand.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewFriend;

    private SearchAdapter mSearchAdapter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setupData();
        initInstances();
    }

    private void setupData() {
        Call<MemberItemCollectionDao> call = HttpManager.getInstance().getService().loadMembers();

        call.enqueue(new Callback<MemberItemCollectionDao>() {
            @Override
            public void onResponse(Call<MemberItemCollectionDao> call, Response<MemberItemCollectionDao> response) {
                if (response.isSuccessful()) {
                    MemberItemCollectionDao dao = response.body();
                    mSearchAdapter.setDao(dao);
                    mSearchAdapter.notifyDataSetChanged();
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
        actionBar.setTitle("Search Friend");

        mSearchAdapter = new SearchAdapter(SearchActivity.this, new MemberItemCollectionDao());

        mRecyclerViewFriend = (RecyclerView) findViewById(R.id.recycler_view_select_friend);
        mRecyclerViewFriend.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewFriend.setAdapter(mSearchAdapter);
    }

}
