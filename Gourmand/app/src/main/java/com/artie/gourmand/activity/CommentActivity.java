package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.artie.gourmand.R;
import com.artie.gourmand.dao.CommentItemCollectionDao;
import com.artie.gourmand.fragment.CommentFragment;
import com.artie.gourmand.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    private static final String INTENT_POST_ID = "postID";

    EditText mEditTextComment;
    Button mButtonPost;

    private int mPostID;
    private int mMemberID = 1;
    private CommentFragment mCommentFragment;

    public static Intent getStartIntent(Context context, int postID) {
        Intent intent = new Intent(context, CommentActivity.class);

        intent.putExtra(INTENT_POST_ID, postID);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        initInstances();

        mPostID = getIntent().getIntExtra(INTENT_POST_ID, 0);
        mCommentFragment = CommentFragment.newInstance(mPostID);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_container, mCommentFragment)
                    .commit();
        }
    }

    private void initInstances() {
        mEditTextComment = (EditText) findViewById(R.id.edit_text_comment);
        mButtonPost = (Button) findViewById(R.id.button_post);
        mButtonPost.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_post:
                    postComment(mEditTextComment.getText().toString(),
                            mPostID,
                            mMemberID);
                    mEditTextComment.setText("");
                    break;
            }
        }
    };

    private void postComment(String text, int postID, int memberID) {
        Call<CommentItemCollectionDao> call = HttpManager.getInstance().getService().addComment(postID, text, memberID);

        call.enqueue(new Callback<CommentItemCollectionDao>() {
            @Override
            public void onResponse(Call<CommentItemCollectionDao> call, Response<CommentItemCollectionDao> response) {
                if (response.isSuccessful()) {
                    CommentItemCollectionDao dao = response.body();
                    mCommentFragment.setDao(dao);
                    mCommentFragment.scrollToLastestComment();
                }
            }

            @Override
            public void onFailure(Call<CommentItemCollectionDao> call, Throwable t) {

            }
        });
    }

}
