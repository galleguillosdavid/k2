package com.example.myapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "table_task")
data class Task (
                @PrimaryKey(autoGenerate = true)
                @NotNull
                val id:Int=0,
                val task: String,
                //val completeTask: Boolean,
                val NameOfItem: String,
                val Price: Int=0,
                val Subtotal: Int=0,
                val total: Int=0
                )

