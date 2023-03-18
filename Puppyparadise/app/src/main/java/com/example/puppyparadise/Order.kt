package com.example.puppyparadise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Order_table")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val Orderid:Int?,
    @ColumnInfo(name = "customerid") val customerid:Int,
    @ColumnInfo(name = "cartid") val cartid:Int,
    @ColumnInfo(name = "Delivery by ") val deliverydate:String,
    @ColumnInfo(name = "Order Date") val orderdate:String,
    @ColumnInfo(name = "Amount") val orderamount:Int
    )
