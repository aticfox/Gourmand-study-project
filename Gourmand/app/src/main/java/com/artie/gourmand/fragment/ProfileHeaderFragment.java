package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.FollowerActivity;
import com.artie.gourmand.activity.FollowingActivity;
import com.artie.gourmand.activity.MapActivity;
import com.artie.gourmand.dao.ProfileItemDao;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by ANFIELD on 1/6/2560.
 */

public class ProfileHeaderFragment extends Fragment{

    static final String ARGUMENT_DAO = "mDao";

    LinearLayout mLinearLayoutFollower;
    LinearLayout mLinearLayoutFollowing;
    Button mButtonToMap;
    ProfileItemDao mDao;
    ImageView mImageUser;
    TextView mTextUsername;
    TextView mTextFollowerCount;
    TextView mTextFollowingCount;

    public static ProfileHeaderFragment newInstance() {
        Bundle args = new Bundle();
        ProfileHeaderFragment fragment = new ProfileHeaderFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDao = getArguments().getParcelable(ARGUMENT_DAO);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_header, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        mLinearLayoutFollower = (LinearLayout) rootView.findViewById(R.id.linear_layout_follower);
        mLinearLayoutFollowing = (LinearLayout) rootView.findViewById(R.id.linear_layout_following);
        mButtonToMap = (Button) rootView.findViewById(R.id.button_to_map);
        mImageUser = (ImageView) rootView.findViewById(R.id.image_user);
        mTextUsername = (TextView) rootView.findViewById(R.id.text_username);
        mTextFollowerCount = (TextView) rootView.findViewById(R.id.text_follower_count);
        mTextFollowingCount = (TextView) rootView.findViewById(R.id.text_following_count);

        mTextUsername.setText(mDao.getName());
        mTextFollowerCount.setText(mDao.getFollowerCount().toString());
        mTextFollowingCount.setText(mDao.getFollowingCount().toString());

        Glide.with(getContext())
                .load(mDao.getAvatarURL())
                .apply(RequestOptions.placeholderOf(R.drawable.avatar_placeholder))
                .into(mImageUser);

        mLinearLayoutFollower.setOnClickListener(onClickListener);
        mLinearLayoutFollowing.setOnClickListener(onClickListener);
        mButtonToMap.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;

        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()) {
                case R.id.linear_layout_follower:
                    intent = FollowerActivity.getStartIntent(getContext());
                    break;
                case R.id.linear_layout_following:
                    intent = FollowingActivity.getStartIntent(getContext());
                    break;
                case R.id.button_to_map:
                    intent = MapActivity.getStartIntent(getContext());
                    startActivity(intent);
                    break;
                default:
                    return;
            }

            startActivity(intent);
        }

    };

    public void setDao(ProfileItemDao dao) {
        mDao = dao;

        getArguments().putParcelable(ARGUMENT_DAO, mDao);
    }

}
