package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.PostActivity;
import com.artie.gourmand.adapter.GridSquarePhotoAdapter;
import com.artie.gourmand.adapter.OnItemClickListener;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;

/**
 * Created by ANFIELD on 23/5/2560.
 */

public class ProfileFragment extends Fragment {

    static final int PHOTO_COLUMN = 3;
    static final String FRAGMENT_PROFILE_HEADER = "ProfileHeaderFragment";

    RecyclerView mRecyclerView;
    RecyclerViewHeader mRecyclerViewHeader;
    ProfileHeaderFragment mProfileHeaderFragment;

    private int[] mPostImageIDs;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        setupData();
        initInstances(rootView);

        return rootView;
    }

    private void setupData() {
        mPostImageIDs = new int[]{
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
    }

    private void initInstances(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), PHOTO_COLUMN));
        mRecyclerView.setAdapter(new GridSquarePhotoAdapter(getContext(), mPostImageIDs, PHOTO_COLUMN, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = PostActivity.getStartIntent(getContext());
                startActivity(intent);
            }
        }));

        mRecyclerViewHeader = (RecyclerViewHeader) rootView.findViewById(R.id.recycler_header_view);
        mRecyclerViewHeader.attachTo(mRecyclerView);

        mProfileHeaderFragment = ProfileHeaderFragment.newInstance();
        getChildFragmentManager().beginTransaction()
                .add(R.id.content_container_header,
                        mProfileHeaderFragment,
                        FRAGMENT_PROFILE_HEADER)
                .commit();
    }

}
