package com.fimo.aidentist.ui.menu.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var fAuth: FirebaseAuth

    private val genderItems = listOf("Laki - Laki", "Perempuan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fAuth = Firebase.auth

        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()
        playAnimation()
        setForm()
        setupAction()
        setupView()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageViewSignup, View.TRANSLATION_X, -30F, 30F).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            startDelay = 500
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1F).setDuration(500)
        val nameInput =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val emailInput =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val genderInput =
            ObjectAnimator.ofFloat(binding.jenisEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val telephoneInput =
            ObjectAnimator.ofFloat(binding.phoneEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val passwordInput =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val button = ObjectAnimator.ofFloat(binding.buttonSignUp, View.ALPHA, 1F).setDuration(500)
        val signupTitle =
            ObjectAnimator.ofFloat(binding.tvTittleLogin, View.ALPHA, 15F).setDuration(500)
        val signUpButton = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 15F).setDuration(500)

        //Show animate alternate
        AnimatorSet().apply {
            playSequentially(
                title,
                nameInput,
                emailInput,
                genderInput,
                telephoneInput,
                passwordInput,
                button,
                signupTitle,
                signUpButton
            )
            start()
        }
    }

    private fun setForm() {
        val genderAdapter = ArrayAdapter(this, R.layout.item_list_dropdown, genderItems)
        (binding.jenisEditTextLayout.editText as? AutoCompleteTextView)?.setAdapter(genderAdapter)
    }

    private fun emailFocusListener() {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailEditTextLayout.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.emailEditText.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordEditTextLayout.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.passwordEditText.text.toString()
        if (passwordText.length < 8) {
            return "Password terlalu pendek"
        }
        return null
    }

    private fun phoneFocusListener() {
        binding.phoneEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.phoneEditTextLayout.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String? {
        val phoneText = binding.phoneEditText.text.toString()
        if (!phoneText.matches(".*[0-9].*".toRegex())) {
            return "Pastikan nomer menggunakan angka"
        }
        if (phoneText.length != 12) {
            return "Pastikan nomer sesuai"
        }
        return null
    }

    private fun setupAction() {
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.buttonSignUp.setOnClickListener {
            firebaseSignUp()
        }


        setupView()
    }

    private fun firebaseSignUp() {
        fAuth.createUserWithEmailAndPassword(
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString()
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
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