package com.artie.gourmand.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ANFIELD on 24/6/2560.
 */

public class SearchFragment extends Fragment {

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();

        fragment.setArguments(args);

        return fragment;
    }
}
