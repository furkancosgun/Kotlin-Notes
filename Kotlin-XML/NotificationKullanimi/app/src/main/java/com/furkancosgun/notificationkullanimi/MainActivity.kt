package com.furkancosgun.notificationkullanimi

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendNotification(view: View) {

        //Bildirim
        var builder: NotificationCompat.Builder

        //Bildirim Yöneticisi
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Bildirime Basıldıgında Hangi Sayfaya Gitcegi
        val intent = Intent(this, MainActivity::class.java)

        //Gidilcek Sayfaya Yonlendirme
        //requestcode alanı ile basıldıgında giidlcek olan ekranı yakalarız biizm için şuanlık gereksiz
        val gidilcekIntent: PendingIntent

        //Surum Farklılıgından Dolayı Sayfaya geçiş işlemni hem eski hemde yeni sürümler için yaptık
        gidilcekIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(
                this,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }


        //Yeni Sürümlerde Bildirim Hangi Kanaldan / Hangi Tipte Gidio Onu Bildirmemiz Gerekiyor
        builder =
            NotificationCompat.Builder(this) //Yeni sürüm oldugu durumda builder tekrardan oluştrulacak

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val kanalId = "KanalID"
            val kanalAd = "KanalAD"
            val kanalTanimi = "Kanalım"
            val kanalOnceligi = NotificationManager.IMPORTANCE_HIGH
            var kanal: NotificationChannel? = notificationManager.getNotificationChannel(kanalId)

            if (kanal == null) {
                kanal = NotificationChannel(kanalId, kanalAd, kanalOnceligi)
                kanal.description = kanalTanimi
                notificationManager.createNotificationChannel(kanal)
            }
            builder = NotificationCompat.Builder(this, kanalId)
        }

        //Bildirim içeriği vs.
        builder.apply {
            setContentTitle("Title")
            setContentText("Content Text")
            setContentIntent(gidilcekIntent) //Hangi Sayfaya Gitcegi
            setSmallIcon(R.drawable.ic_launcher_foreground) //İkonu
            setAutoCancel(true)//Uzerine Basıldıktan sonra bildirimin silinmesi
            setSubText("Sub Text")

            //Layout Hazırlanıp Eklendi Ozel Tasarım Gosterilmesi
            // setContent(RemoteViews(packageName, R.layout.notification_ui))
            //setCustomBigContentView(RemoteViews(packageName, R.layout.notification_ui))
            priority = Notification.PRIORITY_HIGH //Bildirim Onceligi
        }

        notificationManager.notify(1, builder.build())//Bildirim Oluştrulur
    }
}