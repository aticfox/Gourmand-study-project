package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.artie.gourmand.Contextor;
import com.artie.gourmand.R;
import com.artie.gourmand.fragment.LoginFragment;
import com.artie.gourmand.model.User;

public class LoginActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_container, new LoginFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (User.getInstance().getDao() != null) {
            Intent intent = MainActivity.getStartIntent(Contextor.getInstance().getContext(), MainActivity.LAUNCH_SCREEN_FEED);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    
}
