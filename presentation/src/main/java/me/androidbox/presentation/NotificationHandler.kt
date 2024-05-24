package me.androidbox.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri

class NotificationHandler(
    private val context: Context
) {
    companion object {
        private const val CHANNEL_ID = "active_run"
        private val CHANNEL_NAME = "reset_password"
    }

    private val notificationManager =
        context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    fun <T> start(activityClass: Class<T>, message: String) {

        createNotificationChannel()

        val activityIntent = Intent(context, activityClass).apply {
            this.data = "busbyrunner://active_run".toUri()
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        val pendingIntent = TaskStackBuilder.create(context)
            .addNextIntentWithParentStack(activityIntent)
            .getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat
            .Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(context.getString(R.string.check_your_email))
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(100, notification)
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}






/*
inline fun <reified T : Any> getSystemName() {
    serviceClassName(T::class.java)
}

fun <T> serviceClassName(nameOfClass: Class<T>) {
    println(nameOfClass)
}
*/

