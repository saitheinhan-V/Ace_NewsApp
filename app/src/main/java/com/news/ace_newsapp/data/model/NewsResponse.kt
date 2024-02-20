/*
 * *
 *  * Created by Rafsan Ahmad on 9/27/21, 5:30 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package com.news.ace_newsapp.data.model


data class NewsResponse(
    val articles: MutableList<NewsData>,
    val status: String,
    val totalResults: Int
)