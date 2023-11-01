package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
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
//            val handler = object : Handler() {
//                override fun handleMessage(msg: Message) {
//                    super.handleMessage(msg)
//                    binding.resultCoView.text = "time: ${msg.arg2}, sum : ${msg.arg1}"
//                }
//            }

            // thread 작업, 비동기 작업. 코루틴에 비해서 성능이 조금 무겁다.
//            thread {
//                // 무거운 작업(오래 걸리는 작업 넣기.)
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for(i in 1..9_000_000_000){
//                        sum += i
//                    }
//                }
//                //  오래 걸리는 작업의 결과값을 메세지를 통해서, 핸들러에게 전달하는 로직.
//                val message = Message()
//                message.arg1 = sum.toInt()
//                message.arg2 = time .toInt()
//                handler.sendMessage(message)
//
//                Log.d("sjw","time : $time")
//            }

            // 방법2, 코루틴 이용해서, 비동기 처리 해보기.
            // 무거운 작업의 결괏값을 전달하는 도구.
            val channel = Channel<Pair<Int,Int>>()
            // 무건운 작업을 수행하는 로직.
            // Dispatchers.Default : 백그라운드 CPU 연산을 많이 할 때 사용하는 케이스
            val backgroundScop = CoroutineScope(Dispatchers.Default + Job())
            backgroundScop.launch {
                // 무거운 작업 추가.
                var sum = 0L
                var time = measureTimeMillis {
                    for(i in 1..9_000_000_000){
                        sum += i
                    }
                }
                Log.d("sjw","time : $time, sum : $sum")
                channel.send(Pair(time.toInt(),sum.toInt()))
            }

            // 화면 출력을 담당하는 MAIN 을 구축.
            // 무거운 작업에서 결괏값을 받아서 출력을 함.
            val mainScope = GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    var resultTime = it.first
                    var resultSum = it.second
                    binding.resultCoView.text = "time : $resultTime, sum : $resultSum"
                }
            }

        }
        // testCoBtn setOnClick

    }
    // onCreate
}