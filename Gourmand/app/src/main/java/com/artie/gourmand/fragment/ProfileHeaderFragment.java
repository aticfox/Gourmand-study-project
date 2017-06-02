package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.FollowerActivity;
import com.artie.gourmand.activity.FollowingActivity;

/**
 * Created by ANFIELD on 1/6/2560.
 */

public class ProfileHeaderFragment extends Fragment{

    LinearLayout linearLayoutFollower;
    LinearLayout linearLayoutFollowing;

    public static ProfileHeaderFragment newInstance() {
        Bundle args = new Bundle();
        ProfileHeaderFragment fragment = new ProfileHeaderFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_header, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        linearLayoutFollower = (LinearLayout) rootView.findViewById(R.id.linear_layout_follower);
        linearLayoutFollowing = (LinearLayout) rootView.findViewById(R.id.linear_layout_following);


        linearLayoutFollower.setOnClickListener(followClickListener);
        linearLayoutFollowing.setOnClickListener(followClickListener);
    }

    View.OnClickListener followClickListener = new View.OnClickListener() {
        Intent intent;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.linear_layout_follower:
                    intent = FollowerActivity.getStartIntent(getContext());
                    startActivity(intent);
                    break;
                case R.id.linear_layout_following:
                    intent = FollowingActivity.getStartIntent(getContext());
                    startActivity(intent);
                    break;
                default:
                    break;
            }

        }
    };

}
