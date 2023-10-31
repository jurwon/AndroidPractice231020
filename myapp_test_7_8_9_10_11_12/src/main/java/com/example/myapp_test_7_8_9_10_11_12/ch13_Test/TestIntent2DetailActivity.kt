package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val age = intent.getStringExtra("age")

        //데이터 확인
        Log.d("sjw","데이터 확인 이메일 : ${email}, 비밀번호 : ${password}, 나이 : ${age}")

        //결과 뷰에 data연결
        binding.emailResultView.text = "이메일 : ${email}"
        binding.passwordResultView.text = "비밀번호 : ${password}"
        binding.ageResultView.text = "나이 : ${age}"

        //후처리 데이터 보내기
        binding.test2Btn.setOnClickListener{
            //메시지 담기
            intent.putExtra("resultData","2번 화면에서 데이터 가져온 값")
            
            //결과 코드 담기
            setResult(RESULT_OK,intent)
            
            //현재 앱 종료 (root activity가 아니면 finish()호출시 모두 종료)
            finish()

        }

    }
}