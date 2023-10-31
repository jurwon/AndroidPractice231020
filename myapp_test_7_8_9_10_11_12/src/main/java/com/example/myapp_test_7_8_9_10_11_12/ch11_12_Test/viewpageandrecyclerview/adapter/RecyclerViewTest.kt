package com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp_test_7_8_9_10_11_12.databinding.ItemRecycler2Binding

// 뷰 홀더는 , 목록의 요소의 뷰를 설정 해주는 역할.
class MyViewHolderTest (val binding: ItemRecycler2Binding) : RecyclerView.ViewHolder(binding.root)

        // 리사이클러뷰가 동작시 해당 뷰에, 데이터를 연결.
class RecyclerViewTest (val datas:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolderTest(ItemRecycler2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolderTest).binding
        binding.testText.text = datas[position]

    }

}