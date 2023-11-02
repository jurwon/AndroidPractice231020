package com.example.myapp_test_7_8_9_10_11_12.ch16_Test.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp_test_7_8_9_10_11_12.databinding.ItemRecyclerBinding
import com.example.myapp_test_7_8_9_10_11_12.databinding.MultiimagepickItemBinding

//뷰를 모아둔 박스 -> 목록 요소의 뷰,
class MyViewHolder (val binding: MultiimagepickItemBinding) : RecyclerView.ViewHolder(binding.root)

//추가, 어댑터 파라미터에 context추가하면 해당 액티비티나 프래그먼트에서 뷰 작업하기 좋음
class MultiImageAdapter (val datas: ArrayList<Uri>,val context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            MultiimagepickItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))


    override fun getItemCount(): Int {
        Log.d("sjw","getItemCount : ${datas.size}")
        return datas.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("sjw", "onBindViewHolder : $position")
        val binding = (holder as com.example.myapp_test_7_8_9_10_11_12.ch16_Test.adapter.MyViewHolder).binding
        //glid 이용해서 이미지 처리하기
        //데이터에서 각 요소 가져오기
        Glide.with(context).load(datas[position])
            //크기조절
            .override(200,200)
            //결과 이미지 넣기
            .into(binding.multiImageItem)


        //이벤트 처리할 때 필요한 부분
        binding.itemRoot.setOnClickListener {
            Log.d("sjw", "item clicked : $position")
            if(position == 0){
                Log.d("sjw", "0번 요소 item clicked : $position")
                Toast.makeText(context,"0번 요소 item clicked : $position",Toast.LENGTH_SHORT).show()
            }else{
                Log.d("sjw", "0번 요소 item clicked : $position")
            }
        }
    }

}
