package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.CommentActivity;
import com.artie.gourmand.activity.MapActivity;
import com.artie.gourmand.dao.PostItemDao;
import com.artie.gourmand.manager.HttpManager;
import com.artie.gourmand.model.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANFIELD on 3/6/2560.
 */

public class PostFragment extends Fragment {

    private static final String ARGUMENT_POST_ID = "postID";

    ImageView mImageUser;
    ImageView mImagePost;
    TextView mTextUsername;
    TextView mTextLocationName;
    TextView mTextCreateTime;
    TextView mTextCaption;
    TextView mTextLikeCount;
    Button mButtonComment;
    Button mButtonLike;

    private PostItemDao mDao;
    private int mPostID;

    public static PostFragment newInstance(int postID) {
        Bundle args = new Bundle();
        PostFragment fragment = new PostFragment();

        args.putInt(ARGUMENT_POST_ID, postID);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPostID = getArguments().getInt(ARGUMENT_POST_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);

        initInstances(rootView);
        setupData();

        return rootView;
    }

    private void initInstances(View rootView) {
        mImageUser = (ImageView) rootView.findViewById(R.id.image_user);
        mImagePost = (ImageView) rootView.findViewById(R.id.image_post);
        mTextUsername = (TextView) rootView.findViewById(R.id.text_username);
        mTextCreateTime = (TextView) rootView.findViewById(R.id.text_create_time);
        mTextCaption = (TextView) rootView.findViewById(R.id.text_caption);
        mTextLikeCount = (TextView) rootView.findViewById(R.id.text_like_count);
        mButtonComment = (Button) rootView.findViewById(R.id.button_comment);
        mButtonComment.setOnClickListener(onClickListener);
        mTextLocationName = (TextView) rootView.findViewById(R.id.text_location_name);
        mTextLocationName.setOnClickListener(onClickListener);
        mButtonLike = (Button) rootView.findViewById(R.id.button_like);
        mButtonLike.setOnClickListener(onClickListener);
    }

    private void setupData() {
        Call<PostItemDao> call = HttpManager.getInstance().getService().loadPost(mPostID, User.getInstance().getDao().getId());

        call.enqueue(new Callback<PostItemDao>() {
            @Override
            public void onResponse(Call<PostItemDao> call, Response<PostItemDao> response) {
                if (response.isSuccessful()) {
                    PostItemDao dao = response.body();
                    setDao(dao);
                }
            }

            @Override
            public void onFailure(Call<PostItemDao> call, Throwable t) {
                Log.d("Comment", "Fail load data: " + t.toString());
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.text_location_name:
                    presentMapScreen(mDao);
                    break;
                case R.id.button_comment:
                    presentCommentScreen(mDao.getId());
                    break;
                case R.id.button_like:
                    if (mDao.isLike()) {
                        unlike(mDao.getId());
                    } else {
                        like(mDao.getId());
                    }
                    break;
                default:
                    return;
            }
        }
    };

    public void setDao(PostItemDao dao) {
        mDao = dao;

        updateView();
    }

    private void updateView() {
        mTextUsername.setText(mDao.getMember().getName());
        mTextCreateTime.setText(mDao.getCreateTimeText());
        mTextCaption.setText(mDao.getCaption());
        mTextLikeCount.setText("Liked " + mDao.getLikeCount().toString());
        mTextLocationName.setText(mDao.getLocationName());
        if (mDao.isLike()) {
            mButtonLike.setBackgroundResource(R.drawable.btn_like_select);
        } else {
            mButtonLike.setBackgroundResource(R.drawable.btn_like_normal);
        }

        Glide.with(getContext())
                .load(mDao.getMember().getAvatarUrl())
                .apply(RequestOptions.placeholderOf(R.drawable.avatar_placeholder))
                .into(mImageUser);
        Glide.with(getContext())
                .load(mDao.getImageURL())
                .apply(RequestOptions.placeholderOf(R.drawable.avatar_placeholder))
                .into(mImagePost);
    }

    private void presentMapScreen(PostItemDao post) {
        Intent intent = MapActivity.getStartIntent(getContext(),
                post.getLatitude(),
                post.getLongitude(),
                post.getLocationName());
        startActivity(intent);
    }

    private void presentCommentScreen(int postID) {
        Intent intent = CommentActivity.getStartIntent(
                getContext(),
                postID);
        startActivity(intent);
    }

    private void like(int postID) {
        Call<PostItemDao> call = HttpManager.getInstance()
                .getService()
                .like(postID, User.getInstance().getDao().getId());
        call.enqueue(new Callback<PostItemDao>() {
            @Override
            public void onResponse(Call<PostItemDao> call, Response<PostItemDao> response) {
                mDao = response.body();
                updateView();
            }

            @Override
            public void onFailure(Call<PostItemDao> call, Throwable t) {
                Log.d("Feed", "Fail : " + t.toString());
            }
        });
    }

    private void unlike(int postID) {
        Call<PostItemDao> call = HttpManager.getInstance()
                .getService()
                .unlike(postID, User.getInstance().getDao().getId());
        call.enqueue(new Callback<PostItemDao>() {
            @Override
            public void onResponse(Call<PostItemDao> call, Response<PostItemDao> response) {
                mDao = response.body();
                updateView();
            }

            @Override
            public void onFailure(Call<PostItemDao> call, Throwable t) {
                Log.d("Feed", "Fail : " + t.toString());
            }
        });
    }

}
