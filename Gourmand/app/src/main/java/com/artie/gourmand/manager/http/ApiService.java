package com.artie.gourmand.manager.http;

import com.artie.gourmand.dao.CommentItemCollectionDao;
import com.artie.gourmand.dao.MemberItemCollectionDao;
import com.artie.gourmand.dao.PostItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ANFIELD on 19/5/2560.
 */

public interface ApiService {

    @GET("posts.json")
    Call<PostItemCollectionDao> loadPosts();

    @GET("members.json")
    Call<MemberItemCollectionDao> loadMembers();

    @GET("posts/{postID}/comments.json")
    Call<CommentItemCollectionDao> loadComments(@Path("postID") int postID);

}
