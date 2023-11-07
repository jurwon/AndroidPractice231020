package com.example.myapplication_sjw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Carriers.PASSWORD
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_sjw.adapter.MemberListAdapter
import com.example.myapplication_sjw.databinding.ActivityMemberListBinding
import com.example.myapplication_sjw.model.Member

class MemberListActivity : AppCompatActivity() {

    lateinit var binding: ActivityMemberListBinding

    var list = ArrayList<Member>()
    var adapter = MemberListAdapter(list,this)
    var myDB: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDB = DatabaseHelper(this)

        //리사이클러뷰 붙이기
        val layoutManager = LinearLayoutManager(this)
        binding.memberRecyclerView.layoutManager = layoutManager
        binding.memberRecyclerView.adapter = adapter

        viewAll()

    }//onCreate

    fun viewAll(){
        val res = myDB!!.allData
        // 결과가 없을 때
        if (res.count == 0) {
            ShowMessage("실패", "데이터를 찾을 수 없습니다.")
        }

        list.clear()

        while (res.moveToNext()) {
            var member : Member? = null

            val ID = res.getString(0)
            val PASSWORD = res.getString(1)
            val NAME = res.getString(2)
            val GENDER = res.getString(3)
            val PROFILEIMAGE = res.getString(4)
            member = Member(ID,PASSWORD,NAME,GENDER,PROFILEIMAGE)
            list.add(member)
        }
        adapter.notifyDataSetChanged()
    }

    fun ShowMessage(title: String?, Message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

}