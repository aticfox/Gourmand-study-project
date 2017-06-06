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
 * Created by ANFIELD on 6/6/2560.
 */

public class LoginFragment extends Fragment {

    Button mButtonLogin;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        mButtonLogin = (Button) rootView.findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_login:
                    Intent intent = MainActivity.getStartIntent(getContext());
                    startActivity(intent);
                default:
                    break;
            }
        }
    };

}
