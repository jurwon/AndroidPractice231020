package com.example.myapp_test_7_8_9_10_11_12.ch18_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.adapter.MyAdapterRetrofit
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.adapter.MyAdapterRetrofit2
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.newsModel.ItemListModel
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit.MyApplication2
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityNewsApiTestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsApiTestActivity : AppCompatActivity() {

    lateinit var binding:ActivityNewsApiTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsApiTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //레스트 서버에서(뉴스 api)데이터 받아오기 ->  리사이클러 뷰에 붙이기

        //데이터 가져오기 1
        //applicationContext안에 등록한 설정 담겨있음
        val networkService = (applicationContext as MyApplication2).networkService

        //https://newsapi.org/v2/everything?q=tesla&from=2023-10-03&sortBy=publishedAt&apiKey=87af28a1123a4fcc9c869c0b81bd243c
        //호출하는 함수 콜 만들기
        val q = "tesla"
        val from ="2023-10-03"
        val sortBy ="publishedAt"
        val API_KEY = "87af28a1123a4fcc9c869c0b81bd243c"
        val itemListCall = networkService.getList(q,from,sortBy,API_KEY)

        //실제 통신이 시작되는 부분, 이 함수를 통해 데이터를 받아옴
        itemListCall.enqueue(object: Callback<ItemListModel> {
            //익명 클래스가 callback, 레트로핏2에서 제공하는 인터페이스를 구현했고,
            //반드시 재정의해야하는 메서드 구현 필요
            override fun onResponse(call: Call<ItemListModel>, response: Response<ItemListModel>) {
                //데이터 수신 성공
                val itemList = response.body()
                Log.d("sjw","itemList 의 값 : ${itemList?.articles}")

                //리사이클러 뷰 어댑터에 연결
                val layoutManager = LinearLayoutManager(this@NewsApiTestActivity)
                binding.retrofitRecyclerView2.layoutManager =layoutManager
                binding.retrofitRecyclerView2.adapter = MyAdapterRetrofit2(this@NewsApiTestActivity,itemList?.articles)

            }

            override fun onFailure(call: Call<ItemListModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
}