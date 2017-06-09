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
import com.artie.gourmand.activity.MainActivity;
import com.artie.gourmand.adapter.CommentAdapter;
import com.artie.gourmand.adapter.OnItemClickListener;

/**
 * Created by ANFIELD on 9/6/2560.
 */

public class CommentFragment extends Fragment {

    RecyclerView mRecyclerView;

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

        return rootView;
    }

    private void initInstances(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new CommentAdapter(onItemClickListener));
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
