package com.fimo.aidentist.ui.navigation.camera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fimo.aidentist.databinding.ActivityCameraBinding
import com.fimo.aidentist.databinding.ActivityCameraResultBinding
import com.fimo.aidentist.utils.rotateBitmap
import java.io.File

class CameraResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraResultBinding

    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private val launcherIntentCameraX = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile: File = it.data?.getSerializableExtra(EXTRA_PHOTO) as File
            val backCamera = it.data?.getBooleanExtra(BACK_CAMERA, true) as Boolean
            getFile = myFile

            val result = if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
                BitmapFactory.decodeFile(myFile.path)
            } else {
                rotateBitmap(BitmapFactory.decodeFile(myFile.path), backCamera)
            }

            binding.previewImageView.setImageBitmap(result)
        }
    }

    companion object {
        const val CAMERA_X_RESULT = 200
        const val EXTRA_PHOTO = "extra_photo"
        const val BACK_CAMERA = "extra_BackCamera"

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}