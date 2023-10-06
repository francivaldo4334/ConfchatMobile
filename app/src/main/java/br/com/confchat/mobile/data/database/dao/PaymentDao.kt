package br.com.confchat.mobile.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.confchat.mobile.data.database.entitys.Payment
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Query("SELECT * FROM payment")
    fun getAll(): List<Payment>
    @Query("SELECT * FROM payment WHERE id = :id  LIMIT 1")
    fun getById(id:Int): Payment
    @Query("SELECT * FROM payment WHERE order_id = :order LIMIT 1")
    fun getByOrder(order:String): Payment
    @Insert
    abstract fun insert(it: Payment)
    @Query("SELECT * FROM payment ORDER BY create_at DESC LIMIT 1")
    fun getlast():Payment
}