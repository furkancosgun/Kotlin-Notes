package com.furkancosgun.retrofitkullanimi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.furkancosgun.retrofitkullanimi.DAO.PostsDAOInterface
import com.furkancosgun.retrofitkullanimi.Model.Post
import com.furkancosgun.retrofitkullanimi.Model.Posts
import com.furkancosgun.retrofitkullanimi.Utilities.APIUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
    //Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.1.0'

    //Json Converter
    implementation 'com.google.code.gson:gson:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
 */
class MainActivity : AppCompatActivity() {
    val TAG = "XFC"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pdi = APIUtil.getPostsDAOInterface()//Interface Yardımıyla daha basitleştirdik
        //createPost(pdi)
        //getAllPosts(pdi)
        //updatePost(pdi)
        deletePost(pdi)
    }

    fun updatePost(pdi: PostsDAOInterface) {
        pdi.updatePost(1, 20, "New Updated Title", "New Updated Body")
            .enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                    response?.let {
                        Log.d(TAG, it.body().toString())
                    }
                }

                override fun onFailure(call: Call<Post>?, t: Throwable?) {
                    t?.let {
                        it.localizedMessage?.let { it1 -> Log.e(TAG, it1) }
                    }
                }
            })
    }

    fun deletePost(pdi: PostsDAOInterface) {
        pdi.deletePost(1).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                response?.let {
                    Log.d(TAG, it.body().toString())
                }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                t?.let {
                    it.localizedMessage?.let { it1 -> Log.e(TAG, it1) }
                    Log.e(TAG, it.toString())
                }
            }
        })
    }

    fun getAllPosts(pdi: PostsDAOInterface) {
        pdi.getAllPosts().enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>?, response: Response<Posts>?) {
                response.let {

                    val posts = response!!.body()
                    posts.forEach {
                        Log.d(TAG, it.toString())
                    }
                }
            }

            override fun onFailure(call: Call<Posts>?, t: Throwable?) {
                t.let {
                    t!!.localizedMessage?.let { it1 -> Log.e(TAG, it1) }
                }
            }
        })
    }

    fun createPost(pdi: PostsDAOInterface) {
        //Interface içindeki fonksyonu kullanırız ve bu fonksyonun bize post parametresi dondurcegini bekleriz
        pdi.createPost(1, "Title", "Body Field").enqueue(
            object : Callback<Post> {
                //Cevap
                override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                    response.let {
                        Log.d(TAG, it!!.body().toString())
                    }
                }

                //Hata Alınırısa
                override fun onFailure(call: Call<Post>?, t: Throwable?) {
                    t.let {
                        Log.e(TAG, it!!.localizedMessage.toString())
                    }
                }
            }
        )
    }
}