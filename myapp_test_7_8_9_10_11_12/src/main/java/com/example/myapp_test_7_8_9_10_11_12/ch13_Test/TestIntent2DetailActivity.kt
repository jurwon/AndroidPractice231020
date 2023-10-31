package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestIntent2DetailBinding
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestIntentBinding

class TestIntent2DetailActivity : AppCompatActivity() {

    //2번 화면
    lateinit var binding: ActivityTestIntent2DetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestIntent2DetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1번에서 넘긴 DATA 가져오기 -> 임시저장
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val age = intent.getStringExtra("age")
        
        //결과 뷰에 data연결
        binding.nameResultView.text = "이름 : ${name}"
        binding.emailResultView.text = "이름 : ${email}"
        binding.ageResultView.text = "이름 : ${age}"

    }
}