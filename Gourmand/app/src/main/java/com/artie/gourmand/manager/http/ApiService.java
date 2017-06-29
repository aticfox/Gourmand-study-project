package com.artie.gourmand.manager.http;

import com.artie.gourmand.dao.PostItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ANFIELD on 19/5/2560.
 */

public interface ApiService {

    @GET("posts.json")
    Call<PostItemCollectionDao> loadPosts();

}
