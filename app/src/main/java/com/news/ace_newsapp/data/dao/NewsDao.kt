/*
 * *
 *  * Created by Rafsan Ahmad on 9/27/21, 5:30 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package com.news.ace_newsapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.news.ace_newsapp.data.model.NewsData

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(newsData: NewsData): Long

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<NewsData>>

    @Delete
    suspend fun deleteNews(newsData: NewsData)

    @Query("Delete FROM news")
    suspend fun deleteAllNews()
}