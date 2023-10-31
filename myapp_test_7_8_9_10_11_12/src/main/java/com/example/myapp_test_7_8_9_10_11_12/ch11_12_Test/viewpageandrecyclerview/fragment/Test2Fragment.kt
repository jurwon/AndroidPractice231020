package com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp_test_7_8_9_10_11_12.databinding.FragmentTest2Binding


class Test2Fragment : Fragment() {
    lateinit var binding: FragmentTest2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTest2Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTest2Binding.inflate(layoutInflater,container,false)
        return binding.root
    }
}