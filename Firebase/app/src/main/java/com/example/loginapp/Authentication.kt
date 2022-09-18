package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Authentication : AppCompatActivity() {
    private lateinit var  memail: EditText
    private lateinit var mpassword: EditText
    private lateinit var msignup: Button
    private lateinit var mauth: FirebaseAuth
    private lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        memail = findViewById(R.id.emailtxt)
        mpassword = findViewById(R.id.passwordtxt)
        mauth= FirebaseAuth.getInstance()
        msignup=findViewById(R.id.signupbtn)
        text=findViewById(R.id.textView)
        text.setOnClickListener {
            startActivity(Intent(this,loginactivity::class.java))

        }
        msignup.setOnClickListener{
            createUser()
        }
    }
    private fun createUser() {
        val email:String = memail.text.toString()
        val password:String = mpassword.text.toString()
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            if(!password.isEmpty())
            {
                mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    Toast.makeText(this,"Successfully registered", Toast.LENGTH_LONG).show()
                    val intent = Intent(this,loginactivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(this,"Failed registration", Toast.LENGTH_LONG).show()
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