package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class getstarted : AppCompatActivity() {
    private lateinit var msignout:Button
    private lateinit var mauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getstarted)
        msignout = findViewById(R.id.getstartedbtn)
        mauth= FirebaseAuth.getInstance()
        msignout.setOnClickListener {
            mauth.signOut()
            startActivity(Intent(this,loginactivity::class.java))
        }
    }
}