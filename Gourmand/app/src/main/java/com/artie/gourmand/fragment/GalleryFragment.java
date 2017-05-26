package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.PostPhotoActivity;

/**
 * Created by ANFIELD on 26/5/2560.
 */

public class GalleryFragment extends Fragment {

    public static GalleryFragment newInstance() {
        Bundle args = new Bundle();
        GalleryFragment fragment = new GalleryFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
    }

    private void initInstances(View rootView) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_gallery, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_next:
                Intent intent = PostPhotoActivity.getStartIntent(getContext());
                startActivity(intent);
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
