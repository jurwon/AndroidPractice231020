package com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.tabTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview.adapter.ViewPageAdapterTest
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTabTest2Binding
import com.google.android.material.tabs.TabLayoutMediator

class TabTest2Activity : AppCompatActivity() {
    lateinit var binding : ActivityTabTest2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 탭 레이아웃과 뷰페이저2 연동하기.
        // 탭 뷰 부분 선택
        // 예) 요소의 예 : Tab1 , Tab2 , Tab3. 등 이름이 기본값.
        val tabLayout = binding.tabs2
        // 뷰페이져2 뷰 부분 선택
        val viewPager = binding.viewpagerTab

        // 뷰페이져2를 연동시 사용할 프래그먼트 어댑터 필요함.
        // 기존꺼 재사용. ViewPageAdapterTest
        // 어댑터 : 뷰에 필요한 데이터를 바인딩 (연결 시켜주기)
        // 어댑터의 더미 데이터 : 프래그먼트가 들어오고 있음. 각각의 프래그먼트가 , 마치 리스트의 요소로 , 인덱스=포지션
        viewPager.adapter = ViewPageAdapterTest(this)

        // 뷰페이져2, 탭 레이아웃 연결 시켜주는 기능.
        TabLayoutMediator(tabLayout, viewPager) {
                tab, position -> tab.text = "Tab${position+1}"
        }.attach()

    }
}