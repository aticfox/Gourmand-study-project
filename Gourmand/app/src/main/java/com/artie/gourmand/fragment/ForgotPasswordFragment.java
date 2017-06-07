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
import com.artie.gourmand.activity.LoginActivity;

/**
 * Created by ANFIELD on 7/6/2560.
 */

public class ForgotPasswordFragment extends Fragment {

    Button mButtonForgotPassword;

    public static ForgotPasswordFragment newInstance() {
        Bundle args = new Bundle();
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        mButtonForgotPassword = (Button) rootView.findViewById(R.id.button_reset_password);
        mButtonForgotPassword.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()) {
                case R.id.button_reset_password:
                    intent = LoginActivity.getStartIntent(getContext());
                    break;
                default:
                    return;
            }
            startActivity(intent);
        }
    };

}
