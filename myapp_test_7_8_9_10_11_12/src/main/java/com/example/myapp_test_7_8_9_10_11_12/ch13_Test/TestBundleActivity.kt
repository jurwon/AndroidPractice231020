package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestBundleBinding

class TestBundleActivity : AppCompatActivity() {

    lateinit var binding : ActivityTestBundleBinding
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("sjw","onCreate()")

        if(savedInstanceState != null){
            count = savedInstanceState.getInt("data3")
        }

        binding = ActivityTestBundleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //화면 회전시 데이터 손실되는데,
        //임시 저장소 번들에 저장하고, 값 가져오기
        //앱 사용중에는 기본 값들 setter/getter 설정 용이, 앱 종료시 휘발됨(단점)
        // -> 내부 저장소 사용하여 해결 1) 파일 2) DB 3)외부DB사용 예정
        binding.countBtn.setOnClickListener {
            count++
            binding.resultText.text = "$count"
        }

    }//onCreate 마지막 블럭

    //번들 객체 이용
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //setter, onDestroy() 전
        Log.d("sjw","onSaveInstanceState()")
        outState.putString("data1","Hello")
        outState.putInt("data2",20)
        outState.putInt("data3",count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //getter, onResume() 전
        Log.d("sjw","onRestoreInstanceState()")
        val data1 = savedInstanceState.getString("data1")
        val data2 = savedInstanceState.getInt("data2")
        val data3 = savedInstanceState.getInt("data3")
        binding.resultText.text = "$data1 - $data2"
        binding.resultText2.text ="count 임시저장 값 : $data3"
    }

    override fun onStart() {
        super.onStart()
        Log.d("sjw","onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("sjw","onResume()")
    }


    override fun onPause() {
        super.onPause()
        Log.d("sjw","onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("sjw","onStop()")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("sjw","onDestroy()")
    }
}