package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.artie.gourmand.R;

public class PostPhotoActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, PostPhotoActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_photo);
    }

}
