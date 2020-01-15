package com.marlena.wowmovies.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "mymovie")
class InfoEntity : Serializable {

    @PrimaryKey
    @ColumnInfo(name = "poster_path")
    var poster_path: String = "poster_path"
    @ColumnInfo(name = "name")
    var title: String = "Atenção"
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = true
    @ColumnInfo(name = "desc")
    var description: String = "description"
}