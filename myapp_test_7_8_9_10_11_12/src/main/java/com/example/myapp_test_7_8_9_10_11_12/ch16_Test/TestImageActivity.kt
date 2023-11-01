package com.example.myapp_test_7_8_9_10_11_12.ch16_Test

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestImageBinding

class TestImageActivity : AppCompatActivity() {
    // 갤러리, 카메라 앱 연동해서 데이터 가져오기.
    lateinit var binding : ActivityTestImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //작업 구성 2가지.
        // 첫번째, 갤러리 앱을 호출 하는 작업
        // 두번째, 갤러리 앱에 데이터를 가져온 내용을 처리하는 작업.

        // 버튼클릭시 : 갤러리 앱 호출,
        // 갤러리에서 선택 된 사진을 출력하는 뷰하나 생성.

        // 두번째, 갤러리 앱에 데이터를 가져온 내용을 처리하는 작업.
        val requestGalleryLauncher = registerForActivityResult(
            // 지금 정의하는 부분은 시스템에서 각 사용처 마다 정의가 다 되어 있고,
            // 골라서 사용할 예정. , 현재는 외부앱 에서 데이터 가져오는 역할부분을 이용할 예정.
            // StartActivityForResult -> 이부분 각각 다 정의가 되어 있다.
            ActivityResultContracts.StartActivityForResult()
        ) {
            // it 여기에, 갤러리에서 선택된 사진의 데이터가 들어 있음.
            // 위치가 들어 있다.
            //
            // 1) 불러온 사진을 적절히 크기를 조절해서, OOM(OutOf Memory) ,
            // 2) 바이트로 읽은 다음. ->
            // 3) 비트맵 타입으로 변환작업

            // try ~ catch 구문 사용할 예정. : IO(Input Output), 예외처리가 필요함.
            try {
                // 1) 불러온 사진을 적절히 크기를 조절해서, OOM(OutOf Memory) ,
                // 적당한 비율로 줄여주는 단위를 정하는 임의의 함수.
                // calculateInSampleSize 의 재료 , 인자로
                // 1) fileUri : Uri,
                // 2) reqWidth: Int,
                // 3) reqHeight: Int
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
                val bitmap = BitmapFactory.decodeStream(inputStream,null,options)
                inputStream!!.close()
                inputStream = null

                // 결과 사진을 원하는 뷰에 넣기.
                // 지금, 그냥 출력하지만, 비동기식으로 처리하는 glide 라는 라이브러리를 주로 이용할 예정.
                binding.resultUserImage.setImageBitmap(bitmap)

                Log.d("sjw","갤러리에서 선택된 사진의 크기 비율 calRatio : $calRatio")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("sjw", "사진 출력 실패")

            }

        }

        // 버튼클릭시 : 갤러리 앱 호출,
        binding.galleryBtn.setOnClickListener {
            //갤러리 앱 호출, 인텐트의 액션 문자열을 사용함. 시스템꺼를 사용해서 정해진 문자열.
            // Intent.ACTION_PICK : 갤러리 호출
            // MediaStore.Images.Media.EXTERNAL_CONTENT_URI : 외부 저장소(갤러리)의 사진의 위치
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // data type 지정.
            intent.type = "image/*"
            // 후처리 함수 호출 .-> 데이터 가져오기 위한 로직.
            // requestGalleryLauncher: 아직 후처리 함수 정의 안했음.
            requestGalleryLauncher.launch(intent)

        }

    }  // onCreate

    //크기를 조절해주는 임의의 함수 만들기.
    // 자주 쓰는 기능은 따로 클래스를 만들어서 재사용하면됨. 일단,
    // 원본의 가로 세로 크기를, 적당히 반으로 접어서, 원하는 가로 세로 크기 만큼의 비율로 계산을 해줌.
    // 비율 1, 원본, 크기 4 -> 1/4
    private fun calculateInSampleSize (fileUri : Uri, reqWidth: Int, reqHeight: Int) :Int {
        // 매개변수 1: 사진의 위치 , 2: 요구하는 가로크기 , 3: 요구하는 세로크기
        // 함수의 반환값 : 적절한 비율의 단위
        // 비율은 크기가 클수록, 사이즈를 더 작게 만들어줌. 마치, 썸네일 이미지와 비슷함.
        // 이 함수의 내용은 크게 몰라도 됨.
        // 적당히, 원본사진의 비율을 줄여 나가면서, 적당한 크기의 상수를 구하는 로직.
        // 로직을 이해하면, 이 함수를 통째로 사용할 것임.
        //
        // 안드로이드에서 이미지의 타입을 비트맵으로 정하는데, 거기에 옵션을 넣는 인스턴스.
        val options = BitmapFactory.Options()
        // 사진의 영향을 안주고, 옵션만 제공을 하겠다.
        options.inJustDecodeBounds = true

        // 계산을 하기 위해서, 원본 사진을 읽는 과정이 있어서, 여기에서도 , IO 발생
        // 그래서, try ~ catch 구문이 필요함,.
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