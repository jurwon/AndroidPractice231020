package com.example.myapp_test_7_8_9_10_11_12.ch17_Test.PreferenceTest

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivitySharedPrefDetailTestBinding
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivitySharedPrefTestBinding

//2번 화면
//공유 프리퍼런스 파일의 데이터 가져오기
class SharedPrefDetailTestActivity : AppCompatActivity() {

    lateinit var binding: ActivitySharedPrefDetailTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPrefDetailTestBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_shared_pref_detail_test)
        setContentView(binding.root)

        //조회 버튼 누르면 공유된 프리퍼런스 파일에서 값 가져오기
        val pref = getSharedPreferences("memberInfo", MODE_PRIVATE);
        val id = pref.getString("id", "Default ID");
        val password = pref.getString("password", "Default PW");

        binding.resultIdSP.text = id
        binding.resultPasswordSP.text = password

        //삭제 테스트1 (부분삭제)
        //내용물만 삭제되고 파일은 삭제 안됨
        binding.deleteSharedPreferBtnTest.setOnClickListener {
            val pref = getSharedPreferences("memberInfo", MODE_PRIVATE);
            val editor = pref.edit();
            editor.remove("id");
            editor.remove("password");
            editor.commit();
        }

        //삭제 테스트2 (파일 전체 삭제) deleteFileSharedPreferBtnTest
        binding.deleteFileSharedPreferBtnTest.setOnClickListener {
            val pref = getSharedPreferences("memberInfo", MODE_PRIVATE);
            val editor = pref.edit();
            editor.clear();
            editor.commit();
        }

    }
}