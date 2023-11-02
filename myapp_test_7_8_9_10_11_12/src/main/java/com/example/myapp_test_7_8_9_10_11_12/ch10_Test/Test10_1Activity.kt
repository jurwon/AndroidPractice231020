package com.example.myapp_test_7_8_9_10_11_12.ch10_Test

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.ch7_Test.TestActivity
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTest101Binding

class Test10_1Activity : AppCompatActivity() {
    //추가
    lateinit var activityTest101Binding: ActivityTest101Binding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 추가
        activityTest101Binding = ActivityTest101Binding.inflate(layoutInflater)
        // 변경
        setContentView(activityTest101Binding.root)

        //10장의 내용.   // 허가 확인 여부 테스트
        val status = ContextCompat.checkSelfPermission(this@Test10_1Activity,
            "android.permission.ACCESS_FINE_LOCATION")

        if(status == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this@Test10_1Activity,"위치 권한 승인됨", Toast.LENGTH_SHORT).show()
            Log.d("sjw","권한이 승인됨 : ${status}")

        } else {
            Toast.makeText(this@Test10_1Activity,"위치 권한 승인안됨", Toast.LENGTH_SHORT).show()
            Log.d("sjw","권한이 승인안됨 : ${status}")
        }

        // 후처리, 인텐트 기본적인 사용법 단순 페이지 이동만 사용했음.
        // 인텐트 ? -> 시스템에 메세지를 전달자.
        // 예) 페이지 이동 같은 경우, 시스템에 요청해서, 이동했음.
        // 예2) 특정 앱에 접근을 해서, 데이터를 가져오는 작업 (= 후처리)
        // 설정 1)
        val requestPermissionLauncher = registerForActivityResult(
            // 이부분이 시스템에서 정한둔 함수들이 있음. 현재, 허가를 확인 하는 용도.
            // 나중에, 이미지등 데이터에 접근해서, 해당 데이터를 가지고 오는 용도로 사용할 예정.
            ActivityResultContracts.RequestPermission() ) {
                isGranted ->
            if(isGranted) {
                Log.d("sjw","권한이 승인됨 , call back 후처리 요청. ")
            } else {
                Log.d("sjw","권한이 승인안됨 , call back 후처리 요청. ")
            }
        }
        // 이용 -> 호출, 위에 설정으로
        requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")

        activityTest101Binding.testToastBtn?.setOnClickListener {
            // 기존 사용법
//            Toast.makeText(this@Test10_1Activity,"후처리 확인중", Toast.LENGTH_LONG).show()
            // 콜백을 익명 클래스를 추가해서, 사라지거나, 또는 나타나거나 했을 경우 추가 로직 넣기.
            val toast = Toast.makeText(this@Test10_1Activity,"후처리 확인중", Toast.LENGTH_LONG)
            toast.addCallback(
                object : Toast.Callback() {
                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("sjw","토스트 후처리 작업: 사라질 경우 ")
                        val intent = Intent(this@Test10_1Activity, TestActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("sjw","토스트 후처리 작업: 나타날 경우 ")
                    }
                }
            )
            toast.show()
        }

        // 날짜 다이얼 로그 출력 해보기.
        activityTest101Binding.dateBtn?.setOnClickListener {
            DatePickerDialog(this@Test10_1Activity , object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d("sjw","년도: ${year}년, 월: ${month+1}월, 일: ${dayOfMonth}"  )
                    Toast.makeText(this@Test10_1Activity,"년도: ${year}년, 월: ${month+1}월, 일: ${dayOfMonth}"
                        , Toast.LENGTH_SHORT).show()
                    // 텍스트 뷰에 설정해보기.
                    activityTest101Binding.dateTextView?.text = "${year}년 ${month+1}월 ${dayOfMonth}일"
                }
            },2023,9,25).show()
        }

        //시간 다이얼로그 테스트 해보기.
        activityTest101Binding.timeBtn?.setOnClickListener {
            TimePickerDialog(this@Test10_1Activity, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Log.d("sjw","${hourOfDay}시, ${minute}분"  )
                    Toast.makeText(this@Test10_1Activity,"${hourOfDay}시, ${minute}분"
                        , Toast.LENGTH_SHORT).show()
                    // 텍스트 뷰에 설정해보기.
                    activityTest101Binding.timeTextView?.text = "${hourOfDay}시, ${minute}분"
                }
            },14,21,true).show()
        }

        // 커스텀 마이징 한 다이얼로그 출력 해보기. 기본값
        activityTest101Binding.customDialogBtn?.setOnClickListener {
            AlertDialog.Builder(this@Test10_1Activity).run {
                setTitle("커스텀 다이얼로그")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("테스트 할까요?")
                setPositiveButton("수락",null)
                setNegativeButton("취소",null)
                setNeutralButton("더보기",null)
                show()
            }
        }

        // 목록 요소 선택 1.
        val items = arrayOf<String>("사과","바나나","수박","파인애플")

        activityTest101Binding.customDialogBtn2?.setOnClickListener {
            AlertDialog.Builder(this@Test10_1Activity).run {
                setTitle("커스텀 다이얼로그2")
                setIcon(android.R.drawable.ic_dialog_info)
//                setMessage("테스트 할까요?")
                // 추가 사항
                val objectListener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("sjw","선택한 과일 : ${items[which]}")
                    }
                }
                setItems(items,objectListener)


                setPositiveButton("수락",null)
                setNegativeButton("취소",null)
                setNeutralButton("더보기",null)
                show()
            }
        }

        // 목록 요소 선택 2.
        activityTest101Binding.customDialogBtn3?.setOnClickListener {
            AlertDialog.Builder(this@Test10_1Activity).run {
                setTitle("커스텀 다이얼로그3")
                setIcon(android.R.drawable.ic_dialog_info)
//                setMessage("테스트 할까요?")
                // 추가 사항
//                val objectListener = object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int) {
//                        Log.d("sjw","선택한 과일 : ${items[which]}")
//                    }
//                }
                // 체크박스용 클릭 리스너 ,
                val objectListener = object : DialogInterface.OnMultiChoiceClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                        Log.d("sjw","${items[which]}이 ${if(isChecked) "선택됨"  else "선택해제됨"}")
                    }
                }
                // 목록요소 1
                //setItems(items,objectListener)

                // 목록요소2 , 체크박스
                setMultiChoiceItems(items, booleanArrayOf(true,true,false,false),objectListener)


                setPositiveButton("수락",null)
                setNegativeButton("취소",null)
                setNeutralButton("더보기",null)
                show()
            }
        }

        //목록 요소 선택3, 라디오
        activityTest101Binding.customDialogBtn4?.setOnClickListener {
            AlertDialog.Builder(this@Test10_1Activity).run {
                setTitle("커스텀 다이얼로그4")
                setIcon(android.R.drawable.ic_dialog_info)
                // 체크박스용 클릭 리스너 ,
//                val objectListener = object : DialogInterface.OnMultiChoiceClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
//                        Log.d("sjw","${items[which]}이 ${if(isChecked) "선택됨"  else "선택해제됨"}")
//                    }
//                }

                // 라디오 클릭 리스너
                val objectListener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("sjw","선택한 과일 : ${items[which]}")
                    }
                }
                // 목록요소 1
                //setItems(items,objectListener)

                // 목록요소2 , 체크박스
//                setMultiChoiceItems(items, booleanArrayOf(true,true,false,false),objectListener)

                // 목록 요소3, 라디오
                setSingleChoiceItems(items,1,objectListener )


                setPositiveButton("수락",null)
                setNegativeButton("취소",null)
                setNeutralButton("더보기",null)
                // 뒤로가기 버튼을 눌려도 ,알림창 닫아짐. 기본값.
                // 옵션으로 false 설정시, 창 닫힘 방지함.
                setCancelable(false)
                show()
                // 다이얼로그창이 나타났을 경우, 창 밖을 클릭시
                // 기본이 알림창을 닫기가 기본인데, false
                //  창 밖을 클릭해도 창이 닫히지 않음.
            }.setCanceledOnTouchOutside(false)
        }

        // 소리 확인 테스트
        activityTest101Binding.soundTestBtn?.setOnClickListener {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext,notification)
            ringtone.play()

        }
    }
}