package com.fimo.aidentist.ui.menu.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fimo.aidentist.MainActivity
import com.fimo.aidentist.data.UserSign
import com.fimo.aidentist.databinding.ActivityLoginBinding
import com.fimo.aidentist.helper.Constant
import com.fimo.aidentist.helper.PreferenceHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var fAuth: FirebaseAuth

    lateinit var sharedPref: PreferenceHelper
    private val db = Firebase.firestore
    private lateinit var new : UserSign


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = PreferenceHelper(this)
        fAuth = Firebase.auth

        setupAction()
        setupView()
        playAnimation()
    }

    override fun onStart() {
        super.onStart()
        if (sharedPref.getBoolean(Constant.PREF_IS_LOGIN)) {
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(applicationContext, "LOGIN SUCCESS WITH PREFERENCE", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageViewLogin, View.TRANSLATION_X, -30F, 30F).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            startDelay = 500
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1F).setDuration(500)
        val emailInput =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val passwordInput =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val button = ObjectAnimator.ofFloat(binding.buttonLogin, View.ALPHA, 1F).setDuration(500)
        val signupTitle =
            ObjectAnimator.ofFloat(binding.tvTittleSignup, View.ALPHA, 15F).setDuration(500)
        val signUpButton =
            ObjectAnimator.ofFloat(binding.tvSignup, View.ALPHA, 15F).setDuration(500)

        //Show animation alternate
        AnimatorSet().apply {
            playSequentially(title, emailInput, passwordInput, button, signupTitle, signUpButton)
            start()
        }
    }

    private fun isInputReady(): Boolean {
        val isDataInputReady =
            binding.emailEditText.text.toString()
                .isNotEmpty() && binding.passwordEditText.text.toString().isNotEmpty()
        val isPassValidation =
            binding.emailEditText.error.isNullOrBlank() && binding.passwordEditText.error.isNullOrEmpty()
        return isDataInputReady && isPassValidation
    }

    private fun setupAction() {
        binding.buttonLogin.setOnClickListener {
            if (binding.emailEditText.text.toString()
                    .isNotEmpty() && binding.passwordEditText.text.toString().isNotEmpty()
            ) {
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
        fAuth.signInWithEmailAndPassword(
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString()
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                sharedPref.put(Constant.PREF_EMAIL, binding.emailEditText.text.toString())
                sharedPref.put(Constant.PREF_PASSWORD, binding.passwordEditText.text.toString())
                sharedPref.put(Constant.PREF_IS_LOGIN, true)
                db.collection("users").document(fAuth.currentUser?.uid.toString())
                    .update("id",fAuth.currentUser?.uid,"email",binding.emailEditText.text.toString() )
                    .addOnSuccessListener {
                        Log.d(ContentValues.TAG, "Berhasil Menyimpan Data")
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e)
                    }

                Toast.makeText(applicationContext, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
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