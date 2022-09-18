package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Displaydata : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var modelAdapter: ModelAdapter

    private lateinit var modellist:ArrayList<Model>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displaydata)
        recyclerView=findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(this)
        modellist= ArrayList<Model>()
        modelAdapter= ModelAdapter(modellist)
        recyclerView.adapter=modelAdapter
        getuserdata()

    }

    private fun getuserdata() {
        var db:FirebaseDatabase= FirebaseDatabase.getInstance()
        var root:DatabaseReference=db.getReference().child("Users")

        root.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for (datasnapshot in snapshot.children)
                    {
                        val model=datasnapshot.getValue(Model::class.java)
                        modellist.add(model!!) // !! to avoid null value

                    }
                    modelAdapter.notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}