package br.com.confchat.mobile.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.confchat.mobile.data.database.entitys.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): Flow<List<Product>>
    @Query("SELECT * FROM product WHERE id = :id  LIMIT 1")
    fun getById(id:Int): Product
    @Insert
    abstract fun insert(it: Product)
}