package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.artie.gourmand.R;

public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTextViewSelectLocation;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CreatePostActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        initInstances();
    }

    private void initInstances() {
        mTextViewSelectLocation = (TextView) findViewById(R.id.text_select_location);
        mTextViewSelectLocation.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_post:
                Intent intent = MainActivity.getStartIntent(CreatePostActivity.this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_select_location:
                Intent intent = SelectLocationActivity.getStartIntent(CreatePostActivity.this);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
