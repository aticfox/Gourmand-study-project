package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.view.SquareImageView;

public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener {

    static final String INTENT_EXTRA_IMAGE_URI = "imageURI";

    TextView mTextViewSelectLocation;
    SquareImageView mSquareImageViewPost;

    private Uri mImageURI;

    public static Intent getStartIntent(Context context, Uri imageURI) {
        Intent intent = new Intent(context, CreatePostActivity.class);
        intent.putExtra(INTENT_EXTRA_IMAGE_URI, imageURI.toString());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        mImageURI = Uri.parse(getIntent().getStringExtra(INTENT_EXTRA_IMAGE_URI));

        initInstances();
    }

    private void initInstances() {
        mTextViewSelectLocation = (TextView) findViewById(R.id.text_select_location);
        mTextViewSelectLocation.setOnClickListener(this);

        mSquareImageViewPost = (SquareImageView) findViewById(R.id.image_post);
        mSquareImageViewPost.setImageURI(mImageURI);
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
                Intent intent = MainActivity.getStartIntent(CreatePostActivity.this, MainActivity.LAUNCH_SCREEN_FEED);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.text_select_location:
                intent = SelectLocationActivity.getStartIntent(CreatePostActivity.this);
                break;
            default:
                return;
        }

        startActivity(intent);
    }

}
