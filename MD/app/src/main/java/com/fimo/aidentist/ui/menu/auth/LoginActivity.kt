package com.fimo.aidentist.ui.menu.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
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
import com.fimo.aidentist.R
import com.fimo.aidentist.data.local.UserPreference
import com.fimo.aidentist.data.model.UserLoginModel
import com.fimo.aidentist.data.model.UserViewModel
import com.fimo.aidentist.data.model.ViewModelFactory
import com.fimo.aidentist.databinding.ActivityLoginBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: UserViewModel by viewModels { ViewModelFactory.getInstance() }

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

        setupAction()
        setupView()
        playAnimation()
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
            if (binding.emailEditText.text.toString().isEmpty()) {
                binding.emailEditText.error = getString(R.string.input_error)
                return@setOnClickListener
            }
            if (isInputReady()) {
                sendRequest()
            }
        }

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
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