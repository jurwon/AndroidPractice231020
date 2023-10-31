package com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview.fragment.Test1Fragment
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview.fragment.Test2Fragment
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview.fragment.Test3Fragment

class ViewPageAdapterTest(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    //각 프래그먼트들을 담을 리스트
    var testFragment : List<Fragment>

    init {
        testFragment = listOf(Test1Fragment(), Test2Fragment(), Test3Fragment())
    }

    override fun getItemCount(): Int {
        return testFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        val returnFragment : Fragment = testFragment[position]
        return returnFragment
    }

}