package com.sakina.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sakina.workout.databinding.ActivityHomeBinding
import com.sakina.workout.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()

    }

    fun castView(){
        binding.btnlogin.setOnClickListener {
            validateLogin()
            startActivity(Intent(this,HomeActivity::class.java))
        }
        binding.tvsignup.setOnClickListener { startActivity(Intent(this,SignUp::class.java)) }

    }

    fun validateLogin(){
        binding.tilemail.error = null
        binding.tilpassword.error = null
        var error=false
        var email = binding.etemail2.text.toString()
        if (email.isBlank()){
            binding.tilemail.error = "E-mail is required"
            error = true
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilemail.error="Not a valid email address"
            error=true
        }

        var password = binding.etpassword1.text.toString()
        if (password.isBlank()){
            binding.tilpassword.error = "Password is required"
            error = true
        }
        if (!error){

        }
    }
}