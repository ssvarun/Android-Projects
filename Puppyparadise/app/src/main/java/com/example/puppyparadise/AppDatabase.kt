package com.example.puppyparadise

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Customer::class,Cart::class,PetDetails::class,Category::class,Order::class,CartItems::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

     abstract fun customerdao() :CustomerDao

     companion object
     {
         @Volatile
         private var INSTANCE:AppDatabase?=null
         fun getDatabase(context: Context) :AppDatabase
         {
             val tempinstance= INSTANCE
             if(tempinstance!=null)
                 return tempinstance
             synchronized(AppDatabase::class){
                 val instance = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"app_database")
                     .allowMainThreadQueries().build()
                 INSTANCE=instance
                 return instance!!
             }
         }



     }
}