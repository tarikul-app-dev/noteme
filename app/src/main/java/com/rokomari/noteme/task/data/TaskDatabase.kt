package com.rokomari.noteme.task.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rokomari.noteme.task.model.TaskModel


@Database(entities = [TaskModel::class], version = 1,exportSchema = true)
abstract  class TaskDatabase : RoomDatabase(){
    // SongDao is a class annotated with @Dao.
    abstract fun taskDao():TaskDao


    companion object {
        var INSTANCE: TaskDatabase? = null

        fun getAppDataBase(context: Context): TaskDatabase? {
            if (INSTANCE == null){
                synchronized(TaskDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, TaskDatabase::class.java, "taskDb")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}