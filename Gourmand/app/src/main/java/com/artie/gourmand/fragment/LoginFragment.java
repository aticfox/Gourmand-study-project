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
import com.artie.gourmand.activity.ForgotPasswordActivity;
import com.artie.gourmand.activity.MainActivity;
import com.artie.gourmand.activity.RegisterActivity;

/**
 * Created by ANFIELD on 6/6/2560.
 */

public class LoginFragment extends Fragment {

    Button mButtonLogin;
    TextView mTextRegister;
    TextView mTextForgotPassword;

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

        mTextRegister = (TextView) rootView.findViewById(R.id.text_register);
        mTextRegister.setOnClickListener(onClickListener);

        mTextForgotPassword = (TextView) rootView.findViewById(R.id.text_forgot_password);
        mTextForgotPassword.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()) {
                case R.id.button_login:
                    intent = MainActivity.getStartIntent(getContext());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    break;
                case R.id.text_register:
                    intent = RegisterActivity.getStartIntent(getContext());
                    break;
                case R.id.text_forgot_password:
                    intent = ForgotPasswordActivity.getStartIntent(getContext());
                    break;
                default:
                    return;
            }

            startActivity(intent);
        }
    };

}
