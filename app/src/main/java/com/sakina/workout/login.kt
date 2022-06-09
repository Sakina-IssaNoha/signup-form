package com.sakina.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class login : AppCompatActivity() {
    lateinit var btnlogin : Button
    lateinit var tilemail : TextInputLayout
    lateinit var tilpassword : TextInputLayout
    lateinit var etemail : TextInputEditText
    lateinit var etpassword : TextInputEditText
    lateinit var tvsignup : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        castView()

    }

    fun castView(){
        btnlogin = findViewById(R.id.btnlogin)
        tilemail = findViewById(R.id.tilemail)
        tilpassword = findViewById(R.id.tilpassword)
        etemail = findViewById(R.id.etemail2)
        etpassword = findViewById(R.id.etpassword1)
        tvsignup = findViewById(R.id.tvsignup)
        tvsignup.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }


        btnlogin.setOnClickListener { validateLogin() }
    }

    fun validateLogin(){
        tilemail.error = null
        tilpassword.error = null
        var error=false
        var email = etemail.text.toString()
        if (email.isBlank()){
            tilemail.error = "E-mail is required"
            error = true
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tilemail.error="Not a valid email address"
            error=true
        }

        var password = etpassword.text.toString()
        if (password.isBlank()){
            etpassword.error = "Password is required"
            error = true
        }
        if (!error){

        }
    }
}