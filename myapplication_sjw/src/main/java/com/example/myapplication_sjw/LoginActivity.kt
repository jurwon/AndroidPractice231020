package com.example.myapplication_sjw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication_sjw.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //로그인
        binding.loginBtn.setOnClickListener {
            Toast.makeText(this@LoginActivity, "로그인 클릭", Toast.LENGTH_LONG).show()

        }

        //회원가입
        binding.goJoinViewBtn.setOnClickListener {
            Toast.makeText(this@LoginActivity, "회원가입 창으로 이동", Toast.LENGTH_LONG).show()

            val intent = Intent(this@LoginActivity, JoinActivity::class.java)
            startActivity(intent)
        }

    }
}