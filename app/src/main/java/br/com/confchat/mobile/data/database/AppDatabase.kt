package br.com.confchat.mobile.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.confchat.mobile.data.database.dao.PaymentDao
import br.com.confchat.mobile.data.database.dao.ProductDao
import br.com.confchat.mobile.data.database.entitys.Payment
import br.com.confchat.mobile.data.database.entitys.Product

@Database(
    entities = [
        Payment::class,
        Product::class
    ],
    version = 1
)
@TypeConverters(Convertes::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun paymentDao(): PaymentDao
    abstract fun productDao(): ProductDao
    companion object{
        private lateinit var INSTANCE: AppDatabase
        fun getInstance(context:Context) : AppDatabase{
            if(!::INSTANCE.isInitialized) {
                synchronized(AppDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            "confchat_db_mobile"
                        )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
        private val MIGRATION_1_2:Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
            }

        }
    }
}