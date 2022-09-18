package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
     lateinit var binding:ActivityMainBinding
     lateinit var appdb:AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appdb= AppDatabase.getDatabase(this)
        binding.save.setOnClickListener {
            writedata()
        }
        binding.read.setOnClickListener {
            readdata()
        }
        binding.deleteall.setOnClickListener {
            GlobalScope.launch {
                appdb.studentdao().deleteall()
            }
            Toast.makeText(this,"All Data is deleted Successfully",Toast.LENGTH_SHORT).show()
        }
        binding.update.setOnClickListener {
            updatedata()
        }
        binding.deleteroll.setOnClickListener {
            deleteparticular()
        }
    }

    private fun deleteparticular() {
        val providedroll=binding.provideroll.text.toString()
        if(providedroll.isNotEmpty()){
            GlobalScope.launch {
                appdb.studentdao().delete(providedroll.toInt())
            }
        }
        else
        {
            Toast.makeText(this,"Enter roll no",Toast.LENGTH_SHORT).show()
        }
    }

    private fun updatedata() {
        val firstname = binding.firsttxt.text.toString()
        val hobby = binding.hobby.text.toString()
        val rollno = binding.rollnumber.text.toString()
        if (firstname.isNotEmpty() && hobby.isNotEmpty() && rollno.isNotEmpty()) {
            GlobalScope.launch(Dispatchers.IO) {
                appdb.studentdao().update(firstname,hobby,rollno.toInt())
            }
            binding.firsttxt.text.clear()
            binding.hobby.text.clear()
            binding.rollnumber.text.clear()
            Toast.makeText(this,"Updated Successfully",Toast.LENGTH_SHORT).show()

        }
        else
        {
            Toast.makeText(this,"Enter all the fields",Toast.LENGTH_SHORT).show()
        }
    }

    private fun readdata() {
        lateinit var student:Student
        val providedroll=binding.provideroll.text.toString()
        if(providedroll.isNotEmpty()){
        GlobalScope.launch {
            student = appdb.studentdao().getrolldata(providedroll.toInt())
            displadata(student)
        }

        }
        else
        {
            Toast.makeText(this,"Enter roll no",Toast.LENGTH_SHORT).show()
        }
    }

   suspend private fun displadata(student: Student) {
        withContext(Dispatchers.Main)
        {
            val firstname=binding.displayname
            val hobby=binding.displayhobby
            val rollno=binding.displayroll

            firstname.text=student.firstname
            hobby.text=student.hobby
            rollno.text=student.rollno.toString()
        }
    }

    private fun writedata() {
        val firstname = binding.firsttxt.text.toString()
        val hobby = binding.hobby.text.toString()
        val rollno = binding.rollnumber.text.toString()
        if (firstname.isNotEmpty() && hobby.isNotEmpty() && rollno.isNotEmpty()) {
            GlobalScope.launch(Dispatchers.IO) {
                val student =
                    Student(null, firstname, hobby, rollno.toInt())
                appdb.studentdao().insertdata(student)
            }
            binding.firsttxt.text.clear()
            binding.hobby.text.clear()
            binding.rollnumber.text.clear()
            Toast.makeText(this,"Saved Successfully",Toast.LENGTH_SHORT).show()

        }
        else
        {
            Toast.makeText(this,"Enter all the fields",Toast.LENGTH_SHORT).show()
        }
    }
}