package com.fimo.aidentist.ui.navigation.camera

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.view.Surface
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.ActivityCameraBinding
import com.fimo.aidentist.utils.createFile
import com.fimo.aidentist.utils.showToast
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var photoFile: File

    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraExecutor = Executors.newSingleThreadExecutor()
        //Using cameraSelector can be set in onClick button SwitchCamera
        binding.captureImage.setOnClickListener {
            takePhoto()
        }

        binding.switchCamera.setOnClickListener {
            cameraSelector =
                if (cameraSelector.equals(CameraSelector.DEFAULT_BACK_CAMERA)) CameraSelector.DEFAULT_FRONT_CAMERA
                else CameraSelector.DEFAULT_BACK_CAMERA

            startCamera()
        }
    }

    public override fun onResume() {
        super.onResume()
        setupView()
        startCamera()
    }

    //Take image from camera
    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        photoFile = createFile(application)
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            mImageCapture
        )
    }

    private val mImageCapture = object : ImageCapture.OnImageSavedCallback {
        //onImageSaved to handle taking image process is success
        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            val intent = Intent()
            intent.putExtra(CameraResultActivity.EXTRA_PHOTO, photoFile)
            intent.putExtra(
                CameraResultActivity.BACK_CAMERA,
                cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
            )
            setResult(CameraResultActivity.CAMERA_X_RESULT, intent)
            finish()
        }

        override fun onError(exception: ImageCaptureException) {
            showToast(this@CameraActivity, getString(R.string.failed_taken))
        }
    }

    //Start Camera
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            //ImageCapture can be used by takePhoto function to taking an image from camera
            imageCapture = ImageCapture.Builder().build()
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (exc: Exception) {
                showToast(this@CameraActivity, "Failed open camera")
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}