package com.example.puppyparadise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category_table")
data class Category(
    @PrimaryKey(autoGenerate = false)
    val categoryid:Int?,
    @ColumnInfo(name = "Category name") val categoryname:String
)
