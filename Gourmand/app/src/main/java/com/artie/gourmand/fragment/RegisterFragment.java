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

import com.artie.gourmand.R;
import com.artie.gourmand.activity.MainActivity;
import com.artie.gourmand.dao.MemberItemDao;
import com.artie.gourmand.manager.HttpManager;
import com.artie.gourmand.model.User;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANFIELD on 7/6/2560.
 */

public class RegisterFragment extends Fragment {

    private static String MOCK_IMAGE_USER = "https://i.pinimg.com/736x/de/1e/fe/de1efef55429481f3b9d8daf14686e82--beautiful-eyes-most-beautiful.jpg";

    Button mButtonSingUp;
    CircleImageView mImageUser;
    EditText mEditTextUsername;
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
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        mButtonSingUp = (Button) rootView.findViewById(R.id.button_register);
        mButtonSingUp.setOnClickListener(onClickListener);
        mImageUser = (CircleImageView) rootView.findViewById(R.id.image_user);
        mEditTextUsername = (EditText) rootView.findViewById(R.id.edit_text_username);
        mEditTextEmail = (EditText) rootView.findViewById(R.id.edit_text_email);
        mEditTextPassword = (EditText) rootView.findViewById(R.id.edit_text_password);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_register:
                    register(mEditTextEmail.getText().toString(),
                            mEditTextPassword.getText().toString(),
                            mEditTextUsername.getText().toString(),
                            MOCK_IMAGE_USER);
                    break;
                default:
                    return;
            }
        }

        private void register(String email, String password, String username, String imageUserURL) {
            Call<MemberItemDao> call = HttpManager.getInstance()
                    .getService()
                    .register(email, password, username, imageUserURL);
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
                    Log.d("Register", "Fail load data: " + t.toString());
                }
            });
        }
    };

}
