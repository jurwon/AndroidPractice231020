package com.example.myapp_test_7_8_9_10_11_12.ch10_Test

import android.app.NotificationChannel
import android.app.NotificationManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class ReplyReceiver : BroadcastReceiver() {
    // 알림에서 원격으로 입력한 내용을 받아서 처리하는 수신기 역할.

    override fun onReceive(context: Context, intent: Intent) {
        val replyTxt = RemoteInput.getResultsFromIntent(intent)?.getCharSequence("key_text_reply")
        Log.d("sjw", "replyTxt : $replyTxt")

        // 답장을 받는 동시에 또, 알림을 보내는 로직.
        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)

            //채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(context, channelId)
        } else {
            builder = NotificationCompat.Builder(context)
        }

        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("Content Title")
        builder.setContentText("Content Massage")

        manager.notify(11, builder.build())
    }
}