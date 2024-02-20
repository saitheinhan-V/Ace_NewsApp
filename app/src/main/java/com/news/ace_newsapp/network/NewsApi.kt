package com.news.ace_newsapp.network

import com.news.ace_newsapp.data.model.NewsResponse
import com.news.ace_newsapp.utils.AppConstant
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    @Headers("X-Api-Key: ${AppConstant.API_KEY}")
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String ?= AppConstant.COUNTRY_CODE,
        @Query("page")
        pageNumber: Int ?= 1,
        @Query("pageSize")
        pageSize: Int ?= AppConstant.PAGE_SIZE,
//        @Query("apiKey")
//        apiKey: String ?= AppConstant.API_KEY
        @Query("category")
        category: String ?= "business"
    ): NewsResponse
}