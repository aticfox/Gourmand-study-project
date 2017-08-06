package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.CommentActivity;
import com.artie.gourmand.activity.MapActivity;
import com.artie.gourmand.activity.ProfileActivity;
import com.artie.gourmand.adapter.FeedAdapter;
import com.artie.gourmand.adapter.OnItemClickListener;
import com.artie.gourmand.dao.PostItemCollectionDao;
import com.artie.gourmand.dao.PostItemDao;
import com.artie.gourmand.manager.HttpManager;
import com.artie.gourmand.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANFIELD on 23/5/2560.
 */

public class FeedFragment extends Fragment {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private FeedAdapter mFeedAdapter;
    private PostItemCollectionDao mDao;

    public static FeedFragment newInstance() {
        Bundle args = new Bundle();
        FeedFragment fragment = new FeedFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        initInstances(rootView);
        setupData();

        return rootView;
    }

    private void initInstances(View rootView) {
        mFeedAdapter = new FeedAdapter(getContext(), new PostItemCollectionDao(), onItemClickListener);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mFeedAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(pullToRefreshListener);
    }

    private void setupData() {
        Call<PostItemCollectionDao> call = HttpManager.getInstance()
                .getService()
                .loadPosts(User.getInstance().getDao().getId());

        call.enqueue(new Callback<PostItemCollectionDao>() {
            @Override
            public void onResponse(Call<PostItemCollectionDao> call, Response<PostItemCollectionDao> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    mDao = response.body();
                    mFeedAdapter.setDao(mDao);
                    mFeedAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PostItemCollectionDao> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                Log.d("Feed", "Fail load data: " + t.toString());
            }
        });
    }

    private void like(int postID) {
        Call<PostItemDao> call = HttpManager.getInstance()
                .getService()
                .like(postID, User.getInstance().getDao().getId());
        call.enqueue(new Callback<PostItemDao>() {
            @Override
            public void onResponse(Call<PostItemDao> call, Response<PostItemDao> response) {
                PostItemDao dao = response.body();

                for (int i=0; i<mDao.getPosts().size(); i++) {
                    PostItemDao post = mDao.getPosts().get(i);
                    if (post.getId() == dao.getId()) {
                        mDao.getPosts().remove(i);
                        mDao.getPosts().add(i, dao);
                    }
                }

                mFeedAdapter.setDao(mDao);
                mFeedAdapter.notifyDataSetChanged();
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
                PostItemDao dao = response.body();

                for (int i=0; i<mDao.getPosts().size(); i++) {
                    PostItemDao post = mDao.getPosts().get(i);
                    if (post.getId() == dao.getId()) {
                        mDao.getPosts().remove(i);
                        mDao.getPosts().add(i, dao);
                    }
                }

                mFeedAdapter.setDao(mDao);
                mFeedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PostItemDao> call, Throwable t) {
                Log.d("Feed", "Fail : " + t.toString());
            }
        });
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            PostItemDao post = mDao.getPosts().get(position);

            switch (view.getId()) {
                case R.id.image_user:
                case R.id.text_username:
                    presentProfileScreen(post.getMember().getId());
                    break;
                case R.id.text_location_name:
                    presentMapScreen(post);
                    break;
                case R.id.button_comment:
                    presentCommentScreen(post.getId());

                    break;
                case R.id.button_like:
                    if (post.isLike()) {
                        unlike(post.getId());
                    } else {
                        like(post.getId());
                    }
                    break;
                default:
                    return;
            }
        }
    };

    private void presentProfileScreen(int memberID) {
        Intent intent = ProfileActivity.getStartIntent(getContext(), memberID);
        startActivity(intent);
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

    SwipeRefreshLayout.OnRefreshListener pullToRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();
        }
    };

    private void refreshData() {
        setupData();
    }

}