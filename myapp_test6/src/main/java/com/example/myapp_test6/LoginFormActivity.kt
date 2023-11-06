package com.example.myapp_test6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6.databinding.ActivityLoginFormBinding

class LoginFormActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)
    }
}