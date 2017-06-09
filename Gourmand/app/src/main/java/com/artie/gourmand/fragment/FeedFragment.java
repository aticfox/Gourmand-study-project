package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.CommentActivity;
import com.artie.gourmand.activity.MapActivity;
import com.artie.gourmand.adapter.FeedAdapter;
import com.artie.gourmand.adapter.OnItemClickListener;

/**
 * Created by ANFIELD on 23/5/2560.
 */

public class FeedFragment extends Fragment {

    RecyclerView mRecyclerView;

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

        return rootView;
    }

    private void initInstances(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new FeedAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent;

                switch (view.getId()) {
                    case R.id.text_location_name:
                        intent = MapActivity.getStartIntent(getContext());
                        break;
                    case R.id.button_comment:
                        intent = CommentActivity.getStartIntent(getContext());
                        break;
                    default:
                        return;
                }

                startActivity(intent);
            }
        }));
    }

}
