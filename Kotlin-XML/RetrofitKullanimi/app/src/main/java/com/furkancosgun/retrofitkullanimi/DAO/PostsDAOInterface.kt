package com.furkancosgun.retrofitkullanimi.DAO

import com.furkancosgun.retrofitkullanimi.Model.Post
import com.furkancosgun.retrofitkullanimi.Model.Posts
import retrofit2.Call

import retrofit2.http.*

interface PostsDAOInterface {
    //getAllPosts = posts -> GET
    //savePost = posts -> POST -> userId,title,body
    //updatePost = posts/1 -> PUT -> userId,title,body
    //deletePost = posts/1 -> DELETE

    //-------------------------//

    @POST("posts")
    @FormUrlEncoded//Türkçe Parametre Gonderimi
    fun createPost(
        @Field("userId") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<Post>

    @GET("posts")
    fun getAllPosts(): Call<Posts>

    @PUT("posts/{postId}")
    @FormUrlEncoded
    fun updatePost(
        @Path("postId") postId: Int,//Url Path
        @Field("userId") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<Post>

    @DELETE("posts/{postId}")
    fun deletePost(@Path("postId") postId: Int): Call<String>
}