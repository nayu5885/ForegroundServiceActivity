package com.example.foregroundserviceactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceIntent = Intent(this, MyForegroundService::class.java)
        startForegroundService(serviceIntent)

        val button: Button =findViewById(R.id.button)

        button.setOnClickListener {

        }
    }
}