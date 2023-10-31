package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
        Log.d("sjw","onCreate()")

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


        //후처리 데이터 보내기 방법 2 =====================================================================

        //후처리 함수 정의
        //ActivityResultLauncher -> StartActivityForResult : 시스템에서 정의해둔 함수
        //2번 화면에서 데이터를 가져왔을 때 처리하는 함수
        val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            val result2 = it.data?.getStringExtra("result")
            Log.d("sjw","result : ${result2}")
            binding.resultData1.text = "결과 : ${result2}"
        }



        //후처리 데이터 보내기2 (ActivityResultLauncher)
        binding.test3Btn.setOnClickListener{
            //데이터 추가 + 화면 이동
            val intent:Intent = Intent(this@TestIntentActivity,TestIntent2DetailActivity::class.java)

            requestLauncher.launch(intent)

        }


        //인텐트 필터를 이용해서 외부앱 접근====================================================================================
        //intent : 시스템에 전달하는 메시지, intent-filter : 뭐하는지 나타내는 라벨
        //시스템이 해당 필터의 요소 보고 해당 외부앱 연결해줌
        //ex1) 좌표값이다. 위도 경도 -> 지도 앱(외부앱)
        //후처리 데이터 보내기2 (ActivityResultLauncher)
        binding.test5Btn.setOnClickListener{
            //지도 맵 열기 테스트
            // ACTION_VIEW : 뒤에 오는 내용에 따라 맞는 view 연결해줌
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7749,127.4194"))
            // 현재, 지도 관련 앱이 다양하게 없어서, 아마도 기본 앱: 구글 맵이 나옴.
            // 만약, 지도 관련 앱이 여러 개 있다면, 특정 앱을 선택이 가능함.
            // 사용하는 앱의 패키지명을 정확히 입력함.
            intent.setPackage("com.google.android.apps.maps")

            //화면 이동(단순 이동)
            startActivity(intent)

        }

        binding.test6Btn.setOnClickListener{
            //지도 맵 열기 테스트
            // ACTION_VIEW : 뒤에 오는 내용에 따라 맞는 view 연결해줌
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.google.com")
            intent.setPackage("com.android.chrome") // 크롬 브라우저를 사용하도록 지정

            //화면 이동(단순 이동)
            startActivity(intent)

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


    override fun onStart() {
        super.onStart()
        Log.d("sjw","onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("sjw","onResume()")
    }


    override fun onPause() {
        super.onPause()
        Log.d("sjw","onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("sjw","onStop()")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("sjw","onDestroy()")
    }

    }