package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.artie.gourmand.R;
import com.artie.gourmand.fragment.CommentFragment;

public class CommentActivity extends AppCompatActivity {

    private static final String INTENT_POST_ID = "postID";

    private int mPostID;

    public static Intent getStartIntent(Context context, int postID) {
        Intent intent = new Intent(context, CommentActivity.class);

        intent.putExtra(INTENT_POST_ID, postID);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        mPostID = getIntent().getIntExtra(INTENT_POST_ID, 0);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_container, CommentFragment.newInstance(mPostID))
                    .commit();
        }
    }

}
