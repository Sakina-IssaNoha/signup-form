package com.sakina.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class login : AppCompatActivity() {
    lateinit var btnlogin : Button
    lateinit var tilemail : TextInputLayout
    lateinit var tilpassword : TextInputLayout
    lateinit var etemail : TextInputEditText
    lateinit var etpassword : TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnlogin = findViewById(R.id.btnlogin)
        tilemail = findViewById(R.id.tilemail)
        tilpassword = findViewById(R.id.tilpassword)
        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)

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
        var password = etpassword.text.toString()
        if (password.isBlank()){
            etpassword.error = "Password is required"
            error = true
        }
        if (!error){

        }
    }
}