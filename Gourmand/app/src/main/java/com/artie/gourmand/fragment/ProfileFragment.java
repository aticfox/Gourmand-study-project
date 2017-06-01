package com.artie.gourmand.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artie.gourmand.R;
import com.artie.gourmand.adapter.GridSquarePhotoAdapter;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;

/**
 * Created by ANFIELD on 23/5/2560.
 */

public class ProfileFragment extends Fragment {

    static final int PHOTO_COLUMN = 3;
    static final String FRAGMENT_PROFILE_HEADER = "ProfileHeaderFragment";

    RecyclerView mRecyclerView;
    RecyclerViewHeader mRecyclerViewHeader;
    ProfileHeaderFragment mProfileHeaderView;

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

        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(new GridSquarePhotoAdapter(PHOTO_COLUMN));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), PHOTO_COLUMN));

        mRecyclerViewHeader = (RecyclerViewHeader) rootView.findViewById(R.id.recycler_header_view);
        mRecyclerViewHeader.attachTo(mRecyclerView);

        mProfileHeaderView = ProfileHeaderFragment.newInstance();
        getChildFragmentManager().beginTransaction()
                .add(R.id.content_container_header,
                        mProfileHeaderView,
                        FRAGMENT_PROFILE_HEADER)
                .commit();
    }

}
