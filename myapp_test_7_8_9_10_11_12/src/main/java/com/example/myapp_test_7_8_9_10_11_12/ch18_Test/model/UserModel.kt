package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model

import com.google.gson.annotations.SerializedName

//가져오는 데이터 타입 조사
//샘플 데이터
/*"id": 7,
"email": "michael.lawson@reqres.in",
"first_name": "Michael",
"last_name": "Lawson",
"avatar": "https://reqres.in/img/faces/7-image.jpg"*/
data class UserModel(

    //FirstName 저장하면 first_name으로 자동 변환됨

    val id : Int,
    val email : String,
    @SerializedName("first_name")
    val first_name : String,
    @SerializedName("last_name")
    val last_name : String,

    //프로필 이미지가 저장된 위치의 URL주소
    val avatar : String

)

