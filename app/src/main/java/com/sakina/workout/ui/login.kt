package com.sakina.workout.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sakina.workout.api.ApiClient
import com.sakina.workout.api.ApiInterface
import com.sakina.workout.databinding.ActivityLoginBinding
import com.sakina.workout.models.LoginRequest
import com.sakina.workout.models.LoginResponse
import com.sakina.workout.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
        sharedPrefs = getSharedPreferences("WORKOUT_PREFS", MODE_PRIVATE)

    }

    override fun onResume(){
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse ->
            Toast.makeText(baseContext, loginResponse?.message,Toast.LENGTH_LONG).show()
            saveLoginDetails(loginResponse!!)
            startActivity(Intent(baseContext,HomeActivity::class.java))
        })
        userViewModel.errorLiveData.observe(this, Observer { errorMessage->
            Toast.makeText(baseContext,errorMessage,Toast.LENGTH_LONG).show()

        })
    }

    fun castView(){
        binding.btnlogin.setOnClickListener {
            validateLogin()
        }
        binding.tvsignup.setOnClickListener { startActivity(Intent(this, SignUp::class.java)) }

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
            val loginRequest = LoginRequest(email,password)
            binding.pbLogin1.visibility = View.VISIBLE
            userViewModel.loginUser(loginRequest)
        }
    }



    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)

    }
}