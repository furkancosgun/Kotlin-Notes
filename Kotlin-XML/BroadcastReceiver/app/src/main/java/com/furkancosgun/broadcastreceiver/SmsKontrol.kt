package com.furkancosgun.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast
import java.text.Format

class SmsKontrol:BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        p1?.extras.let {
            //Bize gelen smsm pdus formatında ve array halinde
            val pdusObj = it?.get("pdus") as Array<Any>

            //Gelen Mesaj arrayinde dong
            pdusObj.forEach { param ->
                println("DEB0G:"+param.toString())
                val msj : SmsMessage //Messaj Sınıfını kullanarak

                //Marshmellow surum uzerinde işlemler farkldırı
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    val format = it.getString("format")
                    msj = SmsMessage.createFromPdu(param as ByteArray,format)//Formatlı halde
                }else{
                    msj = SmsMessage.createFromPdu(param as ByteArray)//Formatsız
                }
                val telNo = msj.displayOriginatingAddress
                val message = msj.displayMessageBody
                Toast.makeText(p0,"Tel No:${telNo}, Messaj:${message}",Toast.LENGTH_LONG).show()
                println("DEB0G: Tel No:${telNo}, Messaj:${message}")
            }
        }
    }
}