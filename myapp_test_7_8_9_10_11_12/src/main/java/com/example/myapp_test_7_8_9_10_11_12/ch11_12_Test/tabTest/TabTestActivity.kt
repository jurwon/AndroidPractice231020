package com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.tabTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview.fragment.Test1Fragment
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTabTestBinding
import com.google.android.material.tabs.TabLayout

class TabTestActivity : AppCompatActivity() {

    lateinit var binding:ActivityTabTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //기본 탭 레이아웃 붙이기 작업.
        // 방법 2가지, 1) 코드로 2) xml 뷰로 작업 하는 방법.
        // 2) xml로 뷰 작업을 하면 쉽다.
        // 준비물:
        // 1 탭 레이아웃이 그려지는 , 빈 도화지(액티비티),
        // 2 요소 , 탭을 눌렀을 때 변경이 되는 요소가. (프래그먼트 이용함.)


        // 방법1) 코드상에서도 가능.
        val tabLayout = binding.tabs

        // 재료 중에서, 프래그먼트를 이용 -> 미리 만들어 둔 프래그먼트 재사용.
        // 탭의 첫화면을 , Test1Fragment 화면으로 구성하겠다라는 의미.
        supportFragmentManager.beginTransaction().add(R.id.tabContent, Test1Fragment()).commit()


        // 탭 이벤트 리스너 추가하기.
        tabLayout.addOnTabSelectedListener( object: TabLayout.OnTabSelectedListener {
            //익명 클래스 정의하고, 해당 이벤트 리스너 구현하면, 의무적으로,
            // 재정의 해야하는 함수3개 있음.
            override fun onTabSelected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

        })
    }
}