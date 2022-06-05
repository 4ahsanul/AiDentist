package com.fimo.aidentist.ui.menu.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fimo.aidentist.MainActivity
import com.fimo.aidentist.databinding.ActivityLoginBinding
import com.fimo.aidentist.helper.Constant
import com.fimo.aidentist.helper.PreferenceHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var fAuth :FirebaseAuth

    lateinit var sharedPref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = PreferenceHelper(this)
        fAuth = Firebase.auth

        binding.buttonLogin.setOnClickListener {
            if (binding.emailEditText.text.toString()
                    .isNotEmpty() && binding.passwordEditText.text.toString().isNotEmpty()
            ){
                firebaseSignIn()

            }
        }

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        setupView()
    }

    private fun firebaseSignIn() {
        fAuth.signInWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString()).addOnCompleteListener {
            if (it.isSuccessful) {
                sharedPref.put(Constant.PREF_EMAIL, binding.emailEditText.text.toString())
                sharedPref.put(Constant.PREF_PASSWORD, binding.passwordEditText.text.toString())
                sharedPref.put(Constant.PREF_IS_LOGIN, true)
                Toast.makeText(applicationContext, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, it.exception?.message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (sharedPref.getBoolean(Constant.PREF_IS_LOGIN)){
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(applicationContext, "LOGIN SUCCESS WITH PREFERENCE", Toast.LENGTH_SHORT).show()
            finish()
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