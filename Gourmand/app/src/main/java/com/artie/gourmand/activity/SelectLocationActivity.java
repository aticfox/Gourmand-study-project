package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.artie.gourmand.R;

public class SelectLocationActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SelectLocationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
    }

}
