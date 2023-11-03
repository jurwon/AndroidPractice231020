package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit

import android.app.Application
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//자주 사용이 될 네트워크 인터페이스를 미리 시스템에 등록
//메니페스트에 등록해서, 앱이 실행되면 해당 MyApplication의 기능이
//메모리 등록이 되어서 사용하기 편함

class MyApplication :Application() {

    //1)통신에 필요한 인스턴스 선언 & 초기화
    val networkService : INetworkService

    //통신할 서버의 URL주소 등록
    val retrofit : Retrofit

        get() = Retrofit.Builder()
            .baseUrl("httmls://reques.in/")//상대방 서버
            .addConverterFactory(GsonConverterFactory.create())//형변환
            .build()
    init{
        networkService = retrofit.create(INetworkService::class.java)
    }



}