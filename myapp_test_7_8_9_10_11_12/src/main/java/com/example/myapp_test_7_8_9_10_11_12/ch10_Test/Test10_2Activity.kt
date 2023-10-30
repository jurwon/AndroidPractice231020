package com.example.myapp_test_7_8_9_10_11_12.ch10_Test

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTest102Binding

class Test10_2Activity : AppCompatActivity() {
    //추가
    lateinit var activityTest102Binding: ActivityTest102Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //추가
        activityTest102Binding = ActivityTest102Binding.inflate(layoutInflater)
        // 변경
        setContentView(activityTest102Binding.root)

        // 버튼의 요소를 선택해서, 알림을 보내는 로직을 추가할 예정.
        activityTest102Binding.notiBtn.setOnClickListener {
            // 알림 추가 설정.
            // getSystemService(NOTIFICATION_SERVICE) 결과 형이 object 이어서,
            // as NotificationManager -> 형 변환.
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            // sdk 버전에 따라서, 분기, 기능의 패키지명 또는 구현 형식을 달라져서.
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // sdk 26 버전 이후로 , 1번 양식
                //채널 설정
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel (
                    channelId,
                    channelName,
                    // 전달하려는 메세지 전달 강도(레벨), 알림음 설정, 헤드업도 표시등. 강력하게 전달 하겠다.
                    NotificationManager.IMPORTANCE_HIGH
                )

                // 채널의 정보 및 부가 옵션 설정.
                channel.description = "My Channel One 설명"
                // 알림의 갯수를 아이콘 표시
                channel.setShowBadge(true)
                // 채널에 시스템 알림을 설정 연결.
                // uri, 음원, 이미지, 영상등의 위치를 알려주는 타입 보시면.
                // 예) http://도메인주소, 예) content://settings/system/notification_sound
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                Log.d("lsy","uri 의 위치가 어떻게 되니? : ${uri}")
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                channel.setSound(uri,audioAttributes)
                channel.enableLights(true)
                channel.lightColor = Color.RED
                // 진동의 간격마다, 세기 주기설정.
                channel.vibrationPattern = longArrayOf(100,200,100,200)

                // Notification Manager에 채널 등록 하기.
                manager.createNotificationChannel(channel)

                builder = NotificationCompat.Builder(this@Test10_2Activity, channelId)
            } else {
                // 26 버전 이하 버전시 사용하는 형식.
                builder = NotificationCompat.Builder(this@Test10_2Activity)
            }

            //아이콘, 테마, 툴바, 액션바 overlay 단어가 보이면 투명지, 뒤에 부분이 보인다.
            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("알림 제목")
            builder.setContentText("알림의 메세지 내용")

            // 알림 옵션
            // 알림 메세지 창 클릭시, 페이지 이동. 기존에 사용했던 , 인텐트 방식과 비슷.
            val intent = Intent(this@Test10_2Activity,Test10_1Activity::class.java )
            val pendingIntent = PendingIntent.getActivity(this@Test10_2Activity,10,intent,
                PendingIntent.FLAG_IMMUTABLE)
            // 2번째 액션 인텐트 테스트 하기위해 잠시 주석.
            //builder.setContentIntent(pendingIntent)

            // 특정 액션 추가 기능 넣기.
            val actionIntent = Intent(this@Test10_2Activity,OneReceiver::class.java)
            val actionPendingIntent = PendingIntent.getBroadcast(this@Test10_2Activity,20,
                actionIntent,PendingIntent.FLAG_IMMUTABLE)
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more,
                    "Action",
                    actionPendingIntent
                ).build()
            )

            // 특정 액션 추가 부분인데, 위에는 기본 액션 1개를 추개했고, 답장이라는 추가 액션 넣기.
            val KEY_TEXT_REPLY = "key_text_reply"
            val replyLabel : String = "답장"
            var remoteInput : RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }
            val replyIntent = Intent(this@Test10_2Activity, ReplyReceiver::class.java)
            val replyPendingIntent = PendingIntent.getBroadcast(this@Test10_2Activity,30,
                replyIntent,PendingIntent.FLAG_MUTABLE)
            // 답장 액션 추가하기.
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more,
                    "답장",
                    replyPendingIntent
                ).addRemoteInput(remoteInput).build()
            )

            // 프로그레스 진행 바 확인 해보기.
//            builder.setProgress(100,0,false)
//            thread {
//                for (i in 1..100) {
            //언제든지 완료된 작업량을 추정할 수 있는 경우 setProgress(max, progress, false)를 호출하여
            // 그림 4와 같이 표시기의 '확정된' 형태를 사용하세요.
            // 첫 번째 매개변수는 '완료' 값(예: 100)이고
            // 두 번째 매개변수는 현재 진행률이며
            // 마지막 매개변수는 이 진행률 표시줄이 확정된 진행률 표시줄임을 나타냅니다.
            //
            //작업이 진행되면 업데이트된 progress 값으로
            // setProgress(max, progress, false)를 계속 호출하고 알림을 다시 발행합니다.

//                    builder.setProgress(100,i,false)
//                    manager.notify(11, builder.build())
//                    SystemClock.sleep(100)
//                }
//            }

            // 큰 이미지를 첨부해서 알림 보내기
            // 안드로이드 에서 사용하는 이미지 타입 비트맵, 바이트 등.
            val bigPicture = BitmapFactory.decodeResource(resources, R.drawable.bob)
            val bigStyle = NotificationCompat.BigPictureStyle()
            bigStyle.bigPicture(bigPicture)
            builder.setStyle(bigStyle)

            // 긴텍스트
            val bigTextStyle = NotificationCompat.BigTextStyle()
            bigTextStyle.bigText(resources.getString(R.string.long_text))
            builder.setStyle(bigTextStyle)

            // 박스 스타일 형식
            val boxStyle = NotificationCompat.InboxStyle()
            boxStyle.addLine("1코스 - 짜장면")
            boxStyle.addLine("2코스 - 우동")
            boxStyle.addLine("1코스 - 잡채밥")
            boxStyle.addLine("1코스 - 해물우동")
            builder.setStyle(boxStyle)



            //알림 발생 시키기
            manager.notify(11,builder.build())
        }


    }
}