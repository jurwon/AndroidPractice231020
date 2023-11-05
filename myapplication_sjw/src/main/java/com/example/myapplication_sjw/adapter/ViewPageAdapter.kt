package com.example.myapplication_sjw.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication_sjw.fragment.Tab1Fragment
import com.example.myapplication_sjw.fragment.Tab2Fragment
import com.example.myapplication_sjw.fragment.Tab3Fragment

class ViewPageAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    //각 프래그먼트들을 담을 리스트
    var tabFragment : List<Fragment>

    init {
        tabFragment = listOf(Tab1Fragment(), Tab2Fragment(), Tab3Fragment())
    }


    override fun getItemCount(): Int {
        return tabFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        val returnFragment : Fragment = tabFragment[position]
        return returnFragment
    }


}