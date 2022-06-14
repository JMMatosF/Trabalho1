package com.example.trabalho1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            setup()

        }

        private fun setup(){
            findViewById<Button>(R.id.loginbtn).setOnClickListener {
                validateCredentialsAndRedirect()
            }
        }

        private fun validateCredentialsAndRedirect(){
            if (valido()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this@LoginActivity, "Bem-vindo", Toast.LENGTH_SHORT).show()
                // getIntent = startActivity( );
                finish()
            }
        }

        private fun valido(): Boolean {
            val username = findViewById<EditText>(R.id.username).text.toString()
            if (username.isEmpty()){
                Toast.makeText(this@LoginActivity, "Tente novamente", Toast.LENGTH_SHORT).show()
                return false
            }
            val password = findViewById<EditText>(R.id.password).text.toString()
            if (password.isEmpty()){
                Toast.makeText(this@LoginActivity, "Tente novamente", Toast.LENGTH_SHORT).show()
                return false
            }

            val isValid = username == password
            if (!isValid){
                Toast.makeText(this@LoginActivity, "Tente novamente", Toast.LENGTH_SHORT).show()
            }
            return isValid
        }
}