package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentdao() :StudentDao

    // We want only a singe instance of database hence we create a singleton companion object and create a database if it is
    // not yet created by using the room databasebuilder ,if already present return that instance
    companion object{
        // should add volatile it is a huge concept of when to use it just put it
        @Volatile
        private var INSTANCE:AppDatabase?=null
        fun getDatabase(context:Context) :AppDatabase
        {
            val tempinstance= INSTANCE
            if(tempinstance!=null)
                return tempinstance
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"app_database").build()
                INSTANCE=instance
                return instance
            }
        }
    }
}