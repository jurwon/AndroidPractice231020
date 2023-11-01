package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestCoroutineBinding
import kotlin.system.measureTimeMillis

class TestCoroutineActivity : AppCompatActivity() {
    lateinit var binding : ActivityTestCoroutineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //오래걸리는 작업 추가.
        var sum = 0L
        var time = measureTimeMillis {
            for(i in 1..2_000_000_000){
                sum += i
            }
        }
        Log.d("lsy","time : $time")
        binding.resultCoView.text = "time: $time, sum : $sum"



    }
}