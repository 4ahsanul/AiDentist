package com.fimo.aidentist.ui.menu.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.fimo.aidentist.MainActivity
import com.fimo.aidentist.data.UserSign
import com.fimo.aidentist.data.model.UserLoginModel
import com.fimo.aidentist.data.model.UserViewModel
import com.fimo.aidentist.data.model.ViewModelFactory
import com.fimo.aidentist.databinding.ActivityLoginBinding
import com.fimo.aidentist.helper.Constant
import com.fimo.aidentist.helper.PreferenceHelper
import com.fimo.aidentist.data.local.UserPreference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: UserViewModel by viewModels { ViewModelFactory.getInstance() }
    private lateinit var fAuth: FirebaseAuth

    lateinit var sharedPref: PreferenceHelper
    private val db = Firebase.firestore
    private lateinit var new : UserSign


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.loginResponse.observe(this) { data ->
            binding.loadingProgress.isVisible = data.messege.isEmpty()

            if (!data.error && data.messege.isNotEmpty()) {
                moveToMainActivity()
            }

            if (data.error) {
                Toast.makeText(this, data.messege, Toast.LENGTH_SHORT).show()
                return@observe
            }
        }

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

    private fun moveToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun sendRequest() {
        val user = UserLoginModel(
            email = binding.emailEditText.text.toString().trim(),
            password = binding.passwordEditText.text.toString()
        )
        val pref = UserPreference.getInstance(dataStore)
        loginViewModel.login(user, pref)
    }

    private fun setupAction() {
        binding.buttonLogin.setOnClickListener {
            if (binding.emailEditText.text.toString()
                    .isNotEmpty() && binding.passwordEditText.text.toString().isNotEmpty()
            ) {
                //firebaseSignIn()
                sendRequest()
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