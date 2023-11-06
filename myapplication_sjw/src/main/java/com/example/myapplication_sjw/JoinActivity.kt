package com.example.myapplication_sjw

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.myapplication_sjw.databinding.ActivityJoinBinding
import com.example.myapplication_sjw.databinding.ActivityLoginBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class JoinActivity : AppCompatActivity() {
    lateinit var binding: ActivityJoinBinding

    lateinit var filePath : String
    lateinit var profileImageUri : String

    var editTextID: EditText? = null
    var editTextPassword: EditText? = null
    var editTextName: EditText? = null
    var gender:String? = null

    var joinBtn: Button? = null

    var myDB: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //db연동작업
        myDB = DatabaseHelper(this)

        editTextID = binding.editTextID
        editTextPassword = binding.editTextPassword
        editTextName = binding.editTextName
        gender = getValue(binding.testRadioGroup)
        joinBtn = binding.joinBtn

        AddData()


        // 1) 버튼클릭시 : 갤러리 앱 호출,
        binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        // 1) 버튼클릭시 : 카페라 앱 호출,
        binding.cameraBtn.setOnClickListener {
            //UUID로 이름 설정
            val timeStamp : String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

            // 촬영된 사진의 저장소 위치 정하기.
            // Environment.DIRECTORY_PICTURES : 정해진 위치, 갤러리
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

            // 위에서 만든 파일이름과, 저장소위치에 실제 물리 파일 생성하기.
            // 빈 파일.
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )

            // 실제 사진 파일 저장소 위치 정의 , 절대 경로
            filePath = file.absolutePath

            //콘텐츠 프로바이더를 이용해서, 데이터를 가져와야 함.
            var photoURI:Uri = FileProvider.getUriForFile(
                this@JoinActivity,
                "com.example.myapplication_sjw.fileprovider",
                file
            )
            // 카메라를 촬영하는 정해진 액션 문자열
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // 키: MediaStore.EXTRA_OUTPUT , 값 : photoURI
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)

        }



    }//onCreate끝=============================================================



    // 2) 갤러리 앱에서 가져온 내용 처리
    val requestGalleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        try {
            profileImageUri = it.data!!.data.toString()!!
            val calRatio = calculateInSampleSize(
                it.data!!.data!!,
                // 가로 , 세로 : 150dp x 150dp
                resources.getDimensionPixelSize(R.dimen.profile_img_width),
                resources.getDimensionPixelSize(R.dimen.profile_img_height),
            )
            val options = BitmapFactory.Options()
            // 사진의 크기가, 원하는 크기에 거의 비슷하게 맞게 크기 조절이 됨.
            options.inSampleSize = calRatio

            // 사진을 읽은 바이트
            var inputStream = contentResolver.openInputStream(it.data!!.data!!)
            // 비트맵 타입으로 변환 -> 옵션에 크기 비율들어 있어서, ->
            // 비트맵은 크기가 거의 150 x 150 가까이 되어 있음.
            val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null

            // 결과 사진을 원하는 뷰에 넣기.
            // 지금, 그냥 출력하지만, 비동기식으로 처리하는 glide 라는 라이브러리를 주로 이용할 예정.
            binding.resultUserImage.setImageBitmap(bitmap)

            Log.d("sjw", "갤러리에서 선택된 사진의 크기 비율 calRatio : $calRatio")

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("sjw", "사진 출력 실패")

        }
    }

    //카메라 호출해서, 사진 촬영된 사진 가져오기.
    val requestCameraFileLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        profileImageUri = Uri.fromFile(File(filePath)).toString()

        //it , 카메라로 촬영된 사진이 들어 있음.
        // 원본 사진 크기 조절하는 비율 단위(정수값)
        // 사진을 AVD 가상머신을 넣을 때 어느정도 비율이 줄어들었음.
        val calRatio = calculateInSampleSize(
            Uri.fromFile(File(filePath)),
            resources.getDimensionPixelSize(R.dimen.profile_img_width),
            resources.getDimensionPixelSize(R.dimen.profile_img_height),
        )
        // 크기 옵션을 담을 인스턴스 생성.
        val options = BitmapFactory.Options()
        options.inSampleSize = calRatio
        // 촬영된 사진을 bitmap 타입으로 변환.
        val bitmap = BitmapFactory.decodeFile(filePath,options)
        // 비트맵 타입으로 변환된 사진을 출력하기 결과 뷰에
        binding.resultUserImage.setImageBitmap(bitmap)


    }

    //성별
    fun getValue(v: View?): String? {
        val male = binding.radio1
        val female = binding.radio2
        var pickValue: String? = null
        if (male.isChecked) {
            pickValue = male.text.toString()
        } else if (female.isChecked) {
            pickValue = female.text.toString()
        }
        return pickValue
    }



    //데이터베이스 추가하기
    fun AddData() {
        joinBtn!!.setOnClickListener {
            gender = getValue(binding.testRadioGroup)

            val isInserted = myDB!!.insertData(
                editTextID!!.text.toString(),
                editTextPassword!!.text.toString(),
                editTextName!!.text.toString(),
                gender,
                profileImageUri
            )
            if (isInserted == true){
                Toast.makeText(this@JoinActivity, "데이터추가 성공", Toast.LENGTH_LONG).show()
                val intent = Intent(this@JoinActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            else Toast.makeText(this@JoinActivity, "데이터추가 실패", Toast.LENGTH_LONG).show()

        }
    }

    //사진 사이즈 조절
    private fun calculateInSampleSize (fileUri : Uri, reqWidth: Int, reqHeight: Int) :Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        try {
            // fileUri : 사진이 들어있는 위치, 파일을 바이트로 읽었음.
            // inputStream : 사진의 읽은 값이 바이트 단위로 들어 있음.
            var inputStream = contentResolver.openInputStream(fileUri)

            // 변환 작업.
            BitmapFactory.decodeStream(inputStream,null,options)
            inputStream!!.close()
            inputStream = null
        } catch (e:Exception) {
            e.printStackTrace()
            Log.d("sjw", "사진 크기 비율 계산 실패 ")
        }
        // 비율 계산
        val (height : Int, width: Int) = options.run { outHeight to outWidth }
        // 비율 1, 원본 사이즈 크기
        var inSampleSize = 1
        // 원본 사진의 크기 height x width 예를 3000 x 2000 px
        // reqHeight : 150 , reqWidth : 150
        if (height > reqHeight || width > reqWidth) {

            // 반으로 접기
            val halfHeight : Int = height / 2
            val halfWidth : Int = width / 2

            while ( halfHeight / inSampleSize >= reqHeight &&
                halfWidth / inSampleSize >= reqWidth ) {
                // 비율 2배씩 증가, 값이 증가하면, 사이즈의 크기는 줄어듬.
                inSampleSize *= 2
            }
        }

        return  inSampleSize

    }


}