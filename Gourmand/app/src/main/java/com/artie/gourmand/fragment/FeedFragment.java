package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.CommentActivity;
import com.artie.gourmand.activity.MapActivity;
import com.artie.gourmand.adapter.FeedAdapter;
import com.artie.gourmand.adapter.OnItemClickListener;
import com.artie.gourmand.dao.PostItemCollectionDao;
import com.artie.gourmand.dao.PostItemDao;
import com.artie.gourmand.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANFIELD on 23/5/2560.
 */

public class FeedFragment extends Fragment {

    RecyclerView mRecyclerView;

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
        setupData(savedInstanceState);

        return rootView;
    }

    private void initInstances(View rootView) {
        mFeedAdapter = new FeedAdapter(getContext(), new PostItemCollectionDao(), onItemClickListener);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mFeedAdapter);
    }

    private void setupData(Bundle savedInstanceState) {
        Call<PostItemCollectionDao> call = HttpManager.getInstance().getService().loadPosts();

        call.enqueue(new Callback<PostItemCollectionDao>() {
            @Override
            public void onResponse(Call<PostItemCollectionDao> call, Response<PostItemCollectionDao> response) {
                if (response.isSuccessful()) {
                    mDao = response.body();
                    mFeedAdapter.setDao(mDao);
                    mFeedAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PostItemCollectionDao> call, Throwable t) {
                Log.d("Feed", "Fail load data: " + t.toString());
            }
        });
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent;
            PostItemDao post = mDao.getPosts().get(position);

            switch (view.getId()) {
                case R.id.text_location_name:
                    intent = MapActivity.getStartIntent(getContext(),
                            post.getLatitude(),
                            post.getLongitude(),
                            post.getLocationName());
                    break;
                case R.id.button_comment:
                    intent = CommentActivity.getStartIntent(
                            getContext(),
                            mDao.getPosts().get(position).getId());
                    break;
                default:
                    return;
            }

            startActivity(intent);
        }
    };

}
