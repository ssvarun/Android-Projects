package com.example.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "firstnamecol") val firstname:String?,
    @ColumnInfo(name = "hobbycol") val hobby:String?,
    @ColumnInfo(name = "rollnocol") val rollno:Int?,
)
