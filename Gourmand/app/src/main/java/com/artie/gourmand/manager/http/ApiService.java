package com.artie.gourmand.manager.http;

import com.artie.gourmand.dao.CommentItemCollectionDao;
import com.artie.gourmand.dao.MemberItemCollectionDao;
import com.artie.gourmand.dao.MemberItemDao;
import com.artie.gourmand.dao.PostItemCollectionDao;
import com.artie.gourmand.dao.PostItemDao;
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
    Call<PostItemCollectionDao> loadPosts(@Query("member_id") int memberID);

    @GET("members.json")
    Call<MemberItemCollectionDao> loadMembers(@Query("member_id") int memberID);

    @GET("posts/{post_id}/comments.json")
    Call<CommentItemCollectionDao> loadComments(@Path("post_id") int postID,
                                                @Query("member_id") int memberID);

    @GET("posts/{post_id}.json")
    Call<PostItemDao> loadPost(@Path("post_id") int postID,
                               @Query("member_id") int memberID);

    @GET("members/{member_ID}/profile.json")
    Call<ProfileItemDao> loadProfile(@Path("member_ID") int userID,
                                     @Query("member_id") int memberID);

    @GET("members/{user_ID}/followers.json")
    Call<MemberItemCollectionDao> loadFollowers(@Path("user_ID") int userID,
                                                @Query("member_id") int memberID);

    @GET("members/{user_ID}/followings.json")
    Call<MemberItemCollectionDao> loadFollowings(@Path("user_ID") int userID,
                                                 @Query("member_id") int memberID);

    @POST("posts/{post_id}/comments.json")
    Call<CommentItemCollectionDao> addComment(@Path("post_id") int postID,
                                              @Query("text") String text,
                                              @Query("member_id") int memberID);

    @POST("posts.json")
    Call<PostItemDao> addPost(@Query("member_id") int memberID,
                              @Query("image_url") String imageURL,
                              @Query("caption") String caption,
                              @Query("location_lat") double locationLatitude,
                              @Query("location_long") double locationLongitude,
                              @Query("location_name") String locationName);

    @POST("authen/sign_in.json")
    Call<MemberItemDao> login(@Query("email") String email,
                              @Query("password") String password);

}
