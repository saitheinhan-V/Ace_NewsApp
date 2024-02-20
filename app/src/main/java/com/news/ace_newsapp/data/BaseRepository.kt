package com.news.ace_newsapp.data

import android.app.Application
import com.news.ace_newsapp.network.NewsApi
import com.news.ace_newsapp.network.RequestBuilder
import com.news.ace_newsapp.utils.AppConstant

open class BaseRepository(application: Application) {

    val apiAuth = RequestBuilder.createService(NewsApi::class.java, AppConstant.BASE_URL)

}