package com.example.basicphotoeditor.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 2)
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
            return Room.databaseBuilder(mContext, PostRoomDatabase::class.java, "posts_database")
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}