package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
     private lateinit var auth:Button
     private lateinit var realtime:Button
     private lateinit var firestorebtn:Button
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth=findViewById(R.id.authentication)
        auth.setOnClickListener{
            startActivity(Intent(this,Authentication::class.java))
        }
        realtime=findViewById(R.id.realtime)
        realtime.setOnClickListener {
            startActivity(Intent(this,realtimemain::class.java))
        }
         firestorebtn=findViewById(R.id.firestoreactivity)
         firestorebtn.setOnClickListener {
             startActivity(Intent(this,firestore::class.java))
         }
    }

}