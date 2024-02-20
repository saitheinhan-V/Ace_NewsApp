package com.news.ace_newsapp.data

import android.app.Application
import com.news.ace_newsapp.utils.AppConstant


class NewsRepository(application: Application) : BaseRepository(application) {

    suspend fun getNews(pageNumber: Int) = apiAuth.getNews(AppConstant.COUNTRY_CODE,pageNumber,AppConstant.PAGE_SIZE)

}