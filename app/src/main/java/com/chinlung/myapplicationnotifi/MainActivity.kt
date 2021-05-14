package com.chinlung.myapplicationnotifi

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    companion object {
        const val CHANNEL_ID = "12345"
        const val CHANNEL_NAME = "channelname"
        const val CHANNEL_DESCRIPTION = "description"
        const val TITLE = "title"
        const val TEXT = "text"
        const val IMPORTANT = NotificationManager.IMPORTANCE_HIGH
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_notification).setOnClickListener {

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(TITLE)
                .setContentText(TEXT)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            createNotificationChannel(builder)
//            btnNotify(builder)
        }


    }

    fun createNotificationChannel(builder: NotificationCompat.Builder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, IMPORTANT).apply {
                description = CHANNEL_DESCRIPTION
            }
            channel.enableVibration(true)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(12345, builder.build())
            }
        }

    }

    fun btnNotify(builder: NotificationCompat.Builder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_DESCRIPTION,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(true)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as
                    NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
            notificationManager.notify(12345, builder.build())
        }
    }
}