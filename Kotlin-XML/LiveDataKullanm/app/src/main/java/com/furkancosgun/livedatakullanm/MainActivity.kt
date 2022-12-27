package com.furkancosgun.livedatakullanm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.furkancosgun.livedatakullanm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        viewModel.sonuc.observe(this) {
            bind.txtSonuc.text = it
        }

        bind.btnDown.setOnClickListener {
            viewModel.subtractOne()
        }
        bind.btnUp.setOnClickListener {
            viewModel.addOne()
        }
    }
}