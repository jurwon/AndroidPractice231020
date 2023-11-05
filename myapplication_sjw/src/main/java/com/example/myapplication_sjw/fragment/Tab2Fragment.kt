package com.example.myapplication_sjw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication_sjw.R
import com.example.myapplication_sjw.databinding.FragmentTab1Binding
import com.example.myapplication_sjw.databinding.FragmentTab2Binding

class Tab2Fragment : Fragment() {
    lateinit var binding: FragmentTab2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTab2Binding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTab2Binding.inflate(layoutInflater,container,false)
        return binding.root
    }
}