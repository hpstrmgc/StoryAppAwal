package com.nat.storyappawal.ui.authpage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nat.storyappawal.data.api.ApiServiceFactory
import com.nat.storyappawal.databinding.ActivityLoginBinding
import com.nat.storyappawal.ui.stories.StoryActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(ApiServiceFactory.create())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if user is already logged in
        if (isUserLoggedIn()) {
            startActivity(Intent(this, StoryActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            finish()
            return
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.edLoginEmail.text.toString()
            val password = binding.edLoginPassword.text.toString()
            showProgressBar(true)
            authViewModel.login(email, password).observe(this, Observer { result ->
                showProgressBar(false)
                result.onSuccess { response ->
                    response.loginResult?.token?.let { token ->
                        saveToken(token)
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, StoryActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                        finish()
                    } ?: run {
                        Toast.makeText(this, "Login failed: Token is null", Toast.LENGTH_SHORT).show()
                    }
                }
                result.onFailure {
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.textViewLinkToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveToken(token: String) {
        val sharedPreferences = getSharedPreferences("your_app_preferences", MODE_PRIVATE)
        sharedPreferences.edit().putString("token", token).apply()
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("your_app_preferences", MODE_PRIVATE)
        return !sharedPreferences.getString("token", null).isNullOrEmpty()
    }

    private fun showProgressBar(show: Boolean) {
        binding.progressIndicator.visibility = if (show) View.VISIBLE else View.GONE
    }
}