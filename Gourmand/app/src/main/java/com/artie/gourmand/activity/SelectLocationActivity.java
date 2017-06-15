package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.artie.gourmand.R;
import com.artie.gourmand.adapter.SelectLocationAdapter;

public class SelectLocationActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    private String[] mLocationNames;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SelectLocationActivity.class);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        setupData();
        initInstances();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void setupData() {
        mLocationNames = getResources().getStringArray(R.array.location_names);
    }

    private void initInstances() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_select_location);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(new SelectLocationAdapter(mLocationNames));
    }

}
