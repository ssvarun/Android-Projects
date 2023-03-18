package com.example.puppyparadise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cartitem_table")
data class CartItems(
    @PrimaryKey(autoGenerate = true)
    val cartitem: Int?,
    @ColumnInfo(name = "cartid") val cartid:Int?,
    @ColumnInfo(name = "Petid") val Petid:Int,
    @ColumnInfo(name = "Quantity") val quantity:Int

)
