package com.example.task1_isa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class Profile1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile1)

        val intent= this.intent

        val edemail=findViewById<TextView>(R.id.mail)
        val college =findViewById<TextView>(R.id.college)
        val branch=findViewById<TextView>(R.id.branch)

        val edbranch=intent.getStringExtra("branch")
        val edcollege=intent.getStringExtra("college")
        val email=intent.getStringExtra("email")

        branch.text = edbranch
        college.text=edcollege
        edemail.text=email
        val ibtn2=findViewById<ImageButton>(R.id.ibtn2)
        ibtn2.setOnClickListener {
                val intent=   Intent(this,To_be_designed::class.java)
            startActivity(intent)
        }

    }
}