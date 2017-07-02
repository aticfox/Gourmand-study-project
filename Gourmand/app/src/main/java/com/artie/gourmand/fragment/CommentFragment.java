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
import com.artie.gourmand.activity.MainActivity;
import com.artie.gourmand.adapter.CommentAdapter;
import com.artie.gourmand.adapter.OnItemClickListener;
import com.artie.gourmand.dao.CommentItemCollectionDao;
import com.artie.gourmand.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANFIELD on 9/6/2560.
 */

public class CommentFragment extends Fragment {

    RecyclerView mRecyclerView;

    private CommentAdapter mCommentAdapter;

    public static CommentFragment newInstance() {
        Bundle args = new Bundle();
        CommentFragment fragment = new CommentFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comment, container, false);

        initInstances(rootView);
        setupData();

        return rootView;
    }

    private void initInstances(View rootView) {
        mCommentAdapter = new CommentAdapter(getContext(), new CommentItemCollectionDao(), onItemClickListener);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCommentAdapter);
    }

    private void setupData() {
        Call<CommentItemCollectionDao> call = HttpManager.getInstance().getService().loadComments();

        call.enqueue(new Callback<CommentItemCollectionDao>() {
            @Override
            public void onResponse(Call<CommentItemCollectionDao> call, Response<CommentItemCollectionDao> response) {
                if (response.isSuccessful()) {
                    CommentItemCollectionDao dao = response.body();
                    mCommentAdapter.setDao(dao);
                    mCommentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CommentItemCollectionDao> call, Throwable t) {
                Log.d("Comment", "Fail load data: " + t.toString());
            }
        });
    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent;

            switch (view.getId()) {
                case R.id.text_username:
                    intent = MainActivity.getStartIntent(getContext(), MainActivity.LAUNCH_SCREEN_PROFILE);
                    break;
                default:
                    return;
            }

            startActivity(intent);
        }
    };

}
