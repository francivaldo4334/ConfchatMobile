package br.com.confchat.mobile.data.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "payment")
class Payment (
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "client_name") val clientName:String,
    @ColumnInfo(name = "create_at") val createAt: Date,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "order_id") val order:String,
) : BaseEntity() {

}
fun Payment.getReference():String{
    return "sale-${this.id}"
}