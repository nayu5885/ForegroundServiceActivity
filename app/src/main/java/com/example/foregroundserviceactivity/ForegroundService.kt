package com.example.foregroundserviceactivity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.Service.STOP_FOREGROUND_DETACH
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat.STOP_FOREGROUND_DETACH
import java.security.Provider

class ForegroundService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val name = "通知のタイトル的情報を設定"
        val id = "casareal_foreground"
        val notifyDescription = "この通知の詳細情報を設定します"

       if (notificationManager.getNotificationChannel(id) == null) {
           val mChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
           mChannel.apply {
               description = notifyDescription
            }
              notificationManager.createNotificationChannel(mChannel)
        }

        val notification = Notification.Builder(this, id)
            .apply {
                setContentTitle("こんにちは")
                setContentText("さようなら")
                setSmallIcon(R.drawable.ic_launcher_background)
                //.setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }.build()
     // notificationManager .notify(1, notification)

        Thread(
            Runnable {
                (0..5).map {
                    Thread.sleep(1000)

                }

                stopForeground(STOP_FOREGROUND_DETACH)

            }).start()

        startForeground(1, notification)

        return START_STICKY

    }

}