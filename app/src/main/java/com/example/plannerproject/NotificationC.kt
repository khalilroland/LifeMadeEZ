package com.example.plannerproject

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

//KR: class for notifications (In: Progress)
class NotificationC : Application() {

    override fun onCreate() {
        super.onCreate()

        // KR: Check Android studio version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // KR: Create a notification channel
            // KR: set to high importance level
            val channel = NotificationChannel(
                "cID",
                "cName",
                NotificationManager.IMPORTANCE_HIGH
            )

            // KR: set val to  NotificationManager service
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // KR: Creates channel
            notificationManager.createNotificationChannel(channel)
        }
    }
}