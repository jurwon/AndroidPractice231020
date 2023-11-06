package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.UserModel
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit.MyApplication
import com.example.myapp_test_7_8_9_10_11_12.databinding.ItemRetrofitBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyViewHolderRetrofit (val binding: ItemRetrofitBinding) : RecyclerView.ViewHolder(binding.root)

//매개변수 구성 1) context, 2)데이터
class MyAdapterRetrofit(val context: Context, val datas: List<UserModel>?)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //목록 요소를 해당 어댑터에 적용한다
        return MyViewHolderRetrofit(
            ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        //?: 앨비스 연산자 ( 해당 값이 있으면 그 값을 사용하고, 없으면 0)
        return datas?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolderRetrofit).binding

        //datas에 담긴 모델을 하나씩 꺼내서, 뷰에 데이터 붙이는 작업
        //리스트의 각 요소마다 하나씩 꺼내어, 임의의 user변수에 담기
        val user = datas?.get(position)

        binding.retrofitEmailView.text = user?.email
        binding.retrofitFirstNameView.text = user?.first_name
        binding.retrofitLastNameView.text = user?.last_name

        //이미지 가져오기
        val avatarImageCall = user?.let {
            (context.applicationContext as MyApplication)
                .networkService.getAvatarImage(it.avatar)
        }

        // 실제로 이미지 가져오는 통신의 시작.
        avatarImageCall?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                // 이미지를 가져오기 성공시

                if(response.isSuccessful){
                    if(response.body() != null){
                        val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())

                        //방법1
                        binding.retrofitProfileImg.setImageBitmap(bitmap)

                        //방법2
                       /* Glide.with(context).load(bitmap)
                            //크기조절
                            .override(100,100)
                            //결과 이미지 넣기
                            .into(binding.retrofitProfileImg)*/
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // 이미지를 가져오기 실패시
                call.cancel()
            }

        })
        //네트워크 함수 통해서 처리하는 부분, 방법 1)


        //glide로 이미지 직접 가져와서 처리
        //방법 2)
        Glide.with(context).load(user?.avatar)
            //크기조절
            .override(100,100)
            //결과 이미지 넣기
            .into(binding.retrofitProfileImg)
            

    }


}