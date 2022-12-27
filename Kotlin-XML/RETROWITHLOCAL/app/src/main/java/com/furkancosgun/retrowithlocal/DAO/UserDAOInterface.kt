package com.furkancosgun.retrowithlocal.DAO

import com.furkancosgun.retrowithlocal.Model.User
import com.furkancosgun.retrowithlocal.Model.Users
import retrofit2.Call
import retrofit2.http.*

interface UserDAOInterface {

    @POST("users")
    @FormUrlEncoded
    fun createUser(
        @Field("username") username : String,@Field("password") password : String,
    ): Call<User>

    @GET("users")
    fun getAllUsers(): Call<Users>

    @PUT("users")
    @FormUrlEncoded
    fun updateUser(
        @Field("username") username : String,@Field("password") password : String,
    ): Call<User>

    @DELETE("users/{userId}")
    fun deleteUser(@Path("userId") postId: Int): Call<String>
}
