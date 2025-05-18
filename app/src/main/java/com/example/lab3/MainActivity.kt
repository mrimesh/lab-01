package com.example.lab3

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    
    private val CAMERA_PERMISSION_CODE = 100
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        val editTextName: EditText = findViewById(R.id.editTextName)
        val btnSendData: Button = findViewById(R.id.btnSendData)
        btnSendData.setOnClickListener {
            val name = editTextName.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
        }
        
        val btnCustomAction: Button = findViewById(R.id.btnCustomAction)
        btnCustomAction.setOnClickListener {
            val intent = Intent("com.example.MY_CUSTOM_ACTION")
            // We can also pass data with the implicit intent
            val name = editTextName.text.toString()
            if (name.isNotEmpty()) {
                intent.putExtra("USER_NAME", name)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No app can handle this action", Toast.LENGTH_SHORT).show()
            }
        }
        
        val btnOpenWeb: Button = findViewById(R.id.btnOpenWeb)
        btnOpenWeb.setOnClickListener {
            try {
                val webpage = Uri.parse("https://www.google.com")
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Cannot open browser: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        val btnDialPhone: Button = findViewById(R.id.btnDialPhone)
        btnDialPhone.setOnClickListener {
            try {
                val phoneUri = Uri.parse("tel:0786718562")
                val intent = Intent(Intent.ACTION_DIAL, phoneUri)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Cannot open phone dialer: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }


        val btnCapturePhoto: Button = findViewById(R.id.btnCapturePhoto)
        btnCapturePhoto.setOnClickListener {
            try {
                // Simple approach without saving the photo
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivity(takePictureIntent)
            } catch (e: Exception) {
                Toast.makeText(this, "Cannot open camera: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}