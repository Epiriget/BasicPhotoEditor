package com.example.basicphotoeditor.data.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basicphotoeditor.service.HabrApi
import com.example.basicphotoeditor.service.HabrChannel
import com.example.basicphotoeditor.service.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Database(entities = [PostEntity::class], version = 1)
abstract class PostRoomDatabase : RoomDatabase() {
    abstract fun postsDao(): PostDao

    companion object {
        @Volatile
        private var databaseInstance: PostRoomDatabase? = null

        fun getDatabaseInstance(mContext: Context): PostRoomDatabase =
            databaseInstance ?: synchronized(this) {
                databaseInstance ?: buildDatabaseInstance(mContext).also {
                    databaseInstance = it
                }
            }


        private fun buildDatabaseInstance(mContext: Context): PostRoomDatabase {
            val room = Room.databaseBuilder(mContext, PostRoomDatabase::class.java, "posts_database")
                .fallbackToDestructiveMigration()
                .build()
            initDB(room)
            return room
        }

        // Todo remove callback, should be used Pagination (Pager)
        fun initDB(database: PostRoomDatabase) {
            val api = getRetrofit()
            val disposable =
                api.getChannel()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            database.postsDao().insertPosts(convert(it.channel))
                            Log.d("Retrofit", it.channel.items.size.toString())
                        },
                        {
                            Log.d("Retrofit", it.message.toString())
                        })
//            disposable.dispose()
        }

        fun convert(channel: HabrChannel):PostEntity {
            return PostEntity(1, channel.items[0].description, channel.items[0].title, "Habr")
        }

    }
}