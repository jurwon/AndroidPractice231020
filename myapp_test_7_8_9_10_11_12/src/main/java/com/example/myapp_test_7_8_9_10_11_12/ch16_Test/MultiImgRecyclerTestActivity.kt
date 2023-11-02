package com.example.myapp_test_7_8_9_10_11_12.ch16_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityMultiImgRecyclerTestBinding

class MultiImgRecyclerTestActivity : AppCompatActivity() {
    lateinit var binding:ActivityMultiImgRecyclerTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiImgRecyclerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}