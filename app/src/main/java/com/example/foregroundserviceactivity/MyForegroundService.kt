package com.example.foregroundserviceactivity

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.core.app.NotificationCompat

class MyForegroundService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("tag","start")

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

      //  val pendingIntent: PendingIntent =
         //   Intent(this, MainActivity::class.java).let { notificationIntent ->
           //     PendingIntent.getActivity(this, 0, notificationIntent, 0)
          //  }


        val notification = NotificationCompat.Builder(this, id)
            .apply {
                setContentTitle("こんにちは")
                setContentText("さようなら")
                setSmallIcon(R.drawable.ic_launcher_background)
                //.setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }.build()
      notificationManager.notify(1, notification)

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