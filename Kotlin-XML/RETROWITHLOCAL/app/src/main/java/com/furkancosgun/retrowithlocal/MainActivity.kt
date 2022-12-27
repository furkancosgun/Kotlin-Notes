package com.furkancosgun.retrowithlocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.furkancosgun.retrowithlocal.Model.Users
import com.furkancosgun.retrowithlocal.Utilites.APIUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG = "XFC"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pdi = APIUtil.getUserDAOInterface()

        pdi.getAllUsers().enqueue(object :Callback<Users>{
            override fun onResponse(call: Call<Users>?, response: Response<Users>?) {
                response?.let {
                    Log.d(TAG, it.body().toString())
                }
            }

            override fun onFailure(call: Call<Users>?, t: Throwable?) {
                t?.let {
                    t.localizedMessage?.let { it1 -> Log.e(TAG, it1, ) }
                }
            }
        })
    }
}