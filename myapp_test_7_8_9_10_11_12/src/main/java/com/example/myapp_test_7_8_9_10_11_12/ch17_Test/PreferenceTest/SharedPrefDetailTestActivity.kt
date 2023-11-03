package com.example.myapp_test_7_8_9_10_11_12.ch17_Test.PreferenceTest

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
    }
}