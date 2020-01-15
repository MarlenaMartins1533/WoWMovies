package com.marlena.wowmovies.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marlena.wowmovies.model.entity.InfoEntity

@Database(entities = [InfoEntity::class], version = 1)
abstract class MyMovieDB : RoomDatabase() {

    companion object {
        lateinit var instance: MyMovieDB private set

        fun initialize(applicationContext: Context) {
            instance = Room.databaseBuilder(
                applicationContext,
                MyMovieDB::class.java,
                "mymovies.db"
            ).apply {
                allowMainThreadQueries()
            }.build()
        }
    }

    abstract fun mymoviesDAO(): MyMovieDAO
}