package com.example.awt10

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.awt10.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = Home_fragment()
        val codeFragment = Code_fragment()
        val schedulingFragment = Scheduling_fragment()
        val aboutFragment = About_fragment()

        makeCurrentfragment(homeFragment)

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->makeCurrentfragment(homeFragment)
                R.id.code ->makeCurrentfragment(codeFragment)
                R.id.scheduling-> makeCurrentfragment(schedulingFragment)
                R.id.about->makeCurrentfragment(aboutFragment)

            }
            true
        }
    }

    private fun makeCurrentfragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.wrapper,fragment)
            commit()
        }

    fun process_scheduling_clicked(view: android.view.View){
        supportFragmentManager.beginTransaction().apply {
            val processSchedulingInfo = Process_scheduling_info()
            replace(R.id.wrapper,processSchedulingInfo)
            commit()
        }
    }

    fun ps_learnmore(view: android.view.View) {
        gotourl("https://www.tutorialspoint.com/operating_system/os_process_scheduling.htm")
    }

    private fun gotourl(s: String) {
        val uri:Uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW,uri))
    }


}