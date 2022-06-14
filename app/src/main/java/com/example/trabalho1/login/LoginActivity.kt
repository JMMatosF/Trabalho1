package com.example.trabalho1.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.trabalho1.databinding.ActivityLoginBinding
import com.example.trabalho1.overview.main.OverviewActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObservers()

    }
    public override fun onStart() {
        super.onStart()
        auth = Firebase.auth

        val currentUser = auth.currentUser
        updateUI(currentUser)

        customToken?.let {
            auth.signInWithCustomToken(it)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCustomToken:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
        Firebase.auth.signOut()
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