package com.example.testtypicode.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoImage(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray? = null
)
// TODO https://stackoverflow.com/questions/46337519/how-insert-image-in-room-persistence-library