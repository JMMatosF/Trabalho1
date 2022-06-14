package com.example.trabalho1

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        val buttonPhoto = findViewById<Button>(R.id.button)

        buttonPhoto.setOnClickListener(){
            val intent = Intent(this, PhotoActivity :: class.java)
            startActivity(intent)
        }
        val login = findViewById<Button>(R.id.loginbtn)
        login.setOnClickListener(){
            val intent = Intent(this, LoginActivity:: class.java)
            startActivity(intent)
        }


    }
}