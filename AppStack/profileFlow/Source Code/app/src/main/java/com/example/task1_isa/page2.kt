package com.example.task1_isa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class page2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)

        val btn3 = findViewById<Button>(R.id.btn3)
        val ed1 = findViewById<EditText>(R.id.ed1)
        val ed2 = findViewById<EditText>(R.id.ed2)
        val ed3 = findViewById<EditText>(R.id.ed3)
        //val layout1=layoutInflater.inflate(R.layout.customt,ll_wrapper)
        btn3.setOnClickListener {
            if (ed1.text.toString().isEmpty() || ed2.text.toString()
                    .isEmpty() || ed3.text.toString().isEmpty()) {
               Toast.makeText(applicationContext,"Please fill in the required details",Toast.LENGTH_LONG).show()
            } else {
                var email=ed1.text.toString()
                val intent = Intent(this, page3::class.java)
                intent.putExtra("email",email)
                startActivity(intent)
            }

        }
    }
}