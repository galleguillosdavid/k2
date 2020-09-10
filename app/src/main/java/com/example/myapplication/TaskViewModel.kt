package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.Task
import com.example.myapplication.database.TaskDao
import com.example.myapplication.database.TaskDatabase
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {

    private val repository: TaskRepository
    val AllTask: LiveData<List<Task>>

    init {
        val TaskDao = TaskDatabase.getDatabase(application).getTaskDao()
        repository= TaskRepository(TaskDao)
        AllTask = repository.listAllTask
    }
    fun insertTask(task:Task) = viewModelScope.launch {
        repository.insertTask(task)
    }
    fun deleteAllTask() = viewModelScope.launch{
        repository.deleteAll()
    }
    fun getOneTaskById(id:Int) :LiveData<Task>{
        return repository.getOneTaskByID(id)
    }
    fun updateTask(mTask: Task) = viewModelScope.launch {
        repository.updateTask(mTask)
    }
}