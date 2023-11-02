package com.example.myapp_test6

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test6.adapter.MultiImageAdapter
import com.example.myapp_test6.databinding.ActivityMemberListBinding
import com.example.myapp_test6.model.Member


class MemberListActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemberListBinding

    var list = ArrayList<Member>()
    var adapter = MultiImageAdapter(list,this)
    var showMemberListBtn: Button? = null
    var myDB: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showMemberListBtn = binding.showMemberListBtn
        myDB = DatabaseHelper(this)

        //회원가입 창으로 이동 joinViewBtn
        binding.joinViewBtn.setOnClickListener {
            val intent = Intent(this@MemberListActivity, JoinActivity::class.java)
            startActivity(intent)
            finish()
        }

        //리사이클러뷰 붙이기
        val layoutManager = LinearLayoutManager(this)
        binding.memberRecyclerView.layoutManager = layoutManager
        binding.memberRecyclerView.adapter = adapter

        //회원 리스트 조회(리사이클러뷰)
        viewAll()


    }/*onCreate*/

    // 데이터베이스 읽어오기

    //2 ) 새로운 방법
    fun viewAll() {
        // SAM (Single Abstrac Method) 함수형 인터페이스, 추상 메서드가 하나인 메서드.
        // 람다식으로 표현 할 때 , 자주 이용되는 기법 중 하나임.
        showMemberListBtn!!.setOnClickListener(View.OnClickListener {
            // res에 조회된 , 테이블의 내용이 들어가 있다. select 의 조회의 결괏값있다.
            // res -> Cursor = 테이블
            val res = myDB!!.allData
            // 결과가 없을 때
            if (res.count == 0) {
                ShowMessage("실패", "데이터를 찾을 수 없습니다.")
                return@OnClickListener
            }

            list.clear()

            while (res.moveToNext()) {
                var member : Member? = null

                val ID = res.getString(0)
                val NAME = res.getString(1)
                val EMAIL = res.getString(2)
                val PASSWORD = res.getString(3)
                val PROFILEIMAGE = res.getString(4)
                member = Member(ID,NAME,EMAIL,PASSWORD,PROFILEIMAGE)
                list.add(member)
            }
            adapter.notifyDataSetChanged()
        })
    }





    //1) 기존 방법
    /*fun viewAll() {
        showMemberListBtn!!.setOnClickListener(View.OnClickListener {
            // res에 조회된 , 테이블의 내용이 들어가 있다. select 의 조회의 결괏값있다.
            val res = myDB!!.allData
            // 결과가 없을 때
            if (res.count == 0) {
                ShowMessage("실패", "데이터를 찾을 수 없습니다.")
            }

            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append(
                    //코틀린 3중 따옴표, 멀티 라인.
                    // 1행의 첫번째 컬럼을 가져오기.
                    """
    ID: ${res.getString(0)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    이름: ${res.getString(1)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    EMAIL: ${res.getString(2)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    PW: ${res.getString(3)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    ProfileImg: ${res.getString(4)}
    
    """.trimIndent()
                )
            }
            ShowMessage("데이터", buffer.toString())
        })
    }*/

    //사용자 정의 다이얼로그창 자주 이용할 때 사용하는 기본 샘플 코드
    fun ShowMessage(title: String?, Message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }
}