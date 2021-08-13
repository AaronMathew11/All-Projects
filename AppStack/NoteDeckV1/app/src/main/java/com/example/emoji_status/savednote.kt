package com.example.emoji_status

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_savednote.*

class savednote : AppCompatActivity() {

    private lateinit var fireadapter: fireadapter
    private lateinit var notelist : ArrayList<fire>
    val db = Firebase.firestore
    val user = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savednote)

        //fireadapter= fireadapter(mutableListOf())
        //recyclerview.adapter=fireadapter
        recyclerviewsavednote.layoutManager=LinearLayoutManager(this)
        getdata()
    }

    private fun getdata() {
        db.collection("${user?.email}").get().addOnCompleteListener(object : OnCompleteListener<QuerySnapshot>
        {
            override fun onComplete(p0: Task<QuerySnapshot>) {
                if(p0.isSuccessful){
                    for(data in p0.result!!)
                    {
                        notelist.add(fire(data.get("note") as String,data.get("time") as FieldValue))
                    }
                }
            }

        })
                fireadapter=fireadapter(notelist)
                recyclerviewsavednote.adapter=fireadapter





    }

}