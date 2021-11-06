package com.rokomari.noteme.task.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rokomari.noteme.task.model.TaskModel

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(noteMe: TaskModel): Long

    @Query("SELECT * FROM noteMe ")
    fun getAllData(): LiveData<List<TaskModel>>

    @Query("DELETE FROM noteMe WHERE id = :itemId")
    fun deleteBYItemId(itemId: Int?):Int


    //        @Query("DELETE FROM cart")
//        fun clearAllFromCartItem()
//
    @Query("UPDATE noteMe SET name= :name ,description= :description,create_at= :create_at,deadline= :deadline,status= :status,email= :email,phone= :phone,url= :url  WHERE id = :id")
    fun updateTask(
        id: String,
        name: String,
        description: String,
        create_at: String,
        deadline: String,
        status: String,
        email: String,
        phone: String,
        url: String
    ): Int


}