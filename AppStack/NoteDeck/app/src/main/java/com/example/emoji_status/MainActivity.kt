package com.example.emoji_status

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoadapter: todoadapter
    lateinit var  mp: MediaPlayer
    lateinit var  dp: MediaPlayer

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mp=MediaPlayer.create(this,R.raw.add)
        dp= MediaPlayer.create(this,R.raw.delete)
        auth=Firebase.auth
        todoadapter= todoadapter(mutableListOf())
        recyclerview.adapter=todoadapter
        recyclerview.layoutManager=LinearLayoutManager(this)

        btnadd.setOnClickListener {
            val note= edit.text.toString()
            if(note.isNotEmpty())
            {
                val todo = todo(note)
                todoadapter.addtodo(todo)
                edit.text?.clear()
            }

            mp.start()
        }

        btndelete.setOnClickListener {
            todoadapter.deletetodo()
            dp.start()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.milogout) {
            Log.i(TAG, "Logout")
            //logout the user
            auth.signOut()
            val logoutintent= Intent(this,Login_Acitivity::class.java)
            logoutintent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutintent)
        }
        return super.onOptionsItemSelected(item)
    }

}