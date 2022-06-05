package com.fimo.aidentist.ui.menu.auth

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.ActivitySignUpBinding
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private val genderItems = listOf("Laki - Laki", "Perempuan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()
        setForm()
        setupView()
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

    private fun phoneFocusListener(){
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
        if (phoneText.length != 12){
            return "Pastikan nomer sesuai"
        }
        return null
    }

    private fun setupAction() {
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
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