package com.artie.gourmand.manager.http;

import com.artie.gourmand.dao.CommentItemCollectionDao;
import com.artie.gourmand.dao.MemberItemCollectionDao;
import com.artie.gourmand.dao.PostItemCollectionDao;
import com.artie.gourmand.dao.ProfileItemDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ANFIELD on 19/5/2560.
 */

public interface ApiService {

    @GET("posts.json")
    Call<PostItemCollectionDao> loadPosts();

    @GET("members.json")
    Call<MemberItemCollectionDao> loadMembers();

    @GET("posts/{post_id}/comments.json")
    Call<CommentItemCollectionDao> loadComments(@Path("post_id") int postID);

    @GET("members/1/profile.json")
    Call<ProfileItemDao> loadProfile();

    @POST("posts/{post_id}/comments.json")
    Call<CommentItemCollectionDao> addComment(@Path("post_id") int postID,
                                              @Query("text") String text,
                                              @Query("member_id") int memberID);

}
