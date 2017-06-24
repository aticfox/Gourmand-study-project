package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.artie.gourmand.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }
    
}
