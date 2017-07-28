package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.artie.gourmand.R;
import com.artie.gourmand.fragment.PostFragment;

public class PostActivity extends AppCompatActivity {

    private static final String INTENT_EXTRA_POST_ID = "postID";

    private int mPostID;

    public static Intent getStartIntent(Context context, int postID) {
        Intent intent = new Intent(context, PostActivity.class);
        intent.putExtra(INTENT_EXTRA_POST_ID, postID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mPostID = getIntent().getIntExtra(INTENT_EXTRA_POST_ID, 0);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_container, PostFragment.newInstance(mPostID))
                    .commit();
        }
    }

}
