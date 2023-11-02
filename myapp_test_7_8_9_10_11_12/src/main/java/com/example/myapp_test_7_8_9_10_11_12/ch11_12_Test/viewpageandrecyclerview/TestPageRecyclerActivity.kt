package com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview.adapter.RecyclerViewTest
import com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test.viewpageandrecyclerview.adapter.ViewPageAdapterTest
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestPageRecyclerBinding

class TestPageRecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestPageRecyclerBinding
    lateinit var Tag:String
    //액션버튼 스위치
    lateinit var toggle:ActionBarDrawerToggle
    var newDataNumber = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestPageRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Tag = "sjw"

        //1) 툴바, 2)드로워 3)드로워-네비게이션 4) 플로팅 액션 버튼 5)앱바 6)탭 레이아웃

        //1) 툴바
        setSupportActionBar(binding.toolbar)

        //1)-2 툴바 오버플로우 메뉴 붙이기
        //res->toobar_menu재활용

        //2) 드로워 화면 뷰에서 설정

        //3) 드로워 네비게이션 뷰 추가 설정 ->뷰에서 작업
        //재료 1)네비게이션 헤더 2)본문 : res->메뉴

        //3)-2 이벤트 핸들러 추가하기
        //아이템 요소 클릭 이벤트 추가
        binding.mainDrawerView.setNavigationItemSelectedListener {
            it ->
            if(it.title == "로그인"){
                Toast.makeText(this@TestPageRecyclerActivity,"로그인화면 이동",Toast.LENGTH_SHORT).show()
            }else if(it.title == "로그아웃"){
                Toast.makeText(this@TestPageRecyclerActivity,"로그아웃 이동",Toast.LENGTH_SHORT).show()
            }else if(it.title == "메인가기"){
                Toast.makeText(this@TestPageRecyclerActivity,"메인가기 이동",Toast.LENGTH_SHORT).show()
            }
            true
        }

        //드로워 화면에 액션 버튼 클릭시 ->드로우 화면 나오게
        toggle = ActionBarDrawerToggle(this@TestPageRecyclerActivity,binding.drawer,R.string.open,R.string.close)
        //화면에 붙이는 작업
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        //4) floating action Btn
        binding.floatingActionBtn.setOnClickListener{
            when(binding.floatingActionBtn.isExtended){
                true -> binding.floatingActionBtn.shrink()
                false -> binding.floatingActionBtn.extend()
            }
            Toast.makeText(this@TestPageRecyclerActivity,"create버튼 클릭됨", Toast.LENGTH_SHORT).show()
        }


        // 뷰페이저2 프래그먼트 어댑터 이용해서 출력 해보기.
        // 현재 액티비티 도화지 , 캔버스 , 기본 화면.
        // 여기에 뷰페이저와, 리사이클러뷰 2개를 붙이 예정.

        //뷰페이저2 준비물 1)프래그먼트 어댑터 2) 목록요소 , 프래그먼트 3개 필요. 3) 더미 데이터(=자기자신)
        binding.viewPager1.adapter =ViewPageAdapterTest(this)

        // 리사이클러뷰 붙이기
        // 준비물) 1) 리사이클러뷰 어댑터 2)목록요소의 아이템 뷰 3) 더미 데이터
        val datas = mutableListOf<String>()
        for (i in 1..10){
            datas.add("더미 데이터 추가 번호 $i")
        }
        // 출력 방향, 리니어 나란히 수직으로
        val layoutManager = LinearLayoutManager(this)
        // 리사이클러뷰 속성 옵션에 출력 옵션 붙이기.
        binding.recyclerViewTest.layoutManager = layoutManager
        // 리사이클러뷰 속성 옵션에 데이터를 붙이기 , 어댑터 를 연결한다.
        val customAdapter = RecyclerViewTest(datas)
        binding.recyclerViewTest.adapter = customAdapter

        binding.addBtn.setOnClickListener {
            datas.add("NEW DATA " + newDataNumber++)
            customAdapter.notifyItemInserted(datas.size)
        }

        binding.delBtn.setOnClickListener {
            datas.removeAt(datas.size - 1)
            //항상 변경사항 감지하고 다시 뷰 띄워줌
            customAdapter.notifyDataSetChanged() // 만능, 되도록 사용x
        }
    }//OnCreate 끝부분 ================================================================================


    // 오버플로우 메뉴 이벤트 핸들러 추가하기.
    // 만약, 메뉴 교체 하면, 해당 아이디 다시 재정의하기.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle버튼에서 제공된거라면
        //버튼을 열때 이용되는 이벤트 핸들러 부분
        if(toggle.onOptionsItemSelected(item)){
            return true
            //오버플로우 메뉴 클릭시 이벤트
        } else if ( R.id.menu_toolbar1 == item.itemId) {
            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴1 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar2 == item.itemId) {
            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴2 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar3 == item.itemId) {
            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴3 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        return super.onOptionsItemSelected(item)
    }//검색 이벤트 핸들러 ================================================================================


    // 액션바에 오버플로우 메뉴 붙이기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 만약, 다른 메뉴를 만들어서 교체 작업, 밑에 부분으로 교체.
//        menuInflater.inflate(R.menu.menu_main,menu)
        menuInflater.inflate(R.menu.toolbar_menu,menu)

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
                Toast.makeText(this@TestPageRecyclerActivity,"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }


    // 액션바  업버튼 클릭 이벤트 처리.
    override fun onSupportNavigateUp(): Boolean {
        Log.d(Tag,"test")
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}






