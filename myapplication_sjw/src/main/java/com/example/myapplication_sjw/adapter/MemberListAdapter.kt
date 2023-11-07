package com.example.myapplication_sjw.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication_sjw.databinding.MemberpickItemBinding
import com.example.myapplication_sjw.model.Member

class MyViewHolder (val binding:MemberpickItemBinding):RecyclerView.ViewHolder(binding.root)

class MemberListAdapter(val datas: ArrayList<Member>, val context: Context)  : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            MemberpickItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int {
        Log.d("sjw","getItemCount : ${datas.size}")
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("sjw", "onBindViewHolder : $position")
        val binding = (holder as MyViewHolder).binding
        //glid 이용해서 이미지 처리하기
        //데이터에서 각 요소 가져오기
        Glide.with(context).load(datas[position].profileImage)
            //크기조절
            .override(200,200)
            //결과 이미지 넣기
            .into(binding.profileImg)
        binding.memberIdView.text = datas[position].id
        binding.memberpasswordView.text= datas[position].password
        binding.memberNameView.text= datas[position].name
        binding.memberGenderView.text= datas[position].gender



        //이벤트 처리할 때 필요한 부분
        binding.itemRoot.setOnClickListener {
            Log.d("sjw", "item clicked : $position")
            if(position == 0){
                Log.d("sjw", "0번 요소 item clicked : $position")
                Toast.makeText(context,"0번 요소 item clicked : $position", Toast.LENGTH_SHORT).show()
            }else{
                Log.d("sjw", "0번 요소 item clicked : $position")
                Toast.makeText(context,"0번 요소 외 item clicked : $position", Toast.LENGTH_SHORT).show()
            }
        }
    }

}