package com.firebase_auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btn_registernow.setOnClickListener{
            when{
                TextUtils.isEmpty(tv_email.text.toString().trim{ it <= ' '}) ->{
                    Toast.makeText(this,"Please Enter your Email",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(tv_password.text.toString().trim{ it <= ' '})->{
                    Toast.makeText(this,"Please enter your Password",Toast.LENGTH_SHORT).show()
                }
                else ->{
                    val email= tv_email.text.toString().trim{ it <= ' '}
                    val pass= tv_password.text.toString().trim{ it <= ' '}

                    // here is the important part..... create an instance for Firebase
                    FirebaseAuth.getInstance().createUser
                }
            }
        }
    }
}