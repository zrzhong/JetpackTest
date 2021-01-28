package com.zzr.jetpacktest.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.content.ContextCompat.startForegroundService
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.activity.MainActivity


// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_FOO = "com.zzr.jetpacktest.service.action.FOO"
private const val ACTION_BAZ = "com.zzr.jetpacktest.service.action.BAZ"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "com.zzr.jetpacktest.service.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.zzr.jetpacktest.service.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1) ?: ""
                val param2 = intent.getStringExtra(EXTRA_PARAM2) ?: ""
                handleActionFoo(param1, param2)
            }
            ACTION_BAZ -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1) ?: ""
                val param2 = intent.getStringExtra(EXTRA_PARAM2) ?: ""
                handleActionBaz(param1, param2)
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String, param2: String) {

        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0);
//        val notification = NotificationCompat.Builder(this, "1001")
//            .setContentTitle("This is content title.")
//            .setContentText("This is content text.")
//            .setWhen(System.currentTimeMillis())
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setLargeIcon(
//                BitmapFactory.decodeResource(
//                    resources,
//                    R.mipmap.ic_launcher
//                )
//            )
//            .setContentIntent(pi)
//            .build();
//        startForeground(1, notification)

        val builder: Notification.Builder = Notification.Builder(this.applicationContext)
            .setContentIntent(pi) // 设置PendingIntent
            .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
            .setContentTitle(resources.getString(R.string.app_name))
            .setContentText("正在上传...") // 设置上下文内容
            .setWhen(System.currentTimeMillis()) // 设置该通知发生的时间

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //修改安卓8.1以上系统报错
            val notificationChannel = NotificationChannel(
                "CHANNEL_ONE_ID",
                "CHANNEL_ONE_NAME",
                NotificationManager.IMPORTANCE_MIN
            )
            notificationChannel.enableLights(false) //如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
            notificationChannel.setShowBadge(false) //是否显示角标
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_SECRET
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
            builder.setChannelId("CHANNEL_ONE_ID")
        }

        val notification: Notification = builder.build() // 获取构建好的Notification

        notification.defaults = Notification.DEFAULT_SOUND //设置为默认的声音

        startForeground(1, notification)
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String, param2: String) {
        TODO("Handle action Baz")
    }

    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            startForegroundService(context, intent)
//            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionBaz(context: Context, param1: String, param2: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_BAZ
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }
}