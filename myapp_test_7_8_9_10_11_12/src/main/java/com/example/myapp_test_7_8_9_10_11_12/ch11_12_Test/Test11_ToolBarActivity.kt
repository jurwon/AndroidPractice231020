package com.example.myapp_test_7_8_9_10_11_12.ch11_12_Test

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTest11ToolBarBinding

class Test11_ToolBarActivity : AppCompatActivity() {
    lateinit var binding : ActivityTest11ToolBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTest11ToolBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 붙이기 작업.
        setSupportActionBar(binding.toolbar)

        // 이식 작업, 재사용
        //액션바에 업버튼 붙이기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // 일반 텍스트뷰와(현재 sdk 24), 호환성을 고려한 뷰의 차이점 소개.
        // lineHeight (sdk 28 버전이후로 추가된 기능.)
//        binding.testView.lineHeight = 50

        // 호환성 고려한 텍스트 뷰에서, lineHeight 사용해보기.
        binding.testCompView.lineHeight = 50
    }

    // 액션바  업버튼 클릭 이벤트 처리.
    override fun onSupportNavigateUp(): Boolean {
        Log.d("lsy","test")
        onBackPressed()
        return super.onSupportNavigateUp()
    }

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
                Log.d("lsy","텍스트 변경시 마다 호출 : ${newText} ")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어가 제출이 되었을 경우, 연결할 로직.
                // 사용자 디비, 검색을하고, 그 결과 뷰를 출력하는 형태.
                Toast.makeText(this@Test11_ToolBarActivity,"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    // 오버플로우 메뉴 이벤트 핸들러 추가하기.
    // 만약, 메뉴 교체 하면, 해당 아이디 다시 재정의하기.
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.menu_toolbar1 -> {
            Toast.makeText(this@Test11_ToolBarActivity,"툴바메뉴1 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.menu_toolbar2 -> {
            Toast.makeText(this@Test11_ToolBarActivity,"툴바메뉴2 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.menu_toolbar3 -> {
            Toast.makeText(this@Test11_ToolBarActivity,"툴바메뉴3 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }

       /* R.id.menu_main4 -> {
            Toast.makeText(this@Test11_ToolBarActivity,"메뉴4 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }*/
        // 람다식에서 return 사용 못함.
        else -> super.onOptionsItemSelected(item)
    }
}