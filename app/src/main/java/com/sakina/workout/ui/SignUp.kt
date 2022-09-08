package com.sakina.workout.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sakina.workout.api.ApiClient
import com.sakina.workout.api.ApiInterface
import com.sakina.workout.databinding.ActivitySignUpBinding
import com.sakina.workout.models.RegisterRequest
import com.sakina.workout.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
       castViews()

        }
        fun castViews(){
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

    fun validateSignup(){
        binding.tilfirstname.error = null
        binding.tillastname.error = null
        binding.tilemail2.error = null
        binding.tilpassword1.error = null
        binding.tilpassword2.error = null
        binding.tilPhoneNumber.error=null
        var error=false
        var fistname = binding.etfirstname.text.toString()
        if (fistname.isBlank()){
            binding.tilfirstname.error = "First name is required"
            error = true
    }
        var lastname = binding.etlastname.text.toString()
        if (lastname.isBlank()){
            binding.tillastname.error = "Last name is required"
            error = true
}
        var email = binding.etemail2.text.toString()
        if (lastname.isBlank()){
            binding.tilemail2.error = "Last name is required"
            error = true
    }
        var password1 = binding.etpassword1.text.toString()
        if (password1.isBlank()){
            binding.tilpassword1.error = "Password is required"
            error = true
}
        var password2 = binding.etpassword2.text.toString()
        if (password2.isBlank()){
            binding.tilpassword2.error = "Password is required"
            error = true
    }
       if (password1!=password2){
           binding.tilpassword2.error="Wrong Password"

        }
        var phoneNumber = binding.etphonenumber.text.toString()
        if (lastname.isBlank()){
            binding.tilPhoneNumber.error = "Phone number is required"
            error = true


        if(!error){
            val registerRequest = RegisterRequest(fistname,lastname, password = password1,email,phoneNumber)
            makeRegisterRequest(registerRequest)
            startActivity(Intent(this,login::class.java))
         }
}
     }
    fun makeRegisterRequest(requestRequest: RegisterRequest){
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.registerUser(requestRequest)

        request.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
               if (response.isSuccessful){
                   Toast.makeText(baseContext,response.body()?.message,Toast.LENGTH_LONG)

               }
                else{
                    val error = response.errorBody()?.string()
                   Toast.makeText(baseContext,error,Toast.LENGTH_LONG)
               }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })

    }
     }