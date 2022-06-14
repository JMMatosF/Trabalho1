package com.example.trabalho1.login

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.example.trabalho1.main.MainActivity
import com.example.trabalho1.R
import com.example.trabalho1.databinding.ActivityLoginBinding
import com.example.trabalho1.databinding.ActivityMainBinding
import com.example.trabalho1.overview.OverviewActivity

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObservers()

    }

    private fun initViews() {
        binding.loginBtn.setOnClickListener {
            viewModel.validateInput(binding.username.text.toString(), binding.password.text.toString())
        }
    }

    private fun initObservers() {
        viewModel.loginResultLiveData.observe(this) {
            when(it) {
                is LoginUiState.Error -> handleError(it.error)
                is LoginUiState.Success -> handleSuccess()
            }
        }
    }

    private fun handleSuccess() {
        navigateToOverview()
    }

    private fun handleError(resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show()
    }

    private fun navigateToOverview() {
        val intent = Intent(this, OverviewActivity::class.java)
        startActivity(intent)
        finish()
    }

}