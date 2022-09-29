package com.sakina.workout.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sakina.workout.api.ApiClient
import com.sakina.workout.api.ApiInterface
import com.sakina.workout.databinding.ActivitySignUpBinding
import com.sakina.workout.models.RegisterRequest
import com.sakina.workout.models.RegisterResponse
import com.sakina.workout.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()

    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerRequestLiveData.observe(this, Observer { RegisterResponse->
            Toast.makeText(baseContext,RegisterResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,login::class.java))
        })
        userViewModel.registerErrorLiveData.observe(this, Observer { registerError ->
            Toast.makeText(baseContext, registerError, Toast.LENGTH_LONG).show()
        })
    }

    fun castViews() {
        binding.btnsignup.setOnClickListener {
            validateSignup()
        }

        binding.tvlogin.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

//            binding.btnsignup.setOnClickListener {
//                val intent = Intent(this,SignUp::class.java)
//                startActivity(intent)
//            }
    }

    fun validateSignup() {
        var error = false
        binding.tilfirstname.error = null
        binding.tillastname.error = null
        binding.tilemail2.error = null
        binding.tilpassword1.error = null
        binding.tilpassword2.error = null
        binding.tilPhoneNumber.error = null

        var fistname = binding.etfirstname.text.toString()
        if (fistname.isBlank()) {
            binding.tilfirstname.error = "First name is required"
        }
        var lastname = binding.etlastname.text.toString()
        if (lastname.isBlank()) {
            binding.tillastname.error = "Last name is required"
        }
        var email = binding.etemail2.text.toString()
        if (lastname.isBlank()) {
            binding.tilemail2.error = "Last name is required"
        }
        var password1 = binding.etpassword1.text.toString()
        if (password1.isBlank()) {
            binding.tilpassword1.error = "Password is required"
        }
        var password2 = binding.etpassword2.text.toString()
        if (password2.isBlank()) {
            binding.tilpassword2.error = "Password is required"
        }
        if (password1 != password2) {
            binding.tilpassword2.error = "Wrong Password"

        }
        var phoneNumber = binding.etphonenumber.text.toString()
        if (lastname.isBlank()) {
            binding.tilPhoneNumber.error = "Phone number is required"
            error = true


        }
        if (!error) {
            val registerRequest =
                RegisterRequest(fistname, lastname, password = password1, email, phoneNumber)
            userViewModel.registerUser(registerRequest)
        }
    }


}