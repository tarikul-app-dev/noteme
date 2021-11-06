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
        suspend fun createTask(noteMe: TaskModel) : Long

        @Query("SELECT * FROM noteMe ")
        fun getAllData():LiveData<List<TaskModel>>

//        @Query("DELETE FROM cart WHERE item_id = :itemId")
//        fun deleteBYItemId(itemId: Int?)
//
//        @Query("SELECT EXISTS(SELECT * FROM cart WHERE item_id = :itemId And item_type = :itemType)")
//        fun isExistSameItem(itemId: Int?,itemType:String): Boolean
//
//
//        @Query("DELETE FROM cart")
//        fun clearAllFromCartItem()
//
//        @Query("UPDATE cart SET quantity= :qty ,b_discount_price= :bookDiscountPrice,b_regular_price= :bookRegularPrice   WHERE item_id = :itemId")
//        fun updateResult(qty: Int, itemId: Int,bookDiscountPrice : Double,bookRegularPrice : Double)


}