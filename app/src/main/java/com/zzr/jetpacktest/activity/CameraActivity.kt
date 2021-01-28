package com.zzr.jetpacktest.activity

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.os.EnvironmentCompat
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.databinding.ActivityCameraBinding
import com.zzr.jetpacktest.entity.CityBean
import com.zzr.jetpacktest.sputils.SpBase
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    companion object {
        const val TAG = "CameraActivity"

        // 申请相机权限的requestCode
        const val PERMISSION_CAMERA_REQUEST_CODE = 0x00000012
        const val CAMERA_REQUEST_CODE = 0x00000013
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_camera)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOPenCamera.setOnClickListener {
            checkPermissionAndCamera()
        }

    }

    private fun checkPermissionAndCamera() {
        val hasCameraPermission =
            ContextCompat.checkSelfPermission(application, Manifest.permission.CAMERA)
        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {
            //有调起相机拍照。
            openCamera()
        } else {
            //没有权限，申请权限。
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),
                PERMISSION_CAMERA_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //允许权限，有调起相机拍照。
                openCamera()
            } else {
                //拒绝权限，弹出提示框。
                Toast.makeText(this, "拍照权限被拒绝", Toast.LENGTH_LONG).show()
            }
        }
    }

    //用于保存拍照图片的uri
    private var mCameraUri: Uri? = null

    // 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
    private var mCameraImagePath: String? = null

    // 是否是Android 10以上手机
    private val isAndroidQ: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    /**
     * 调起相机拍照
     */
    private fun openCamera() {
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // 判断是否有相机
        if (captureIntent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            var photoUri: Uri? = null
            if (isAndroidQ) {
                // 适配android 10
                photoUri = createImageUri()
            } else {
                try {
                    photoFile = createImageFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (photoFile != null) {
                    mCameraImagePath = photoFile.absolutePath
                    photoUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
                        FileProvider.getUriForFile(this, "$packageName.fileprovider", photoFile)
                    } else {
                        Uri.fromFile(photoFile)
                    }
                }
            }
            mCameraUri = photoUri
            if (photoUri != null) {
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                startActivityForResult(captureIntent, CAMERA_REQUEST_CODE)
            }
        }
    }

    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     */
    private fun createImageUri(): Uri? {
        val status: String = Environment.getExternalStorageState()
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        return if (status == Environment.MEDIA_MOUNTED) {
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues())
        } else {
            contentResolver.insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, ContentValues())
        }
    }

    /**
     * 创建保存图片的文件
     */
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        val imageName: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        storageDir?.run {
            if (!storageDir.exists()) {
                storageDir.mkdir()
            }
        }

        val tempFile = File(storageDir, imageName)
        return if (Environment.MEDIA_MOUNTED != EnvironmentCompat.getStorageState(tempFile)) {
            null
        } else tempFile
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (isAndroidQ) {
                    // Android 10 使用图片uri加载
                    binding.ivShow.setImageURI(mCameraUri)
                } else {
                    // 使用图片路径加载
                    binding.ivShow.setImageBitmap(BitmapFactory.decodeFile(mCameraImagePath))
                }
            } else {
                Toast.makeText(this, "取消", Toast.LENGTH_LONG).show()
            }
        }
    }

}