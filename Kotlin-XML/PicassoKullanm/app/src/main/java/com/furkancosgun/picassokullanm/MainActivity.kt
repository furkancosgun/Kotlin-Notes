package com.furkancosgun.picassokullanm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.furkancosgun.picassokullanm.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var design: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = ActivityMainBinding.inflate(layoutInflater)
        setContentView(design.root)
        design.btnGetImage.setOnClickListener {
            val url = design.txtUrl.text.toString()
            Picasso.with(this)
                .load(url)
                //.placeholder(R.drawable.ic_launcher_foreground) Resim Yuklenirken Gozukur
                //Hata Alınırsa Gozukur .error(R.drawwww)
                //.resize(10,10) Uzunluk ve genişlik
                .into(design.imgView)
        }
    }
}