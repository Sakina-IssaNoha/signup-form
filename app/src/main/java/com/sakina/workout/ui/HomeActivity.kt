package com.sakina.workout.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sakina.workout.R

class HomeActivity : AppCompatActivity() {
    lateinit var bottom_navigation : BottomNavigationView
    lateinit var fcvhome : FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        castView()
        setUpBottomNav()
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
}