### Implementation

```gradle
//build_gradle(:app)
implementation "com.google.accompanist:accompanist-permissions:0.27.1"
```

### Permission

```xml
<!--AndroidManifest.xml-->
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

### FullCode

```kotlin
//ActivityMain.kt
package com.example.lerandroidslemleri

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.lerandroidslemleri.ui.theme.İlerAndroidİslemleriTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            İlerAndroidİslemleriTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        RequestNotificationPermission()
                    }else{
                        MainView()
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun MainView() {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { showNotification(context) }) {
            Text(text = "Show Notification")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun RequestNotificationPermission() {
    val notificationPermissionState =
        rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)
    if (notificationPermissionState.status.isGranted) {
        MainView()
    } else {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textToShow = if (notificationPermissionState.status.shouldShowRationale) {
                "The notification permission is important for this app. Please grant the permission."
            } else {
                "notifications not available"
            }

            Text(textToShow)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { notificationPermissionState.launchPermissionRequest() }) {
                Text("Request permission")
            }
        }
    }
}

fun showNotification(context: Context) {
    /*
      <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
     */
    val builder: NotificationCompat.Builder
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //Kanal Oluştrulur Yeni Sürümler İçin
        val channelId = "id"
        val channelName = "name"
        val channelDescription = "desc"
        val channelPriority = NotificationManager.IMPORTANCE_HIGH

        var channel: NotificationChannel? = notificationManager.getNotificationChannel(channelId)
        if (channel == null) {
            channel =
                NotificationChannel(channelId, channelName, channelPriority)
            channel.description = channelDescription
            notificationManager.createNotificationChannel(channel)
        }
        //Bildirim İçeriği
        builder = NotificationCompat.Builder(context, channelId)

        builder.setContentTitle("Title")
            .setContentText("Content")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)

    } else {
        //Bildirim İçeriği
        builder = NotificationCompat.Builder(context)

        builder.setContentTitle("Title")
            .setContentText("Content")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)//Bildirimi Seçtiginde otomatik bildirimi kapatır
            .priority = Notification.PRIORITY_HIGH
    }
    with(NotificationManagerCompat.from(context)) {
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(808, builder.build())
    }

}

```
