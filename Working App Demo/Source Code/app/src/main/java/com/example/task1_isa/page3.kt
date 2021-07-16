package com.example.task1_isa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class page3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val ed1=findViewById<EditText>(R.id.e1)
        val ed2 = findViewById<EditText>(R.id.e2)
        val ed3 = findViewById<EditText>(R.id.e3)
        btn4.setOnClickListener {
            if(ed1.text.toString().isEmpty() || ed2.text.toString().isEmpty() || ed3.text.toString().isEmpty())
            {
                Toast.makeText(applicationContext, "Kindly fill all required information", Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(applicationContext, "Thank You for your coorporation. Your informations is being processed", Toast.LENGTH_LONG).show()
                val intent = Intent(this,To_be_designed::class.java)
                intent.putExtra("college",ed2.text.toString())
                intent.putExtra("branch",ed3.text.toString())
                startActivity(intent)
            }

        }
    }
}