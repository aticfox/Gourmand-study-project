package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.artie.gourmand.R;
import com.artie.gourmand.adapter.SearchAdapter;
import com.artie.gourmand.dao.MemberItemCollectionDao;
import com.artie.gourmand.dao.MemberItemDao;
import com.artie.gourmand.manager.HttpManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewFriend;
    EditText mEditTextSearch;

    private MemberItemCollectionDao mDao;
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
                    mDao = response.body();
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

        mEditTextSearch = (EditText) findViewById(R.id.edit_text_search);
        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MemberItemCollectionDao dao;
                if (s.toString().isEmpty()) {
                    dao = new MemberItemCollectionDao(new ArrayList<MemberItemDao>());
                } else {
                    dao = filter(mDao, s.toString());
                }
                mSearchAdapter.setDao(dao);
                mSearchAdapter.notifyDataSetChanged();
            }
        });
        mSearchAdapter = new SearchAdapter(SearchActivity.this, new MemberItemCollectionDao());

        mRecyclerViewFriend = (RecyclerView) findViewById(R.id.recycler_view_select_friend);
        mRecyclerViewFriend.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewFriend.setAdapter(mSearchAdapter);
    }

    private MemberItemCollectionDao filter(MemberItemCollectionDao mDao, String keyword) {
        List<MemberItemDao> members = new ArrayList<>();
        for (int i=0; i<mDao.getMembers().size(); i++) {
            MemberItemDao member = mDao.getMembers().get(i);
            if (isMemberNameSameKeyword(member.getName(), keyword)) {
                members.add(member);
            }
        }
        return new MemberItemCollectionDao(members);
    }

    private boolean isMemberNameSameKeyword(String name, String keyword) {
        return name.toLowerCase().contains(keyword.toLowerCase());
    }

}
