package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.artie.gourmand.R;
import com.artie.gourmand.dao.PostItemDao;
import com.artie.gourmand.manager.HttpManager;
import com.artie.gourmand.view.SquareImageView;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.android.Utils;
import com.cloudinary.utils.ObjectUtils;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String INTENT_EXTRA_IMAGE_URI = "imageURI";
    private static final int MOCK_DATA_MEMBER_ID = 1;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private static final String TAG = "create post";

    TextView mTextViewSelectLocation;
    SquareImageView mSquareImageViewPost;
    EditText mEditTextCaption;

    private Uri mImageURI;
    private String mImageURL;
    private Place place;

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
        uploadImage(mImageURI);

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
                        mImageURL,
                        mEditTextCaption.getText().toString(),
                        place.getLatLng().latitude,
                        place.getLatLng().longitude,
                        place.getName().toString());
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void uploadImage(final Uri imageURI) {
        final Cloudinary cloudinary = new Cloudinary(Utils.cloudinaryUrlFromContext(getApplicationContext()));

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                try{
                    return (String) cloudinary.uploader().upload(getContentResolver().openInputStream(imageURI),  ObjectUtils.asMap("transformation",
                            new Transformation().width(600).height(600).crop("fill"))).get("url");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String imageURL) {
                super.onPostExecute(imageURL);

                mImageURL = imageURL;
            }
        }.execute();
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
        switch (v.getId()) {
            case R.id.text_select_location:
                presentAutocompleteActivity();
                break;
            default:
                break;
        }
    }

    private void presentFeedScreen() {
        Intent intent = MainActivity.getStartIntent(CreatePostActivity.this, MainActivity.LAUNCH_SCREEN_FEED);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void presentAutocompleteActivity() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
        } catch (GooglePlayServicesRepairableException e) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                    0 /* requestCode */).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            String message = "Google Play Services is not available: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

            Log.e(TAG, message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                place = PlaceAutocomplete.getPlace(this, data);
                mTextViewSelectLocation.setText(place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e(TAG, "Error: Status = " + status.toString());
            }
        }
    }

}
