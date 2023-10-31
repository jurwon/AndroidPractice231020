package com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewPager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewPager.fragment.SampleFrag1
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewPager.fragment.SampleFrag2
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewPager.fragment.SampleFrag3

// 뷰페이저의 데이터를 연결 시켜주는, 프래그먼트 형식의 어댑터
class MyFragmentPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    var sampleFragment : List<Fragment>
    init {
        //SampleFrag1 ~ 3-> 만들 예정.
        sampleFragment = listOf(SampleFrag1(), SampleFrag2(), SampleFrag3())
    }
    override fun getItemCount(): Int = sampleFragment.size

    override fun createFragment(position: Int): Fragment {
        val returnFrament : Fragment = sampleFragment[position]
        return  returnFrament
    }

}