package com.example.myapplication_sjw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication_sjw.databinding.ActivityMemberListBinding

class MemberListActivity : AppCompatActivity() {

    lateinit var binding: ActivityMemberListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberListBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}