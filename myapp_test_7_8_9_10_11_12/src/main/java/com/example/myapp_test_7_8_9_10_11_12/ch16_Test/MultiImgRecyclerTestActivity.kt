package com.example.myapp_test_7_8_9_10_11_12.ch16_Test

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.ch16_Test.adapter.MultiImageAdapter
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityMultiImgRecyclerTestBinding
import java.util.ArrayList

class MultiImgRecyclerTestActivity : AppCompatActivity() {
    lateinit var binding:ActivityMultiImgRecyclerTestBinding

    //사진 위치 Uri를 가지는 리스트 하나 만들기
    var list = ArrayList<Uri>()
    var adapter = MultiImageAdapter(list,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiImgRecyclerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //해야할 것 2가지
        //1. 갤러리 버튼 클릭시, 이벤트 처리하는 부분 -> 후처리, 재사용하고 속성 몇개 고치기
        //2. 리사이클러뷰 준비 재료 1)어댑터 2)뷰홀더 3)목록요소의 레이아웃 구성


        //작업 구성 2가지.
        // 첫번째, 갤러리 앱을 호출 하는 작업
        // 두번째, 갤러리 앱에 데이터를 가져온 내용을 처리하는 작업.

        // 두번째, 갤러리 앱에 데이터를 가져온 내용을 처리하는 작업.
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            // it 여기에, 갤러리에서 선택된 사진의 데이터가 들어 있음.

            //추가 (10장까지로 제한하는 로직)
            list.clear()
            //사진 여러장 선택시 사용할 키워드 : clipData
            if(it.data?.clipData != null){
                //선택된 사진 갯수
                val count = it.data!!.clipData?.itemCount
                if(count != null){
                    if(count>10){
                        Toast.makeText(this,"사진은 10장까지만 선택 가능",Toast.LENGTH_SHORT).show()
                    }
                }

                //반복문으로 사진 데이터 Uri조회해서 리스트에 담기
                for(i in 0 until count!!){
                    val imageUri = it.data!!.clipData?.getItemAt(i)?.uri

                    if(imageUri != null){
                        list.add(imageUri)
                    }
                }
            }else{ //한장인 경우
                it.data.let {
                        uri -> val imageUri:Uri? = it.data?.data
                    if(imageUri != null){
                        list.add(imageUri)
                    }
                }
            }
            adapter.notifyDataSetChanged()
        }//후처리 닫음

        // 버튼클릭시 : 갤러리 앱 호출,
        binding.multiGalleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            // 사진을 여러장 고르는 속성 추가. 시스템에게 사진 여러장 선택 한다는 신고.

            //추가 1) 시스템에게 사진 여러장 선택한다고 알려줌
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            intent.type = "image/*"

            //추가 2)사진에 접근하겠다.
            intent.action = Intent.ACTION_PICK
            requestGalleryLauncher.launch(intent)//후처리
        }

        // 리사이클러뷰 준비 재료 1) 어댑터 2) 뷰홀더 3) 목록 요소의 레이아웃 구성.
        val layoutManager = LinearLayoutManager(this)
        binding.multiImagePickRecycler.layoutManager = layoutManager
        binding.multiImagePickRecycler.adapter = adapter

    }/*onCreate*/

}