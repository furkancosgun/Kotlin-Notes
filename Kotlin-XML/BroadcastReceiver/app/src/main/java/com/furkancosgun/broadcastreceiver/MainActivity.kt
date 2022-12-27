package com.furkancosgun.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var sarjSeviyeKontrol: SarjSeviyeKontrol

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sarjSeviyeKontrol  = SarjSeviyeKontrol()
    }

    override fun onResume() {
        super.onResume()
        //Filter Kullanarak Receiver Yardımıyla Şarjın Azaldıgı Durumlarda Direk Class Yapısını Kullanarak
        //Ekarana Mesaj Veya Herhangi Bir Şey Verebilirz
        val filter = IntentFilter()
        filter.addAction("android.intent.action.BATTERY_LOW")
        registerReceiver(sarjSeviyeKontrol,filter)
    }

    override fun onStop() {
        super.onStop()
        //Uygulama Arka Plana Alındıgı Durumlar Receiver İşlemi Durdurulur
        unregisterReceiver(sarjSeviyeKontrol)
    }
}


