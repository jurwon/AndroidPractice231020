package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit

import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.UserListModel
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.UserModel
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.newsModel.ItemListModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

//통신 라이브러리 :retrofit2 이용해서
// 인터페이스, 추상 메서드 만들어서 -> 레트로핏한테 전달
// 여기에 정의된 함수로 통신함(crud)
interface INetworkService2 {
    //https://newsapi.org/v2/everything?q=tesla&from=2023-10-03&sortBy=publishedAt&apiKey=87af28a1123a4fcc9c869c0b81bd243c
    //기본 주소 : https://newsapi.org/
    //추가 주소 : /v2/everything
    @GET("/v2/everything")
    fun getList(@Query("q") q: String?,
                @Query("from") from: String?,
                @Query("sortBy") sortBy: String?,
                @Query("apiKey") apiKey: String?): Call<ItemListModel>

    @GET
    fun getImage(@Url url:String): Call<ResponseBody>


}