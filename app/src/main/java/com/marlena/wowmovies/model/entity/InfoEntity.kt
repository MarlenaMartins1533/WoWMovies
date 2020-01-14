package com.marlena.wowmovies.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "mypicture")
class InfoEntity : Serializable {

    @PrimaryKey
    @ColumnInfo(name = "url")
    var url: String = "url"
    @ColumnInfo(name = "name")
    var name: String = "Atenção"
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = true
    @ColumnInfo(name = "desc")
    var sensations: String = "sensations"
}