package com.sakina.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUp : AppCompatActivity() {
    lateinit var etfirstname : TextInputEditText
    lateinit var etlastname : TextInputEditText
    lateinit var etemail2 : TextInputEditText
    lateinit var etpassword1 : TextInputEditText
    lateinit var etpassword2 : TextInputEditText
    lateinit var tilfirstname : TextInputLayout
    lateinit var tillastname : TextInputLayout
    lateinit var tilemail2 : TextInputLayout
    lateinit var tilpassword1 : TextInputLayout
    lateinit var tilpassword2 : TextInputLayout
    lateinit var btnsignup : Button
    lateinit var tvlogin : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        etfirstname = findViewById(R.id.etfirstname)
        etlastname = findViewById(R.id.etlastname)
        etemail2 = findViewById(R.id.etemail2)
        etpassword1 = findViewById(R.id.etpassword1)
        etpassword2 = findViewById(R.id.etpassword2)
        tilfirstname = findViewById(R.id.tilfirstname)
        tillastname = findViewById(R.id.tillastname)
        tilemail2 = findViewById(R.id.tilemail2)
        tilpassword1 = findViewById(R.id.tilpassword1)
        tilpassword2 = findViewById(R.id.tilpassword2)
        btnsignup = findViewById(R.id.btnsignup)
        btnsignup.setOnClickListener {
            validateSignup()
        }
        tvlogin = findViewById(R.id.tvlogin)

        tvlogin.setOnClickListener {
            val intent = Intent(this,login::class.java)
            startActivity(intent)
        }
    }

    fun validateSignup(){
        tilfirstname.error = null
        tillastname.error = null
        tilemail2.error = null
        tilpassword1.error = null
        tilpassword2.error = null
        var error=false
        var fistname = etfirstname.text.toString()
        if (fistname.isBlank()){
            tilfirstname.error = "First name is required"
            error = true
    }
        var lastname = etlastname.text.toString()
        if (lastname.isBlank()){
            tillastname.error = "Last name is required"
            error = true
}
        var email = etemail2.text.toString()
        if (lastname.isBlank()){
            tilemail2.error = "Last name is required"
            error = true
    }
        var password1 = etpassword1.text.toString()
        if (password1.isBlank()){
            tilpassword1.error = "Password is required"
            error = true
}
        var password2 = etpassword2.text.toString()
        if (password2.isBlank()){
            tilpassword2.error = "Password is required"
            error = true
    }
        if(!error){

        }
         }
}