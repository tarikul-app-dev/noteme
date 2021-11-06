package com.rokomari.noteme.task.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "noteMe")
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    val create_at: String,
    val deadline: String,
    val status: String,
    val email: String,
    val phone: String,
    val url: String
)