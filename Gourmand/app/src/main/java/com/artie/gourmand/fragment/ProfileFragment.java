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
import com.artie.gourmand.dao.ProfileItemDao;
import com.artie.gourmand.model.User;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;

/**
 * Created by ANFIELD on 23/5/2560.
 */

public class ProfileFragment extends Fragment {

    static final int PHOTO_COLUMN = 3;
    static final String ARGUMENT_DAO = "mDao";
    static final String FRAGMENT_PROFILE_HEADER = "ProfileHeaderFragment";

    RecyclerView mRecyclerView;
    RecyclerViewHeader mRecyclerViewHeader;
    ProfileHeaderFragment mProfileHeaderFragment;
    private ProfileItemDao mDao;
    private GridSquarePhotoAdapter mGridSquarePhotoAdapter;
    private int mProfileMemberID = User.getInstance().getDao().getId();

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDao = getArguments().getParcelable(ARGUMENT_DAO);
        if(savedInstanceState == null) {
            mProfileHeaderFragment = ProfileHeaderFragment.newInstance();
            mProfileHeaderFragment.setDao(mDao);
            
            getChildFragmentManager().beginTransaction()
                    .add(R.id.content_container_header,
                            mProfileHeaderFragment,
                            FRAGMENT_PROFILE_HEADER)
                    .commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        mGridSquarePhotoAdapter = new GridSquarePhotoAdapter(getContext(), mDao, PHOTO_COLUMN, onItemClickListener);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), PHOTO_COLUMN));
        mRecyclerView.setAdapter(mGridSquarePhotoAdapter);
        mRecyclerViewHeader = (RecyclerViewHeader) rootView.findViewById(R.id.recycler_header_view);
        mRecyclerViewHeader.attachTo(mRecyclerView);

        updateView();
    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = PostActivity.getStartIntent(getContext(), mDao.getPosts().get(position).getId());
            startActivity(intent);
        }
    };

    public void setDao(ProfileItemDao dao) {
        mDao = dao;
        updateView();
    }

    private void updateView() {
        // Hot fix crash when present profile screen from post
        for (long i = 0; i<9999999; i++) {}

        if (isLoadViewComplete() && mDao != null) {
            mProfileHeaderFragment.setDao(mDao);
            mGridSquarePhotoAdapter.setDao(mDao);
            mGridSquarePhotoAdapter.notifyDataSetChanged();
            getArguments().putParcelable(ARGUMENT_DAO, mDao);
        }
    }

    public boolean isLoadViewComplete() {
        return mRecyclerView != null;
    }

}
