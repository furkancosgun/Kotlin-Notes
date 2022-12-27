package com.furkancosgun.retrofitkullanimi.Model

import com.google.gson.annotations.SerializedName

typealias Posts = List<Post>

data class Post(
    @SerializedName("userId")
    val userID: Long,
    val id: Long,
    val title: String,
    val body: String
)
