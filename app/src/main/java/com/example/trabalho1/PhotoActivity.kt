package com.example.trabalho1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ActionMenuView
import android.widget.Button
import android.widget.ImageView

class PhotoActivity : AppCompatActivity() {

    private val REQUEST_CODE = 2000

    private lateinit var camara : ImageView
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        camara = findViewById(R.id.camara)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null ){
            camara.setImageBitmap(data.extras!!.get("data") as Bitmap)
        }
    }

}