package com.news.ace_newsapp.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.news.ace_newsapp.data.NewsRepository
import com.news.ace_newsapp.data.dao.NewsDao
import com.news.ace_newsapp.data.database.NewsRoomDatabase
import com.news.ace_newsapp.data.model.NewsData
import com.news.ace_newsapp.network.Resource
import com.news.ace_newsapp.utils.ErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.concurrent.TimeoutException

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NewsRepository = NewsRepository(application)
    private val newsRoomDatabase: NewsRoomDatabase
    private val newsDao: NewsDao

    lateinit var news: NewsData

    lateinit var savedNewList: LiveData<List<NewsData>>

    init {
        newsRoomDatabase = NewsRoomDatabase.getInstance(application)
        newsDao = newsRoomDatabase.getNewsDao()
        getSavedNews()
    }

    fun getNew(pageNumber: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = repository.getNews(pageNumber)
            emit(Resource.success(data = data))
        } catch (responseException: HttpException) {
            emit(
                Resource.error(
                    ErrorHandler().apiError(
                        responseException.response()?.errorBody()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(e.message))
        }catch (e: TimeoutException){
            emit(Resource.error(e.message))
        }
    }

    fun saveNews(news: NewsData?) {
        viewModelScope.launch {
            newsDao.saveNews(news!!)
        }
    }

    private fun getSavedNews() = viewModelScope.launch{
        savedNewList = newsDao.getAllNews()
    }

    fun deleteNews(news: NewsData?){
        viewModelScope.launch {
            newsDao.deleteNews(news!!)
        }
    }
}