package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginactivity : AppCompatActivity() {
    private lateinit var  memail: EditText
    private lateinit var mpassword: EditText
    private lateinit var msignup: Button
    private lateinit var mauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)
        memail = findViewById(R.id.emailtxt2)
        mpassword = findViewById(R.id.passwordtxt2)
        mauth= FirebaseAuth.getInstance()
        msignup=findViewById(R.id.loginbtn)
        msignup.setOnClickListener{
            loginUser()
        }
    }
    private fun loginUser() {
        val email:String = memail.text.toString()
        val password:String = mpassword.text.toString()
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            if(!password.isEmpty())
            {
               mauth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                   Toast.makeText(this,"Successfully login", Toast.LENGTH_LONG).show()
                   startActivity(Intent(this,getstarted::class.java))
               }.addOnFailureListener {
                   Toast.makeText(this,"Failed Login", Toast.LENGTH_LONG).show()
               }

            }
            else
            {
                mpassword.setError("Dont leave the fields blank")
            }
        }
        else if(email.isEmpty())
        {
            memail.setError("Dont leave the fields blank")
        }
        else
        {
            memail.setError("Enter a valid email adress")
        }
    }
}