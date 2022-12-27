package com.furkancosgun.viewmodelyaps

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.furkancosgun.viewmodelyaps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*
    implementation("android.arch.lifecycle:viewmodel:1.1.1")
    implementation("androidx.activity:activity-ktx:1.6.1")
     */
    //ViewModelYapısnı kullanarak arayuz içerisindeki işlemsel kodlar parçalanır
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalc.setOnClickListener {
            viewModel.topla(binding.txtS1.text.toString(), binding.txtS2.text.toString())
            binding.txtSonuc.text = viewModel.sonuc
        }
    }
}