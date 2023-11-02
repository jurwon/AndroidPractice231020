package com.example.myapp_test_7_8_9_10_11_12.ch8_Test

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTest8Binding

class Test8Activity : AppCompatActivity() {
    lateinit var activityTest8Binding: ActivityTest8Binding
    lateinit var TAG:String
    ////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG ="sjw"
//        setContentView(R.layout.activity_test8)
        // 모든 뷰들의 인스턴스들을 하나의 인스턴스에 모두 담기.
        // 하나의 인스턴스에서 뷰를 꺼내어서 사용하기.
        activityTest8Binding = ActivityTest8Binding.inflate(layoutInflater)
        // 모두 담은 인스턴스의 부모 레이아웃을 출력을 함.
        setContentView(activityTest8Binding.root)

        // test 버튼 클릭 리스너 이벤트 처리 SAM 기법 (Sing Abstract Method) ,
    //  보통 자바에서, 람다식을 연결할 때 사용하는 인터페이스
    //  함수형 인터페이스, 추상 메서드가 하나인 인터페이스
        activityTest8Binding.testBtn.setOnClickListener {
            Toast.makeText(this@Test8Activity,"test !!!!",Toast.LENGTH_SHORT).show()
        }

        //롱 클릭 이벤트 추가하기.
        activityTest8Binding.longBtn.setOnLongClickListener {
            Toast.makeText(this@Test8Activity,"Long Clicked!!!!",Toast.LENGTH_SHORT).show()
            true
        }

    }
    ////////////////////////////////////////////////////////////////////////////////////
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG,"touch down event x:${event.x},event rawX:${event.rawX}, " +
                        "event y:${event.y},event rawY:${event.rawY}  ")
                Toast.makeText(this@Test8Activity,"touch down event",Toast.LENGTH_SHORT).show()
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG,"touch up event x:${event.x},event rawX:${event.rawX}, " +
                        "event y:${event.y},event rawY:${event.rawY}  ")
                Toast.makeText(this@Test8Activity,"touch up event",Toast.LENGTH_SHORT).show()
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG,"touch move event x:${event.x},event rawX:${event.rawX}, " +
                        "event y:${event.y},event rawY:${event.rawY}  ")
                Toast.makeText(this@Test8Activity,"touch move event",Toast.LENGTH_SHORT).show()

            }            }
        return super.onTouchEvent(event)


    }
////////////////////////////////////////////////////////////////////////////////////
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
    when(keyCode){
        KeyEvent.KEYCODE_0 -> Log.d(TAG,"0 키 누름")
        KeyEvent.KEYCODE_A -> Log.d(TAG,"A 키 누름")
        KeyEvent.KEYCODE_ENTER -> Log.d(TAG,"enter 키 누름")


    }
        return super.onKeyDown(keyCode, event)
    }

}