package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.CommentActivity;
import com.artie.gourmand.activity.MapActivity;

/**
 * Created by ANFIELD on 3/6/2560.
 */

public class PostFragment extends Fragment {

    TextView mTextLocationName;
    Button mButtonComment;

    public static FeedFragment newInstance() {
        Bundle args = new Bundle();
        FeedFragment fragment = new FeedFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        mButtonComment = (Button) rootView.findViewById(R.id.button_comment);
        mButtonComment.setOnClickListener(onClickListener);
        mTextLocationName = (TextView) rootView.findViewById(R.id.text_location_name);
        mTextLocationName.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()) {
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
    };

}
