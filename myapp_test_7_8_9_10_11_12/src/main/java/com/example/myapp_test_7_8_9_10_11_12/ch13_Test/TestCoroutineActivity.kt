package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestCoroutineBinding
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class TestCoroutineActivity : AppCompatActivity() {
    lateinit var binding : ActivityTestCoroutineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testCoBtn.setOnClickListener {

            //오래걸리는 작업 추가. -> 화면에서 처리하기보다는 -> 비동기식으로 처리를 하겠다.
            // 비동기식으로 처리하는 방법이 기존에 스레드(핸들러작업), 코루틴으로 작업하는 부분 소개.

            // binding.resultCoView.text = "time: $time, sum : $sum"


            //방법1, 비동기 작업, Handler 방식, deprecated , 참고만,
            val handler = object : Handler() {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    binding.resultCoView.text = "time: ${msg.arg2}, sum : ${msg.arg1}"
                }
            }

            // thread 작업, 비동기 작업. 코루틴에 비해서 성능이 조금 무겁다.
            thread {
                // 무거운 작업(오래 걸리는 작업 넣기.)
                var sum = 0L
                var time = measureTimeMillis {
                    for(i in 1..9_000_000_000){
                        sum += i
                    }
                }
                //  오래 걸리는 작업의 결과값을 메세지를 통해서, 핸들러에게 전달하는 로직.
                val message = Message()
                message.arg1 = sum.toInt()
                message.arg2 = time .toInt()
                handler.sendMessage(message)

                Log.d("lsy","time : $time")
            }

        }




    }
    // onCreate
}