package com.fimo.aidentist.ui.navigation.camera

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fimo.aidentist.MainActivity

import com.fimo.aidentist.databinding.ActivityCameraResultBinding
import com.fimo.aidentist.ml.Classifier
import com.fimo.aidentist.utils.rotateBitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File

class CameraResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraResultBinding
    private val mInputSize = 150
    private val mModelPath = "model.tflite"
    private val mLabelPath = "labels.txt"
    private lateinit var fAuth: FirebaseAuth
    private lateinit var classifier: Classifier
    private val db = Firebase.firestore

    private var getFile: File? = null

    //User give permission
    private fun allPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClassifier()
        fAuth = Firebase.auth

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.openCamera.setOnClickListener {
            if (!allPermissionGranted()) {
                ActivityCompat.requestPermissions(
                    this,
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
                return@setOnClickListener
            }
            startCameraX()
        }

        binding.checkButton.setOnClickListener {
            val bitmap = ((binding.previewImageView).drawable as BitmapDrawable).bitmap

            val result = classifier.recognizeImage(bitmap)
            runOnUiThread { Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show() }
            db.collection("users").document(fAuth.currentUser?.uid.toString())
                .update("disease" ,result[0].title,"confidence",result[0].confidence.toDouble())
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG, "Berhasil Menyimpan Data")
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()

        }

    }


    private fun initClassifier() {
        classifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)
    }

    //Start CameraX
    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
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