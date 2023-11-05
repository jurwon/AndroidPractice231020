package com.example.myapplication_sjw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication_sjw.R
import com.example.myapplication_sjw.databinding.FragmentTab1Binding

class Tab1Fragment : Fragment() {
    lateinit var binding: FragmentTab1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTab1Binding.inflate(layoutInflater)

        //1) 툴바
        //(activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTab1Binding.inflate(layoutInflater,container,false)
        return binding.root
    }



}