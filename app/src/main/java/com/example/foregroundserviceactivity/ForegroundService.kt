package com.example.foregroundserviceactivity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.security.Provider

class ForegroundService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val name = "通知のタイトル的情報を設定"
        val id = "casareal_foreground"
        val notifyDescription = "この通知の詳細情報を設定します"

        if (manager.getNotificationChannel(id) == null) {
            val mChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            mChannel.apply {
                description = notifyDescription
            }
            manager.createNotificationChannel(mChannel)
        }


        val notification = NotificationCompat.Builder(this,id).apply {
            mContentTitle = "通知のタイトル"
            mContentText = "通知の内容"
            setSmallIcon(R.drawable.ic_launcher_background)
        }.build()

        Thread(
            Runnable {
                (0..5).map {
                    Thread.sleep(1000)

                }

                stopForeground(Service.STOP_FOREGROUND_DETACH)

            }).start()

        startForeground(1, notification)

        return START_STICKY

    }
}