package com.salton123.utils

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat
import com.salton123.eleph.R
import kt.getString

/**
 * User: newSalton@outlook.com
 * Date: 2019/3/25 10:25
 * ModifyTime: 10:25
 * Description:
 */
class ForegroundNotificationHelper {
    // 通知渠道的id
    var channelId = R.string.notification_channel_id.getString()
    var notificationId = 0x100
    var notifyTitle = R.string.notification_title.getString()
    var notifyContent = R.string.notification_content.getString()
    var resId = R.drawable.ic_launcher
    var pendingIntent: PendingIntent? = null
    private lateinit var mNotificationManagerCompat: NotificationManagerCompat
    private lateinit var service: Service
    fun attach(service: Service) {
        this.service = service
        mNotificationManagerCompat = NotificationManagerCompat.from(service)
    }

    private fun initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = service.getString(R.string.app_name)
            val channel = NotificationChannelCompat.Builder(channelId, NotificationManager.IMPORTANCE_DEFAULT)
                .setName(name)
                .setLightsEnabled(false)
                .setVibrationEnabled(false)
                .setShowBadge(false)
                .build()
            mNotificationManagerCompat.createNotificationChannel(channel)
        }
    }

    fun startForegroundNotification() {
        initNotificationChannel()
        val notification = NotificationCompat.Builder(service, channelId)
            .setContentTitle(notifyTitle) //标题
            .setContentText(notifyContent) //内容
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(resId) //小图标一定需要设置,否则会报错(如果不设置它启动服务前台化不会报错,但是你会发现这个通知不会启动),如果是普通通知,不设置必然报错
            .setLargeIcon(BitmapFactory.decodeResource(service.resources, resId))
            .build()
        service.startForeground(notificationId, notification)
    }

    fun deleteForegroundNotification() {
        with(mNotificationManagerCompat) {
            cancel(notificationId)
        }
        ServiceCompat.stopForeground(service, ServiceCompat.STOP_FOREGROUND_REMOVE)
    }
}