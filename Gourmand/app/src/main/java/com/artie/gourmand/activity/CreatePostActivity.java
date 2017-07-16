package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.dao.PostItemDao;
import com.artie.gourmand.manager.HttpManager;
import com.artie.gourmand.view.SquareImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener {

    static final String INTENT_EXTRA_IMAGE_URI = "imageURI";
    static final int MOCK_DATA_MEMBER_ID = 1;
    static final String MOCK_DATA_IMAGE_URL = "http://img.taste.com.au/q34WYzLy/w720-h480-cfill-q80/taste/2016/11/basic-pancakes-78986-1.jpeg";
    static final double MOCK_DATA_LOCATION_LATITUDE = 14;
    static final double MOCK_DATA_LOCATION_LONGITUDE = 17;
    static final String MOCK_DATA_LOCATION_NAME = "waffer cafee";

    TextView mTextViewSelectLocation;
    SquareImageView mSquareImageViewPost;
    EditText mEditTextCaption;

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
        mEditTextCaption = (EditText) findViewById(R.id.edit_text_caption);
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
                createPost(MOCK_DATA_MEMBER_ID,
                        MOCK_DATA_IMAGE_URL,
                        mEditTextCaption.getText().toString(),
                        MOCK_DATA_LOCATION_LATITUDE,
                        MOCK_DATA_LOCATION_LONGITUDE,
                        MOCK_DATA_LOCATION_NAME);
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createPost(int memberID,
                            String imageURL,
                            String caption,
                            double locationLatitude,
                            double locationLongitude,
                            String locationName) {
        Call<PostItemDao> call = HttpManager.getInstance()
                .getService()
                .addPost(memberID, imageURL, caption, locationLatitude, locationLongitude, locationName);

        call.enqueue(new Callback<PostItemDao>() {
            @Override
            public void onResponse(Call<PostItemDao> call, Response<PostItemDao> response) {
                presentFeedScreen();
            }

            @Override
            public void onFailure(Call<PostItemDao> call, Throwable t) {

            }
        });
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

    private void presentFeedScreen() {
        Intent intent = MainActivity.getStartIntent(CreatePostActivity.this, MainActivity.LAUNCH_SCREEN_FEED);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
