package com.marlena.wowmovies.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.marlena.wowmovies.model.entity.InfoEntity

@Dao
interface MyMovieDAO {

    @Query("SELECT * FROM mymovie")
    fun getAllMyMovies(): List<InfoEntity>

    @Query("SELECT * FROM mymovie WHERE poster_path = :poster_path")
    fun getByPosterPath(poster_path: String): InfoEntity

    @Insert
    fun insert(info: InfoEntity)

    @Delete
    fun delete(info: InfoEntity)
}