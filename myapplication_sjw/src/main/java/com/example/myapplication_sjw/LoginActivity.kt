package com.example.myapplication_sjw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication_sjw.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    var myDB: DatabaseHelper? = null

    lateinit var binding:ActivityLoginBinding

    var idEditView: EditText? = null
    var passwordEditView: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //DatabaseHelper 클래스 를 사용한다.
        myDB = DatabaseHelper(this)

        idEditView = binding.idEditView
        passwordEditView = binding.passwordEditView

        //회원가입
        binding.goJoinViewBtn.setOnClickListener {
            Toast.makeText(this@LoginActivity, "회원가입 창으로 이동", Toast.LENGTH_LONG).show()

            val intent = Intent(this@LoginActivity, JoinActivity::class.java)
            startActivity(intent)
        }

        // 최초 1회 실행시, 직접 만든 함수를 호출하는 부분.
        loginCheck()

    }//onCreate끝

    fun loginCheck(){
        //로그인
        binding.loginBtn.setOnClickListener {
            
            //조회 결과값
            val res = myDB!!.loginCheck(idEditView?.text.toString(),passwordEditView?.text.toString())

            Log.d("sjw","로그인 결과 : ${res.count}")
            if(res.count == 0){
                Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_LONG).show()
            }
            
        }
    }
}