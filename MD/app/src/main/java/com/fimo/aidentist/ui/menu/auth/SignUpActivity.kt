package com.fimo.aidentist.ui.menu.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.fimo.aidentist.R
import com.fimo.aidentist.data.model.UserSignUpModel
import com.fimo.aidentist.data.model.UserViewModel
import com.fimo.aidentist.data.model.ViewModelFactory
import com.fimo.aidentist.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: UserViewModel by viewModels { ViewModelFactory.getInstance() }

    private val genderItems = listOf("Laki - Laki", "Perempuan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpViewModel.signUpResponse.observe(this) { data ->
            binding.loadingProgress.isVisible = data.messege.isEmpty()

            if (!data.error && data.messege.isNotEmpty()) {
                startActivity(Intent(this, LoginActivity::class.java))
            }

            if (data.error) {
                Toast.makeText(this, data.messege, Toast.LENGTH_SHORT).show()
                return@observe
            }
        }

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

    private fun isInputReady(): Boolean {
        val isDataInputReady =
            binding.nameEditText.text.toString().trim()
                .isNotEmpty() && binding.emailEditText.text.toString().trim()
                .isNotEmpty() && binding.jenisEditText.text.toString().trim()
                .isNotEmpty() && binding.jenisEditText.toString().trim()
                .isNotEmpty() && binding.emailEditText.toString().trim()
                .isNotEmpty() && binding.passwordEditText.text.toString().trim().isNotEmpty()
        val isPassValidation =
            binding.emailEditText.error.isNullOrBlank() && binding.passwordEditText.error.isNullOrEmpty()
        return isDataInputReady && isPassValidation
    }

    //Send request to server
    private fun sendRequest() {
        val newUser = UserSignUpModel(
            binding.nameEditText.text.toString().trim(),
            binding.emailEditText.text.toString().trim(),
            binding.jenisEditText.text.toString().trim(),
            binding.phoneEditText.toString().trim(),
            binding.passwordEditText.text.toString().trim()
        )
        signUpViewModel.signUp(newUser)
    }

    private fun setForm() {
        val genderAdapter = ArrayAdapter(this, R.layout.item_list_dropdown, genderItems)
        (binding.jenisEditTextLayout.editText as? AutoCompleteTextView)?.setAdapter(genderAdapter)
    }

    private fun setupAction() {
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        binding.buttonSignUp.setOnClickListener {
            if (binding.nameEditText.text.toString().isEmpty()) {
                binding.nameEditText.error = getString(R.string.input_error)
            }

            if (binding.emailEditText.text.toString().isEmpty()) {
                binding.emailEditText.error = getString(R.string.input_error)
            }

            if (binding.phoneEditText.text.toString().isEmpty()) {
                binding.phoneEditText.error = getString(R.string.input_error)
            }

            if (isInputReady()) {
                sendRequest()
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