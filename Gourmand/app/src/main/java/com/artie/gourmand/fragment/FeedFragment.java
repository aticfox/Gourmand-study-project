package com.artie.gourmand.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.artie.gourmand.R;
import com.artie.gourmand.adapter.FeedAdapter;

/**
 * Created by ANFIELD on 23/5/2560.
 */

public class FeedFragment extends Fragment {

    ListView listView;
    FeedAdapter itemAdapter;

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
        listView = (ListView) rootView.findViewById(R.id.listView);
        itemAdapter = new FeedAdapter();
        listView.setAdapter(itemAdapter);
    }

}
