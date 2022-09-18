package com.example.roomdatabase

import androidx.room.*

// all are suspend func as we use courotines because these operations on database take long time
@Dao
interface StudentDao {

    // onconflict is for ignoring if the data provided is same and still adding it
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertdata(student:Student)

    //Query is used to create diffrent methods
    @Query("SELECT * FROM student_table" )
    suspend fun getalldata() : List<Student>

    // where is used to select a particular column and like is used to search a parameter to the query limit defines number of objects
    // it should select if the rollno is same ( a parameter is passed using : )
    @Query("SELECT * FROM student_table WHERE rollnocol LIKE :rollno LIMIT 1")
    suspend fun getrolldata(rollno:Int) :Student

    @Query("DELETE FROM student_table WHERE rollnocol LIKE:rollno " )
    suspend fun delete(rollno: Int)


    @Query("DELETE FROM student_table")
    suspend fun deleteall()

    @Query("Update student_table SET firstnamecol=:first_name,hobbycol=:hobby WHERE rollnocol LIKE :rollno")
    suspend fun update(first_name:String,hobby:String,rollno: Int)

}