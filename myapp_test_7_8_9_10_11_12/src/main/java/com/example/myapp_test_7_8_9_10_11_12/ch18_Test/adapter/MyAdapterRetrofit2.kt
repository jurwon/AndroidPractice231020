package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.newsModel.ItemModel
import com.example.myapp_test_7_8_9_10_11_12.databinding.ItemRetrofit2Binding


class MyViewHolderRetrofit2 (val binding: ItemRetrofit2Binding) : RecyclerView.ViewHolder(binding.root)

//매개변수 구성 1) context, 2)데이터
class MyAdapterRetrofit2(val context: Context, val datas:MutableList<ItemModel>?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //목록 요소를 해당 어댑터에 적용한다
        return MyViewHolderRetrofit2(
            ItemRetrofit2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        //?: 앨비스 연산자 ( 해당 값이 있으면 그 값을 사용하고, 없으면 0)
        return datas?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolderRetrofit2).binding

        //datas에 담긴 모델을 하나씩 꺼내서, 뷰에 데이터 붙이는 작업
        //리스트의 각 요소마다 하나씩 꺼내어, 임의의 user변수에 담기
        val item = datas?.get(position)

        binding.retrofitTitleView.text = item?.title
        binding.retrofitContentView.text = item?.content

        Glide.with(context).load(item?.urlToImage)
            //크기조절
            .override(700,300)
            //결과 이미지 넣기
            .into(binding.retrofitProfileImg2)

    }
}
