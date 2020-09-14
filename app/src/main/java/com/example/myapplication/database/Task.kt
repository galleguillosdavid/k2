package com.example.myapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "table_task")
data class Task (
                @PrimaryKey(autoGenerate = true)
                @NotNull
                val id:Int=0,
                val NameOfItem: String,
                val Cuantity: Int=0,
                val Price: Int=0,
                val Subtotal: Int=0,
                val Total: Int=0
                )

