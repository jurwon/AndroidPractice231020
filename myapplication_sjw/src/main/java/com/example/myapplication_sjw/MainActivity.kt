package com.example.myapplication_sjw

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_sjw.adapter.ViewPageAdapter
import com.example.myapplication_sjw.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var Tag:String
    // 액션 버튼 토글(스위치), 서랍화면 나오게 하는 버튼.
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Tag = "sjw"

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //----- 테스트를 위한 더미 데이터 생성 --------------------
        val testDataSet = ArrayList<String>()
        for (i in 0..19) {
            testDataSet.add("TEST DATA$i")
        }
        //--------------------------------------------------------


        //1) 툴바
        //setSupportActionBar(binding.toolbar)

        // 탭 레이아웃과 뷰페이저2 연동하기.
        // 탭 뷰 부분 선택
        val tabLayout = binding.tabs
        // 뷰페이져2 뷰 부분 선택
        val viewPager = binding.viewpagerTab
        //어댑터 연결
        viewPager.adapter = ViewPageAdapter(this)

        // 뷰페이져2, 탭 레이아웃 연결 시켜주는 기능.
        TabLayoutMediator(tabLayout, viewPager) {
                tab, position -> tab.text = "Tab${position+1}"
        }.attach()


        // 3) 이벤트 핸들러 추가하기.
        // 각 아이템 요소 클릭 이벤트 추가. 각 뷰마다 이벤트 핸들러가 다 다름.
        binding.mainDrawerView.setNavigationItemSelectedListener {
                it ->
            if (it.title == "로그인") {
                Toast.makeText(this@MainActivity,"로그인 화면 이동",Toast.LENGTH_SHORT).show()
            }
            else if (it.title == "로그아웃") {
                Toast.makeText(this@MainActivity,"로그아웃 화면 이동",Toast.LENGTH_SHORT).show()
            }
            else if (it.title == "회원가입") {
                Toast.makeText(this@MainActivity,"회원가입 화면 이동",Toast.LENGTH_SHORT).show()
            }
            else if (it.title == "내 정보") {
                Toast.makeText(this@MainActivity,"내 프로필 화면 이동",Toast.LENGTH_SHORT).show()
            }
            else if (it.title == "회원 조회") {
                Toast.makeText(this@MainActivity,"회원조회 화면 이동",Toast.LENGTH_SHORT).show()
            }
            true
        }


        // 드로워 화면에 액션 버튼 클릭시 -> 드로워 화면에 나오게.
        toggle = ActionBarDrawerToggle(this@MainActivity,
            binding.drawer,R.string.open,R.string.close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()



    }

    // 2) 액션바에 오버플로우 메뉴 붙이기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 만약, 다른 메뉴를 만들어서 교체 작업, 밑에 부분으로 교체.
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_toolbar_search)
        // menuItem 의 형을 SearchView 타입으로 변환, 형변환
        // SearchView -> 자동 임포트 주의 -> 제트팩이 아닌 일반 android 로 임포트 하면 안됨.
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어가 변경시 마다, 실행될 로직을 추가.
                Log.d(Tag,"텍스트 변경시 마다 호출 : ${newText} ")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어가 제출이 되었을 경우, 연결할 로직.
                // 사용자 디비, 검색을하고, 그 결과 뷰를 출력하는 형태.
                Toast.makeText(this@MainActivity,"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}