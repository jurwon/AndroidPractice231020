package com.example.myapp_test_7_8_9_10_11_12.ch18_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.adapter.MyAdapterRetrofit
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.UserListModel
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit.MyApplication
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityHttpTestReqResBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HttpTestReqResActivity : AppCompatActivity() {

    lateinit var binding:ActivityHttpTestReqResBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpTestReqResBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //reqres.in : 외국에서 무료로 REST 테스트 서버 제공해주는 곳
        //데이터를 받아서, 리사이클러 뷰에 출력해보기
        //리사이클러뷰 준비물 1)어댑터 2)뷰홀더 3)목록 요소의 뷰 4)데이터
        
        //http 통신
        //1)MyApplication 설정 파일 2)인터페이스 3) 모델 또는 모델이 담겨진 리스트
        
        //준비작업1 ) 모델 준비하기
        //2) 모델을 요소로 하는 리스트로 준비하기.

        //준비작업2 ) networkInterface 정의하기
        
        //준비작업3 ) myapplication,baseurl등록, 인터페이스 연결
        // 등록해서 앱 실행시 선언 및 초기화 자동으로 되고 바로 사용 가능

        //4) 일단 retrofit통신 이용해서 데이터가 전달 되는지 확인
        
        //데이터 가져오기 1
        //applicationContext안에 등록한 설정 담겨있음
        val networkService = (applicationContext as MyApplication).networkService

        //호출하는 함수 콜 만들기
        val userListCall = networkService.doGetUserList("2")

        //실제 통신이 시작되는 부분, 이 함수를 통해 데이터를 받아옴
        userListCall.enqueue(object:Callback<UserListModel>{
            //익명 클래스가 callback, 레트로핏2에서 제공하는 인터페이스를 구현했고,
            //반드시 재정의해야하는 메서드 구현 필요
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
                //데이터 수신 성공
                val userList = response.body()
                Log.d("sjw","userList 의 값 : ${userList?.data}")

                //리사이클러 뷰 어댑터에 연결
                val layoutManager = LinearLayoutManager(this@HttpTestReqResActivity)
                binding.retrofitRecyclerView.layoutManager =layoutManager
                binding.retrofitRecyclerView.adapter = MyAdapterRetrofit(this@HttpTestReqResActivity,userList?.data)

            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                //데이터 수신 실패
                call.cancel()
            }
        })

        //5) 리사이클러뷰에 넣음
    }
}