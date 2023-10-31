package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestIntentBinding

class TestIntentActivity : AppCompatActivity() {

    //1번화면
    lateinit var binding : ActivityTestIntentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //인텐트에 기본 데이터 추가 및 가져오기 test
        binding.testBtn.setOnClickListener{
            //데이터 추가 + 화면 이동
            val intent:Intent = Intent(this@TestIntentActivity,TestIntent2DetailActivity::class.java)

            //데이터 추가하기(json형태)
            intent.putExtra("name","sjw")
            intent.putExtra("email","134@naver.com")
            intent.putExtra("age","24")

            //화면 이동
            startActivity(intent)

        }

    }
}