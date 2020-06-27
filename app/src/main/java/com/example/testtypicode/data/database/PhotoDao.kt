package com.example.testtypicode.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photos: List<PhotoItem>)

    @Query("SELECT * FROM photos WHERE albumId = :albumId")
    fun getPhotosByAlbumId(albumId: Int): Observable<List<PhotoItem>>
}