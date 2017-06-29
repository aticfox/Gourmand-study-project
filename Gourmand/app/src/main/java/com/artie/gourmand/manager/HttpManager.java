package com.artie.gourmand.manager;

import android.content.Context;

import com.artie.gourmand.Contextor;
import com.artie.gourmand.manager.http.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ANFIELD on 19/5/2560.
 */

public class HttpManager {

    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private Context mContext;
    private ApiService mService;

    private HttpManager() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.104:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return mService;
    }

}
