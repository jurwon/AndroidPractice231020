package com.example.myapp_test_7_8_9_10_11_12.ch17_Test.PreferenceTest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivitySharedPrefTestBinding

//1번 화면
//val test 공유 프리퍼런스 파일에 데이터 담기
//EditText, 라디오 뷰, 각 뷰에서 데이터 가져오기 -> test에 저장
class SharedPrefTestActivity : AppCompatActivity() {

    lateinit var binding:ActivitySharedPrefTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPrefTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //회원가입
        binding.joinBtnSP.setOnClickListener {
            val id = binding.editTextID.text.toString()
            val password = binding.editTextPassword.text.toString()
            //라디오 잠시 대기

            //공유 프리퍼런스에 값 넣기
            // 공유프리퍼런스 값 넣기. 저장.
            val pref = getSharedPreferences("memberInfo", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("id",id)
            editor.putString("password",password)
            editor.commit()
        }
    }
}