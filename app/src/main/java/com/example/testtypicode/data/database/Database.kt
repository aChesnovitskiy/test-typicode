package com.example.testtypicode.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

//abstract class Database: RoomDatabase() {
//    abstract val photoDao: PhotoDao
//
//    companion object {
//        private const val DB_NAME = "Database.db"
//        private val LOCK = Any() // Block key
//
//        private var instance: Database? = null
//
//        fun getInstance(context: Context): Database? {
//            if (instance == null) {
//                synchronized(LOCK) {
//                    instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
//                }
//            }
//            return instance
//        }
//    }
//}