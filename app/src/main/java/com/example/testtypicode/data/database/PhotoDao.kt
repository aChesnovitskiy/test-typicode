package com.example.testtypicode.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotoImages(photoImages: List<PhotoImage>)

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getPhotoImageById(id: Int): Single<PhotoImage>
}