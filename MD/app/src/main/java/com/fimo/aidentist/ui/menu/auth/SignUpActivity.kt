package com.fimo.aidentist.ui.menu.auth

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.fimo.aidentist.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var fAuth :FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fAuth = Firebase.auth

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        binding.buttonSignUp.setOnClickListener {
            firebaseSignUp()
        }


        setupView()
    }

    private fun firebaseSignUp() {
        fAuth.createUserWithEmailAndPassword(binding.emailEditText.text.toString(),binding.passwordEditText.text.toString()).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Register Success",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, it.exception?.message ,Toast.LENGTH_SHORT).show()

            }
        }
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