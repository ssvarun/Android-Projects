package com.example.puppyparadise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_table")
data class
Customer(
    @PrimaryKey(autoGenerate = true)
    val customerid:Int?,
    @ColumnInfo(name="Name") val customername:String?,
    @ColumnInfo(name = "Email") val customeremail:String?,
    @ColumnInfo(name = "Password") val customerpass:String?,
    @ColumnInfo(name = "Address") val customeradress:String?,
    @ColumnInfo(name = "MobileNumber") val customernumber:Long?,
    @ColumnInfo(name="Pincode") val customerpincode:Int?
)
