package br.com.confchat.mobile.data.database.entitys

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

open class BaseEntity {
    @ColumnInfo(name = "is_upload") var isUpload: Boolean = false
}