package com.news.ace_newsapp

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    companion object {
        fun newInstance() = MyApplication()
    }

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

    }
}