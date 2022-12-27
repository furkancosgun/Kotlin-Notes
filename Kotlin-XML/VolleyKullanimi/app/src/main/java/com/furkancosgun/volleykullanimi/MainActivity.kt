package com.furkancosgun.volleykullanimi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {

    val TAG = "XFC"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getAllPosts() {
        val url = "https://jsonplaceholder.typicode.com/posts"
        println("XFC")//Debug Line

        //APi Adresine bir get request oluştrudur
        val request = object : StringRequest(Method.GET, url, {
            Log.d(TAG, it)
        }, {
            Log.e(TAG, it.localizedMessage)
        }) { //Burdaki Alanı kullanarakta Parametre gonderebiliriz
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["key"] = "value"
                return params
            }
        }

        //Oluştrudugumuz requesti aşagıdaki şekilde çalıştrıırz
        Volley.newRequestQueue(this).add(request)
    }

    fun deletePosts() {
        val url = "https://jsonplaceholder.typicode.com/posts/1"
        val req = object : StringRequest(Method.DELETE, url, {}, {}) {}
        Volley.newRequestQueue(this).add(req)
    }

    fun addNewPost() {
        val url = "https://jsonplaceholder.typicode.com/posts"

        val req = object : StringRequest(Method.POST, url, {
            Log.d(TAG, it)
        }, {
            Log.e(TAG, it.localizedMessage)
        }) {
            //BODY Alanı
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["userId"] = "1"
                params["title"] = "Title Params"
                params["body"] = "Post Body Data"
                return params
            }
        }
        Volley.newRequestQueue(this).add(req)
    }

    fun updatePost() {
        val url = "https://jsonplaceholder.typicode.com/posts/1"

        val req = object : StringRequest(Method.PUT, url, {
            Log.d(TAG, it)
        }, {
            Log.e(TAG, it.localizedMessage)
        }) {
            //BODY Alanı
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["userId"] = "1"
                params["title"] = "New Title Params"
                params["body"] = "New Post Body Data"
                return params
            }
        }
        Volley.newRequestQueue(this).add(req)
    }


}