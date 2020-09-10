package com.example.myapplication

import androidx.lifecycle.LiveData
import com.example.myapplication.database.Task
import com.example.myapplication.database.TaskDao

class TaskRepository(private val mTaskDao: TaskDao) {

    val listAllTask: LiveData<List<Task>> = mTaskDao.getAllTaskFromDb()

    suspend fun insertTask(mTask: Task) {
        mTaskDao.insertOneTask(mTask)
    }

    suspend fun deleteAll(){
        mTaskDao.deleteAllTask()
    }

    fun getOneTaskByID(id:Int): LiveData<Task>{
        return mTaskDao.getOneTaskByID(id)
    }
    //Este metodo realiza un update
    suspend fun updateTask(mTask: Task){
        mTaskDao.updateOneTask(mTask)
    }


}