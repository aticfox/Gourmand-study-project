package com.artie.gourmand.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.android.Utils;
import com.cloudinary.utils.ObjectUtils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ANFIELD on 7/6/2560.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {

    Button mButtonSingUp;
    CircleImageView mImageUser;
    EditText mEditTextUsername;
    EditText mEditTextEmail;
    EditText mEditTextPassword;

    private String mImageUserURL;

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
        mButtonSingUp.setOnClickListener(this);
        mImageUser = (CircleImageView) rootView.findViewById(R.id.image_user);
        mImageUser.setOnClickListener(this);
        mEditTextUsername = (EditText) rootView.findViewById(R.id.edit_text_username);
        mEditTextEmail = (EditText) rootView.findViewById(R.id.edit_text_email);
        mEditTextPassword = (EditText) rootView.findViewById(R.id.edit_text_password);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
                register(mEditTextEmail.getText().toString(),
                        mEditTextPassword.getText().toString(),
                        mEditTextUsername.getText().toString(),
                        mImageUserURL);
                break;
            case R.id.image_user:
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(getContext(), this);
                break;
            default:
                return;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri mSelectedImageURI = result.getUri();
                mImageUser.setImageURI(mSelectedImageURI);
                uploadImage(mSelectedImageURI);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void uploadImage(final Uri imageURI) {
        final Cloudinary cloudinary = new Cloudinary(Utils.cloudinaryUrlFromContext(getActivity().getApplicationContext()));

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                try{
                    return (String) cloudinary.uploader()
                            .upload(getActivity().getContentResolver().openInputStream(imageURI),
                                    ObjectUtils.asMap("transformation",
                                            new Transformation().width(600)
                                                    .height(600)
                                                    .crop("fill")))
                            .get("url");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String imageURL) {
                mImageUserURL = imageURL;
            }
        }.execute();
    }

}
