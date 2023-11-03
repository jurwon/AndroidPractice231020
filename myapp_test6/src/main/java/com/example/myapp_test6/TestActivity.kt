package com.example.myapp_test6

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    // 뷰바인딩 순서1
    // 이름은 고정 : 예를 들어 , 레이아웃 이름 :activity_test
    // 이름 이 자동으로 생성 : ActivityTestBinding . 캐멀케이스 표기법에 뒤에 + binding 추가.
    lateinit var activityTestBinding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // R.layout 버전으로 출력을 하는 형식.
//        setContentView(R.layout.activity_test)
        // 뷰바인딩 순서2
        activityTestBinding = ActivityTestBinding.inflate(layoutInflater)
        // // 뷰바인딩 순서3 , 전체 레이아웃, 바인딩으로 출력을 해야함.
        setContentView(activityTestBinding.root)

        //showBtn -> 클릭시 , 프로필 이미지 profileImg , 보이게
        // 특정 뷰를 선택하는 방법 2가지.
        // 1)-1 findViewById<>() 를 이용하는 방법
        val showBtn:Button = findViewById(R.id.showBtn)
        // 2)-1 viewBinding 를 이용하는 방법
        val showBtn2 = activityTestBinding.showBtn
        showBtn2.setOnClickListener {
            activityTestBinding.profileImg.visibility = View.VISIBLE
        }

        // 선택한 뷰를 클릭 이벤트 핸들러를 추가.
        //1)-2
//        showBtn.setOnClickListener {
//            val profilImg : ImageView = findViewById(R.id.profileImg)
//            profilImg.visibility = View.VISIBLE
//        }

        // hideBtn -> 클릭시 , 프로필 이미지 profileImg , 사라짐.
        //1)-3
        // 특정 뷰를 선택하는 방법 2가지.
        // 1)-1 findViewById<>() 를 이용하는 방법
        val hideBtn:Button = findViewById(R.id.hideBtn)

//        hideBtn.setOnClickListener {
//            val profilImg : ImageView = findViewById(R.id.profileImg)
//            profilImg.visibility = View.INVISIBLE
//        }

        // 2)-1 viewBinding 를 이용하는 방법
        val hideBtn2 = activityTestBinding.hideBtn
        hideBtn2.setOnClickListener {
            activityTestBinding.profileImg.visibility = View.INVISIBLE
        }

//        val profileImgView : ImageView = findViewById(R.id.profileImg)
//        profileImgView.setOnClickListener {
//            // 매개변수 1번: 현재 컨텍스트 : 액티비티, 2번 : 메세지 내용 , 3번: 메세지 출력 시간, 숏:3초, 롱:5초 정도
//            Toast.makeText(this@TestActivity,"이미지 클릭했음.!!",Toast.LENGTH_SHORT).show()
//        }

        activityTestBinding.profileImg.setOnClickListener {
            Toast.makeText(this@TestActivity,"이미지 클릭했음.!!",Toast.LENGTH_SHORT).show()
        }

//        maxWidth, maxHeight 속성을 이용해 뷰를 출력할 최대 크기를 지정해 주면 이 문제를 해결 가능.
//        maxWidth, maxHeight 속성은  android:adjustViewBounds 속성과 함께 사용함.
//        이 속성을  true로 설정하면 이미지의 가로세로 길이와 비례해 뷰의 크기를 맞춤.

        //체크박스 클릭시 이벤트 핸들러 사용하기.
        activityTestBinding.checkBox1.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity,"check1 선택됨",Toast.LENGTH_SHORT).show()
        }

        activityTestBinding.checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity,"check2 선택됨",Toast.LENGTH_SHORT).show()
        }

        // 라디오 버튼 체크 확인.
        activityTestBinding.radio1.setOnCheckedChangeListener { buttonView, isChecked ->
            // // 라디오 버튼 값 가져오는 샘플
            val test = buttonView.text.toString()
            Toast.makeText(this@TestActivity,"남자 클릭됨 : $test",Toast.LENGTH_SHORT).show()
        }

        activityTestBinding.radio2.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity,"여자 클릭됨",Toast.LENGTH_SHORT).show()
        }

    }
}