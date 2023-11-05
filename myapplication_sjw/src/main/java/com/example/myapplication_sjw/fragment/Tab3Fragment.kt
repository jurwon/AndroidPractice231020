package com.example.myapplication_sjw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication_sjw.R
import com.example.myapplication_sjw.databinding.FragmentTab1Binding
import com.example.myapplication_sjw.databinding.FragmentTab3Binding

class Tab3Fragment : Fragment() {
    lateinit var binding: FragmentTab3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTab3Binding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTab3Binding.inflate(layoutInflater,container,false)
        return binding.root
    }
}