package com.example.task1_isa
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            val intent = Intent(this, page2::class.java)
            startActivity(intent)
        }
        val edemail =findViewById<EditText>(R.id.editTextTextEmailAddress)
        val edpass = findViewById<EditText>(R.id.editTextTextPassword)
        val btn2= findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener {
            if(edemail.text.toString().isEmpty() || edpass.text.toString().isEmpty())
            {
                Toast.makeText(applicationContext,"Please fill in all the required details",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(applicationContext,"Welcome",Toast.LENGTH_LONG).show()
                val intent= Intent(this,To_be_designed::class.java)
                startActivity(intent)
            }
        }
    }

    }
