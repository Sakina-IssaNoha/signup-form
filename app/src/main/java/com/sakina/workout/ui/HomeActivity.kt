package com.sakina.workout.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sakina.workout.R
import com.sakina.workout.databinding.ActivityHomeBinding
import com.sakina.workout.databinding.ActivityLoginBinding

class HomeActivity : AppCompatActivity() {
    lateinit var bottom_navigation : BottomNavigationView
    lateinit var fcvhome : FragmentContainerView
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs: SharedPreferences
    lateinit var tvsignout : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_home)
        castView()
        setUpBottomNav()


//        tvsignout.setOnClickListener {
//            val editor = sharedPrefs.edit()
//            editor.putString("ACCESS_TOKEN","")
//            editor.putString("USER_ID","")
//            editor.putString("PROFILE_ID","")
//            editor.apply()
//            startActivity(Intent(this,login::class.java))
//            signOutRequest()
//
//        }
    }
    fun castView(){
        bottom_navigation=findViewById(R.id.bottom_navigation)
        fcvhome=findViewById(R.id.fcvhome)
    }
    fun setUpBottomNav(){
        bottom_navigation.setOnItemSelectedListener{ item->
            when(item.itemId){
             R.id.plan ->{
                 supportFragmentManager.beginTransaction().replace(R.id.fcvhome, PlanFragment()).commit()
                 true
             }
                R.id.track ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvhome, TrackFragment()).commit()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvhome,
                        ProfileFragment()
                    ).commit()
                    true
                }
                else->false
            }
        }
    }

    fun signOutRequest(){
        sharedPrefs.edit().clear().commit()
    }
}