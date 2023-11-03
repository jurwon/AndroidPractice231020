package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit

import android.app.Application
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//자주 사용이 될 네트워크 인터페이스를 미리 시스템에 등록
//메니페스트에 등록해서, 앱이 실행되면 해당 MyApplication의 기능이
//메모리 등록이 되어서 사용하기 편함

class MyApplication2 :Application() {

    companion object {
        val QUERY = "travel"
        val QUERY2 = "Apple"
        val from ="2023-05-22"
        val sortBy ="popularity"
        val API_KEY = "87af28a1123a4fcc9c869c0b81bd243c"
        val BASE_URL = "https://newsapi.org"
        val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"


        //add....................................
        var networkService: INetworkService2
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            networkService = retrofit.create(INetworkService2::class.java)
        }
    }

}