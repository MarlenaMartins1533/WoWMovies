package com.marlena.wowmovies.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.marlena.wowmovies.model.entity.InfoEntity

@Dao
interface MyPicturesDAO {

    @Query("SELECT * FROM mypicture")
    fun getAllMyPictures(): List<InfoEntity>

    @Query("SELECT * FROM mypicture WHERE url = :url")
    fun getByUrl(url: String): InfoEntity

    @Insert
    fun insert(info: InfoEntity)

    @Delete
    fun delete(info: InfoEntity)
}