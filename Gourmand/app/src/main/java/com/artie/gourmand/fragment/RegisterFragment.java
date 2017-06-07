package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.MainActivity;

/**
 * Created by ANFIELD on 7/6/2560.
 */

public class RegisterFragment extends Fragment {

    Button mButtonSingUp;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        mButtonSingUp = (Button) rootView.findViewById(R.id.button_register);
        mButtonSingUp.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.button_register:
                    intent = MainActivity.getStartIntent(getContext());
                    break;
                default:
                    return;
            }
            startActivity(intent);
        }
    };

}
