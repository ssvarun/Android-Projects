package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class firestore : AppCompatActivity() {
    private lateinit var  memail: EditText
    private lateinit var mname: EditText
    private lateinit var mgender: EditText
    private lateinit var mregister: Button
    private lateinit var mreadcloud:Button
    private lateinit var mgenderswitch: Switch
    private lateinit var db:FirebaseFirestore
    private var check:String="female"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firestore)
        db= FirebaseFirestore.getInstance()
        memail = findViewById(R.id.cloudemail)
        mgender = findViewById(R.id.gender)
        mname=findViewById(R.id.cloudfirstname)
        mregister=findViewById(R.id.register)
        mgenderswitch=findViewById(R.id.maleswitch)
        mreadcloud=findViewById(R.id.readcategory)
        mregister.setOnClickListener {
            val email:String = memail.text.toString()
            val gender:String = mgender.text.toString()
            val name:String = mname.text.toString()
            var user:HashMap<String,String> = HashMap()
            user.put("Name",name)
            user.put("Gender",gender)
            user.put("email",email)
            db.collection("users").add(user).addOnSuccessListener {
                Toast.makeText(this,"Successfully added",Toast.LENGTH_SHORT)
            }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed to add check internet connection",Toast.LENGTH_SHORT)

                }
        }

       mgenderswitch.setOnClickListener {
           if(mgenderswitch.isChecked)
               check="Male"
           else
               check="female"
       }
        mreadcloud.setOnClickListener {
            db.collection("users").whereEqualTo("Gender",check).get().addOnSuccessListener { result->
                for (document in result)
                {
                    Log.d("Firestore data",document.id+" => "+document.getData())
                }
            }
                .addOnFailureListener {
                    Log.d("Firestore data","Failed to read")
                }

        }
    }
}