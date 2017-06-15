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
import com.artie.gourmand.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANFIELD on 23/5/2560.
 */

public class FeedFragment extends Fragment {

    RecyclerView mRecyclerView;

    private List<Post> mPosts = new ArrayList<>();

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

        setupData();
        initInstances(rootView);

        return rootView;
    }

    private void setupData() {
        String[] userNames = getActivity().getResources().getStringArray(R.array.users_name);
        int[] postImageIDs = new int[]{
                R.drawable.image_food,
                R.drawable.image_food2,
                R.drawable.image_food3,
                R.drawable.image_food4,
                R.drawable.image_food5,
                R.drawable.image_food6,
                R.drawable.image_food7,
                R.drawable.image_food8,
                R.drawable.image_food9,
                R.drawable.image_food10,
                R.drawable.image_food11
        };
        int[] userImageIDs = new int[]{
                R.drawable.image_profile,
                R.drawable.image_profile2,
                R.drawable.image_profile3,
                R.drawable.image_profile4,
                R.drawable.image_profile5,
                R.drawable.image_profile6,
                R.drawable.image_profile7,
                R.drawable.image_profile8,
                R.drawable.image_profile9,
                R.drawable.image_profile10,
                R.drawable.image_profile11
        };

        for(int i = 0; i< userNames.length; i++) {
            Post post = new Post(userImageIDs[i], postImageIDs[i], userNames[i]);
            mPosts.add(post);
        }
    }

    private void initInstances(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new FeedAdapter(mPosts, new OnItemClickListener(){
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
