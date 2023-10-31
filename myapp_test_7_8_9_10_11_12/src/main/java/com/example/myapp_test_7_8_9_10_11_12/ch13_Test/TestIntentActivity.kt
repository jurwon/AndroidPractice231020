package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestIntentBinding

class TestIntentActivity : AppCompatActivity() {

    //1번화면
    lateinit var binding : ActivityTestIntentBinding

    //로그인 변수
    lateinit var email : String
    lateinit var password : String
    lateinit var age : String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //인텐트에 기본 데이터 추가 및 가져오기 test
        binding.loginBtn.setOnClickListener{
            //데이터 추가 + 화면 이동
            val intent:Intent = Intent(this@TestIntentActivity,TestIntent2DetailActivity::class.java)
            
            //뷰 입력값 -> 변수에 저장
            email = binding.emailEdit.text.toString()
            password = binding.passwordEdit.text.toString()
            age = binding.ageEdit.text.toString()

            //데이터 확인
            Log.d("sjw","데이터 확인 이메일 : ${email}, 비밀번호 : ${password}, 나이 : ${age}")

            //데이터 추가하기(json형태)
            intent.putExtra("email",email)
            intent.putExtra("password",password)
            intent.putExtra("age",age)

            //화면 이동
            startActivity(intent)

        }

        //후처리 (1번 화면 -> 2번화면 -> 특정 액션 ->다시 1번 화면)
        //ex) 갤러리에서 이미지 가져오기
        binding.testBtn.setOnClickListener{

            //화면 이동
            val intent:Intent = Intent(this@TestIntentActivity,TestIntent2DetailActivity::class.java)

            //후처리 하기
            // 10 : 요청코드(=식별ID)
            startActivityForResult(intent,10)
        }

        //후처리 함수 정의
        //ActivityResultLauncher -> StartActivityForResult : 시스템에서 정의해둔 함수
        //2번 화면에서 데이터를 가져왔을 때 처리하는 함수
        val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            val result2 = it.data?.getStringExtra("result")
            binding.resultData1.text = "결과 : ${result2}"
        }

        //후처리 데이터 보내기2 (ActivityResultLauncher)
        binding.test3Btn.setOnClickListener{
            //데이터 추가 + 화면 이동
            val intent:Intent = Intent(this@TestIntentActivity,TestIntent2DetailActivity::class.java)

        }

    }

    //onCreate밖에서 재정의
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //자동완성된 코드에 로직만 추가
        if(requestCode == 10 && resultCode == Activity.RESULT_OK){
            val result = data?.getStringExtra("resultData")
            Log.d("sjw","후처리 데이터 확인 : ${result}")

        }
    }
}