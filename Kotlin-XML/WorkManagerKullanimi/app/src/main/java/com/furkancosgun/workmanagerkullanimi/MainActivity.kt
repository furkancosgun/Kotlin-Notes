package com.furkancosgun.workmanagerkullanimi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.furkancosgun.workmanagerkullanimi.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        createWorkManagerWithContingent()

        createWorkManagerWithPeriodic()
        binding.btnWork.setOnClickListener { //One Time Request
            val istek = OneTimeWorkRequestBuilder<MyWorker>()
                .setInitialDelay(3, TimeUnit.SECONDS)
                .build()
            WorkManager.getInstance(this).enqueue(istek)

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(istek.id).observeForever {
                val state = it.state
                Log.d("XFC", "onWork: $state")
            }
        }
    }

    //Koşula baglı
    fun createWorkManagerWithContingent() {
        //Builder den sonraki alanı kullanarak belirli koşullarda çalıştırma saglayabilriz
        val calismaKosulu =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val request = OneTimeWorkRequestBuilder<MyWorker>()
            .setInitialDelay(3, TimeUnit.SECONDS)
            .setConstraints(calismaKosulu)
            .build()
        WorkManager.getInstance(this).enqueue(request)

        //Burdaki observe bizim istegin durumuna gore kontrol saglar ve çalışır
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observeForever {
            val state = it.state
            Log.d("XFC", "onWork: $state")
        }
    }

    fun createWorkManagerWithPeriodic() {
        val req = PeriodicWorkRequestBuilder<MyWorkerNotif>(5, TimeUnit.SECONDS)
            .setInitialDelay(10, TimeUnit.SECONDS)//Başlangıc gecikmesi
            .build()

        WorkManager.getInstance(this).enqueue(req)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(req.id).observe(this) {
            val state = it.state
            Log.d("XFC", "createWorkManagerWithPeriodic: $state")
        }
    }

}