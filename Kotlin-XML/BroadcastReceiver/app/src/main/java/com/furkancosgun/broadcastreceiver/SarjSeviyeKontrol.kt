package com.furkancosgun.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Toast

class SarjSeviyeKontrol : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        println("FCSEC")
        Toast.makeText(p0, "Şarj Seviyeniz Azalmıştır Kalan Şarj: ${p1!!.getIntExtra(
            BatteryManager.EXTRA_LEVEL, -1)}", Toast.LENGTH_SHORT).show()
    }
}