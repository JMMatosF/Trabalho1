package com.example.trabalho1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

        private  val viewModel: LoginViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            setup()

        }

        private fun setup(){
            findViewById<Button>(R.id.loginbtn).setOnClickListener {

            }

            viewModel.loginResultLiveData.observe(this){
                loginResult ->
                if (loginResult){
                    findViewById<TextView>(R.id.textView).text = getString(R.string.erro)
                }
                else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this@LoginActivity, "Bem-vindo", Toast.LENGTH_SHORT).show()
                // getIntent = startActivity( );
                finish()

            }
        }

               fun validateCredentialsAndRedirect(){
                val username = findViewById<EditText>(R.id.username).text.toString()
                if (username.isEmpty()){
                    Toast.makeText(this@LoginActivity, "Tente novamente", Toast.LENGTH_SHORT).show()
                    return
                }
                val password = findViewById<EditText>(R.id.password).text.toString()
                if (password.isEmpty()){
                    Toast.makeText(this@LoginActivity, "Tente novamente", Toast.LENGTH_SHORT).show()
                    return
                }

                viewModel.valido(username,password)

            }


        }
}