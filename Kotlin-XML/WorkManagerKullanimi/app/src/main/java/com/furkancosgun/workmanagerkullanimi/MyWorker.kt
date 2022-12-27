package com.furkancosgun.workmanagerkullanimi

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


//Worker sınfıını kullanamk için context ve worker parametrelerine ihityaç var
//worker sınıfından kalıtım alınca doWork yapsıı implement etmemiz gerekir
//bu yapıyıla istedigimiz işlemleri gerçeklleştirebilriiz
class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("XFC", "WORKER SINIFI ÇALIŞTI")
        return Result.success()
    }


}