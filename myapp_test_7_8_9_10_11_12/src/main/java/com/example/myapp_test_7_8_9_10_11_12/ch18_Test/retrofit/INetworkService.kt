package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit

import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.UserListModel
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

//통신 라이브러리 :retrofit2 이용해서
// 인터페이스, 추상 메서드 만들어서 -> 레트로핏한테 전달
// 여기에 정의된 함수로 통신함(crud)
interface INetworkService {
    @GET("api/users")
    //baseurl : http://reqres.in
    //get : 가져올 주소가 정확히
    //https://reqres.in/api/users?page=2
    // 호출할때 -> doGetUserList("3")
    //반환 타입은 call, 담겨진 데이터는 리스트의 요소가(UserModel)
    fun doGetUserList(@Query("page") page: String): Call<UserListModel>

    // 프로필 이미지를 받기 위한 , 추상 함수.
    //@Url
    //기본 baseUrl 이 있지만, 다른 url 를 호출 할 때 사용.
    @GET
    fun getAvatarImage(@Url url:String): Call<ResponseBody>


}