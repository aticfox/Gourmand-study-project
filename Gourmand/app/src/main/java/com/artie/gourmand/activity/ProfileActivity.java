package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.artie.gourmand.R;
import com.artie.gourmand.dao.ProfileItemDao;
import com.artie.gourmand.fragment.ProfileFragment;
import com.artie.gourmand.manager.HttpManager;
import com.artie.gourmand.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ProfileFragment mFragment;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(savedInstanceState == null) {
            mFragment = ProfileFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_container, mFragment)
                    .commit();
            loadUserProfileData();
        }
    }

    private void loadUserProfileData() {
        int userID = User.getInstance().getDao().getId();

        Call<ProfileItemDao> call = HttpManager.getInstance()
                .getService()
                .loadProfile(userID, userID);

        call.enqueue(new Callback<ProfileItemDao>() {
            @Override
            public void onResponse(Call<ProfileItemDao> call, Response<ProfileItemDao> response) {
                if (response.isSuccessful()) {
                    ProfileItemDao dao = response.body();
                    mFragment.setDao(dao);
                }
            }

            @Override
            public void onFailure(Call<ProfileItemDao> call, Throwable t) {
                Log.d("Comment", "Fail load data: " + t.toString());
            }
        });
    }

}
