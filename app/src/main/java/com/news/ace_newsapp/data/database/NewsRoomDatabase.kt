package com.news.ace_newsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.news.ace_newsapp.data.dao.NewsDao
import com.news.ace_newsapp.data.model.NewsData
import androidx.room.TypeConverters


@Database(entities = [NewsData::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
 abstract class NewsRoomDatabase : RoomDatabase(){

     abstract fun getNewsDao() : NewsDao

    companion object {

        @Volatile
        private var INSTANCE: NewsRoomDatabase? = null

        fun getInstance(context: Context): NewsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsRoomDatabase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

//        private var instance: NewsRoomDatabase? = null
//
//        @Synchronized
//        fun getInstance(ctx: Context): NewsRoomDatabase {
//            if(instance == null)
//                instance = Room.databaseBuilder(ctx.applicationContext, NewsRoomDatabase::class.java,
//                    "news_db")
////                    .fallbackToDestructiveMigration()
//                    .addCallback(roomCallback)
//                    .build()
//
//            return instance!!
//
//        }

//        fun getDatabase(context: Context): NewsRoomDatabase =
//            instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also {
//                    instance = it
//                }
//            }
//
//        //Build a local database to store data
//        private fun buildDatabase(appContext: Context) =
//            Room.databaseBuilder(appContext, NewsRoomDatabase::class.java, "news_db")
//                .fallbackToDestructiveMigration()
//                .addCallback(roomCallback)
//                .build()

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
 }