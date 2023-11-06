package com.example.myapplication_sjw.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.myapplication_sjw.R
import com.example.myapplication_sjw.databinding.FragmentTab1Binding

class Tab1Fragment : Fragment() {
    lateinit var binding: FragmentTab1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTab1Binding.inflate(layoutInflater)

        //1) 툴바
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTab1Binding.inflate(layoutInflater,container,false)
        return binding.root
    }

    // 2) 액션바에 오버플로우 메뉴 붙이기
    override fun onCreateOptionsMenu(menu: Menu,inflater: MenuInflater) {
        // 만약, 다른 메뉴를 만들어서 교체 작업, 밑에 부분으로 교체.
        inflater.inflate(R.menu.toolbar_menu,menu)

        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_toolbar_search)
        // menuItem 의 형을 SearchView 타입으로 변환, 형변환
        // SearchView -> 자동 임포트 주의 -> 제트팩이 아닌 일반 android 로 임포트 하면 안됨.
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어가 변경시 마다, 실행될 로직을 추가.
                Log.d("sjw","텍스트 변경시 마다 호출 : ${newText} ")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어가 제출이 되었을 경우, 연결할 로직.
                // 사용자 디비, 검색을하고, 그 결과 뷰를 출력하는 형태.
                Toast.makeText(requireContext(),"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })
    }


}
