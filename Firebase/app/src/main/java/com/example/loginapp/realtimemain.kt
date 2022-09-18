package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class realtimemain : AppCompatActivity() {
    private lateinit var  memail: EditText
    private lateinit var mname: EditText
    private lateinit var mpass: EditText
    private lateinit var msavedata: Button
    private lateinit var mshowdata: Button
//    private lateinit var usermap:HashMap<String,String>
    private  var db:FirebaseDatabase= FirebaseDatabase.getInstance()
    private  var root:DatabaseReference=db.getReference().child("Users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realtimemain)
        memail = findViewById(R.id.realemail)
        mpass = findViewById(R.id.realpass)
        mname=findViewById(R.id.realname)
        msavedata=findViewById(R.id.savedata)
        mshowdata=findViewById(R.id.showdata)
        mshowdata.setOnClickListener {
            startActivity(Intent(this,Displaydata::class.java))
        }
        msavedata.setOnClickListener {
            val email:String = memail.text.toString()
            val password:String = mpass.text.toString()
            val name:String = mname.text.toString()
            var usermap:HashMap<String,String> = HashMap()
            usermap.put("Email",email)
            usermap.put("Password",password)
            root.child(name).setValue(usermap).addOnCompleteListener{
                Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show()
            }

        }
        val update=findViewById<Button>(R.id.update)
        update.setOnClickListener {
            val memail:String = memail.text.toString()
            val mpassword:String = mpass.text.toString()
            val mname:String = mname.text.toString()
            val updatemap= mapOf<String,String>(
                "Email" to memail,
                "Password" to mpassword
            )
            root.child(mname).updateChildren(updatemap)
        }
        val delete:Button=findViewById(R.id.delete)
        delete.setOnClickListener {
            val dname:String = mname.text.toString()
            root.child(dname).removeValue()
        }

    }
}