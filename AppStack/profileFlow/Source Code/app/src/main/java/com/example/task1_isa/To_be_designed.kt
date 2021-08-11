package com.example.task1_isa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class To_be_designed : AppCompatActivity() {

    private lateinit var currentFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_be_designed)

        // had to remove this part as it was not allowing the application to run

        //  val ibtn = findViewById<ImageButton>(R.id.ibtn)
        //  ibtn.setOnClickListener {
        //      val intent = Intent(this, Profile1::class.java)
        //      startActivity(intent)
        //  }

        val homeFragment = HomeFragment()
        val infoFragment = InfoFragment()
        val addFragment = AddFragment()
        val profileFragment = ProfileFragment()
        val renewFragment = RenewFragment()

        makecurrentfragment(homeFragment)
        val bottomnav=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomnav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> makecurrentfragment(homeFragment)
                R.id.renewFragment -> makecurrentfragment(renewFragment)
                R.id.addFragment -> makecurrentfragment(addFragment)
                R.id.infoFragment -> makecurrentfragment(infoFragment)
                R.id.profileFragment -> makecurrentfragment(profileFragment)
            }
            true
        }

    }
    private fun makecurrentfragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,fragment)
            commit()
        }
}