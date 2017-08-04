package com.artie.gourmand.model;

import android.content.Context;

import com.artie.gourmand.Contextor;
import com.artie.gourmand.dao.MemberItemDao;

/**
 * Created by ANFIELD on 16/6/2560.
 */

public class User {

    private static User instance;
    private Context mContext;
    private MemberItemDao mDao;

    private User() {
        init();
    }

    public static User getInstance() {
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void init() {
        mContext = Contextor.getInstance().getContext();
    }

    public Context getContext() {
        return mContext;
    }

    public void setDao(MemberItemDao dao) {
        mDao = dao;
    }

    public MemberItemDao getDao() {
        return mDao;
    }
    
}
