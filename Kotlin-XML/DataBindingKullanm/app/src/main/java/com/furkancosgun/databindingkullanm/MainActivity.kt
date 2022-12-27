package com.furkancosgun.databindingkullanm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.furkancosgun.databindingkullanm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "XFC"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Data Binding Sınıfı yardımıyıla gorunumu koda bağlamış olduk
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activityObject = this
        binding.txtSonuc = 0.toString()
    }

    fun buttonTopla(s1: String?, s2: String?) {
        var sayi1: Int = 0
        var sayi2: Int = 0
        s1?.toInt()?.let {
            sayi1 = it
        }
        s2?.toInt()?.let {
            sayi2 = it
        }

        binding.txtSonuc = (sayi1 + sayi1).toString()
    }
}