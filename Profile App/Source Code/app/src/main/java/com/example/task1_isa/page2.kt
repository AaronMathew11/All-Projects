package com.example.task1_isa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class page2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)

        val btn3 = findViewById<Button>(R.id.btn3)
        btn3.setOnClickListener {
            val intent = Intent(this,page3::class.java)
            startActivity(intent)
        }
    }
}