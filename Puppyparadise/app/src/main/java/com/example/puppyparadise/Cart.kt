package com.example.puppyparadise


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cart_table")
data class Cart(
    @PrimaryKey(autoGenerate = false)
    val cartid:Int?,
    @ColumnInfo(name="customerid") val customerid:Int
)
