package com.example.myapp_test_7_8_9_10_11_12.ch18_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityNewsApiTestBinding

class NewsApiTestActivity : AppCompatActivity() {

    lateinit var binding:ActivityNewsApiTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsApiTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //레스트 서버에서(뉴스 api)데이터 받아오기 ->  리사이클러 뷰에 붙이기

    }
}