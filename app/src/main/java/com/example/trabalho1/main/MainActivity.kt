package com.example.trabalho1.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabalho1.PhotoActivity
import com.example.trabalho1.databinding.ActivityMainBinding
import com.example.trabalho1.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, PhotoActivity :: class.java)
            startActivity(intent)
        }
        binding.loginBtn.setOnClickListener(){
            val intent = Intent(this, LoginActivity:: class.java)
            startActivity(intent)
        }
    }
}