package com.artie.gourmand.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.artie.gourmand.R;
import com.artie.gourmand.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

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

}
