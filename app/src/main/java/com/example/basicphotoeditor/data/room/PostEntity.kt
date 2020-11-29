package com.example.basicphotoeditor.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity (
    @PrimaryKey(autoGenerate = false)
    var id: String,

    // Todo: implement another format of image
    @ColumnInfo(name ="image")
    var image: String,

    @ColumnInfo(name ="title")
    var title: String,

    @ColumnInfo(name ="source")
    var source: String
)
