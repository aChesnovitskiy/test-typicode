package com.example.testtypicode.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testtypicode.data.pojo.Photo
import io.reactivex.Maybe
import io.reactivex.Single

//@Dao
//interface PhotoDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertCharacters(character: List<Photo>)
//
//    @Query("SELECT id, houseId as house, name, titles, aliases FROM characters WHERE houseId = :name")
//    fun findCharactersByHouseName(name: String): Maybe<List<CharacterItem>>
//
//    @Query("SELECT characters.id id, characters.name name, houses.words words, characters.born born, " +
//            "characters.died died, characters.titles titles, characters.aliases aliases, characters.houseId house, " +
//            "characters.father father, characters.mother mother " +
//            "FROM characters, houses WHERE characters.id = :id AND houses.id = characters.houseId")
//    fun findCharacterFullById(id: String): Maybe<CharacterFull>
//
//    @Query("SELECT id, name, houseId as house FROM characters WHERE id = :id")
//    fun findRelativeCharacterById(id: String): Single<RelativeCharacter>
//
//    @Query("SELECT COUNT(*) FROM houses")
//    fun getCountEntity(): Single<Int>
//}