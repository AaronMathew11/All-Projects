 package com.example.drawingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    fun redclicked(view: android.view.View) {}
    fun blueclicked(view: android.view.View) {}
    fun greenclicked(view: android.view.View) {}
    fun whiteclicked(view: android.view.View) {}
    fun yellowclicked(view: android.view.View) {}
    fun delete(view: android.view.View) {}
}