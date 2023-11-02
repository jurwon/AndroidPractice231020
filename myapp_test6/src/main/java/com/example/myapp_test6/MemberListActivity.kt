package com.example.myapp_test6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test6.databinding.ActivityMemberListBinding

class MemberListActivity : AppCompatActivity() {
    lateinit var binding:ActivityMemberListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //회원가입 창으로 이동 joinViewBtn
        binding.joinViewBtn.setOnClickListener {
            val intent = Intent(this@MemberListActivity, JoinActivity::class.java)
            startActivity(intent)
        }

        //회원 리스트 조회(리사이클러뷰)
        binding.showMemberListBtn.setOnClickListener{

        }

    }
}