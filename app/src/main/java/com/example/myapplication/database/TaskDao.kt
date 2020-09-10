package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.database.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneTask(mTask: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultipleTask(mTask: List<Task>)

    @Update
    suspend fun updateOneTask(mTask: Task)

    @Delete
    fun deleteOneTask(mTask: Task)

    //esta funcion esta envuelta en live data, y devolvera un listado envuelto en el
    @Query ("SELECT * FROM table_task")
    fun getAllTaskFromDb(): LiveData<List<Task>>

    //TRAE LOS DATOS POR ID
    @Query ("SELECT * FROM table_task WHERE id=:mId")
    fun getOneTaskByID(mId:Int): LiveData<Task>

    @Query("DELETE FROM table_task")
    suspend fun deleteAllTask()

}