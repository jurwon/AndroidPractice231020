package com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewPager

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.recycler.MyAdapter2
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewPager.adapter.MyFragmentPagerAdapter
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestViewPagerBinding
import com.example.myapp_test_7_8_9_10_11_12.databinding.ItemPagerBinding

class TestViewPagerActivity : AppCompatActivity() {
    lateinit var binding : ActivityTestViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 더미 데이터 재사용, 일단, 텍스트 뷰
        val testDataSet = ArrayList<String>()
        for (i in 0..2) {
            testDataSet.add("TEST DATA$i")
        }
        // 외부 기능이니 디펜던시 build.gradle 에 복사후 , sync now 해서 ,
        //뷰페이저2 출력 방식 2가지, 1) 어댑터 사용방법 2) 프래그먼트 이용

// 테스트 진행중 방법1) 어댑터 ,
// 문제점은, 어댑터 구조는 똑같은데, 데이터 똑같고, 아이템의 요소의 뷰의 형식이 다름.
//        binding.viewPager1.adapter = MyPagerAdapter(testDataSet)
        binding.viewPager1.adapter = MyAdapter2(testDataSet)

        // 방법2 , 프래그먼트 형식으로 뷰페이저 구성.
        // 준비물, 1) 프래그먼트 형식의 어댑터 2) 요소로 사용이될 프래그먼트 3개정도 만들기.
        binding.viewPager2.adapter = MyFragmentPagerAdapter(this)

    }

    class MyPagerViewHolder(val binding: ItemPagerBinding): RecyclerView.ViewHolder(binding.root)

//    class MyPagerViewHolder(val binding: ItemPager2Binding): RecyclerView.ViewHolder(binding.root)
    class MyPagerAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemCount(): Int {
            return datas.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            MyPagerViewHolder(
                ItemPagerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding = (holder as MyPagerViewHolder).binding
            //뷰에 데이터 출력
            binding.itemPagerTextView.text = datas[position]
            when (position % 3) {
                0 -> binding.itemPagerTextView.setBackgroundColor(Color.RED)
                1 -> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
                2 -> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
            }
        }
    }
}