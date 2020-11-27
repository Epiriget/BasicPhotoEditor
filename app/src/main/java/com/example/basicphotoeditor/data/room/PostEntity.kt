package com.example.basicphotoeditor.data.room

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
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

//fun List<PostEntity>.asDomainModel(): List<Post> {
//    return map {
//        Post(
//            id = it.id,
//            image = it.image,
//            title = it.title,
//            source = it.source
//        )
//    }
//}
