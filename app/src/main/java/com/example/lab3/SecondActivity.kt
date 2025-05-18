package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.second_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // Check how the activity was launched
        val action = intent.action
        val launchInfo = if (action == "com.example.MY_CUSTOM_ACTION") {
            "Launched via Custom Action"
        } else {
            "Launched via Explicit Intent"
        }
        
        // Create a TextView to display launch info (or update existing one)
        val textViewLaunchInfo: TextView = findViewById(R.id.textView3)
        textViewLaunchInfo.text = "Second Activity\n$launchInfo"
        
        // Retrieve the name from the intent
        val name = intent.getStringExtra("USER_NAME") ?: "Guest"
        val textViewGreeting: TextView = findViewById(R.id.textViewGreeting)
        textViewGreeting.text = "Hello, $name!"


        
        val backButton: Button = findViewById(R.id.button2)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}