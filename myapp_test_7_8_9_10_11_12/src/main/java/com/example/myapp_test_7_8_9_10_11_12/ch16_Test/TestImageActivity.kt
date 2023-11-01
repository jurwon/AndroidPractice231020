package com.example.myapp_test_7_8_9_10_11_12.ch16_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestImageBinding

class TestImageActivity : AppCompatActivity() {
    // 갤러리, 카메라 앱 연동해서 데이터 가져오기.
    lateinit var binding : ActivityTestImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //작업 구성 2가지.
        // 첫번째, 갤러리 앱을 호출 하는 작업
        // 두번째, 갤러리 앱에 데이터를 가져온 내용을 처리하는 작업.

        // 버튼클릭시 : 갤러리 앱 호출,
        // 갤러리에서 선택 된 사진을 출력하는 뷰하나 생성.

    }
}