package com.artie.gourmand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.MainActivity;
import com.artie.gourmand.activity.RegisterActivity;
import com.artie.gourmand.dao.MemberItemDao;
import com.artie.gourmand.manager.HttpManager;
import com.artie.gourmand.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANFIELD on 6/6/2560.
 */

public class LoginFragment extends Fragment {

    Button mButtonLogin;
    TextView mTextRegister;
    EditText mEditTextEmail;
    EditText mEditTextPassword;

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

        mEditTextEmail = (EditText) rootView.findViewById(R.id.edit_text_email);
        mEditTextPassword = (EditText) rootView.findViewById(R.id.edit_text_password);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_login:
                    login(mEditTextEmail.getText().toString(), mEditTextPassword.getText().toString());
                    break;
                case R.id.text_register:
                    presentRegisterScreen();
                    break;
                default:
                    return;
            }
        }
    };

    private void presentRegisterScreen() {
        Intent intent = RegisterActivity.getStartIntent(getContext());
        startActivity(intent);
    }

    private void login(String email, String password) {
        Call<MemberItemDao> call = HttpManager.getInstance().getService().login(email, password);
        call.enqueue(new Callback<MemberItemDao>() {
            @Override
            public void onResponse(Call<MemberItemDao> call, Response<MemberItemDao> response) {
                if (response.isSuccessful()) {
                    MemberItemDao dao = response.body();
                    User.getInstance().setDao(dao);

                    Intent intent = MainActivity.getStartIntent(getContext(), MainActivity.LAUNCH_SCREEN_FEED);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<MemberItemDao> call, Throwable t) {
                Log.d("Comment", "Fail load data: " + t.toString());
            }
        });
    }

}
