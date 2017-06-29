package com.artie.gourmand;

import android.content.Context;

/**
 * Created by ANFIELD on 19/5/2560.
 */

public class Contextor {

    private static Contextor instance;
    private Context mContext;

    private Contextor() {

    }

    public static Contextor getInstance() {
        if (instance == null) {
            instance = new Contextor();
        }

        return instance;
    }

    public void init(Context mContext) {
        this.mContext = mContext;
    }

    public Context getContext() {
        return mContext;
    }

}
