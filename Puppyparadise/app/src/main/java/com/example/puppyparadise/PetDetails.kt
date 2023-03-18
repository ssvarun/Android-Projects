package com.example.puppyparadise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Petdetails_table")
data class PetDetails(
    @PrimaryKey(autoGenerate = false)
    val Petid:Int?,
    @ColumnInfo(name = "categoryid") val categoryid:Int,
    @ColumnInfo(name = "Image") val petimage:String,
    @ColumnInfo(name = "Rate") val petrate:Double,
    @ColumnInfo(name = "Food") val petfood:String,
    @ColumnInfo(name="Breed") val petbreed:String
)
