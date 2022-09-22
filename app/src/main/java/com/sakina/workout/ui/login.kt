package com.sakina.workout.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.sakina.workout.api.ApiClient
import com.sakina.workout.api.ApiInterface
import com.sakina.workout.databinding.ActivityLoginBinding
import com.sakina.workout.models.LoginRequest
import com.sakina.workout.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
        sharedPrefs = getSharedPreferences("WORKOUT_PREFS", MODE_PRIVATE)

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
            makeLoginRequest(loginRequest)

        }
    }

    fun makeLoginRequest(LoginRequest: LoginRequest){
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.LoginUser(LoginRequest)

        request.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                binding.pbLogin1.visibility= View.GONE
                if (response.isSuccessful){
                    val loginResponse = response.body()
                    Toast.makeText(baseContext, loginResponse?.message,Toast.LENGTH_LONG).show()
                    saveLoginDetails(loginResponse!!)
                    startActivity(Intent(baseContext,HomeActivity::class.java))
                }
                else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.pbLogin1.visibility= View.GONE
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)

    }
}