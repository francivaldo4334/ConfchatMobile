package br.com.confchat.mobile.data.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
class Product(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    var name: String,
    var price: Int
) : BaseEntity()
