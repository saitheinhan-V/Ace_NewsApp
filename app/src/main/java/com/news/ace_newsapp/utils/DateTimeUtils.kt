package com.news.ace_newsapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun shortDateFormat(inputDate: String?): String? {
    val inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    val outputPattern = "MMM dd"
    val inputFormat = SimpleDateFormat(inputPattern, Locale.US)
    val outputFormat = SimpleDateFormat(outputPattern, Locale.US)
    var date: Date? = null
    var outputDate: String? = null
    try {
        date = inputFormat.parse(inputDate)
        outputDate = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return outputDate
}

fun localDateFormat(inputDate: String?): String? {
    if (!inputDate.isNullOrEmpty()) {
        val inputPattern = "yyyy-MM-dd'T'HH:mm:ss"
        val outputPattern = "dd MMM yyyy"
        val inputFormat = SimpleDateFormat(inputPattern, Locale.US)
        val outputFormat = SimpleDateFormat(outputPattern, Locale.US)
        var date: Date? = null
        var outputDate: String? = null
        try {
            date = inputFormat.parse(inputDate)
            outputDate = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputDate
    }
    else {
        return ""
    }
}

fun localDateTimeFormat(inputDate: String?): String? {
    val inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    val outputPattern = "dd MMM yyyy hh:mm aa"
    val inputFormat = SimpleDateFormat(inputPattern, Locale.US)
    val outputFormat = SimpleDateFormat(outputPattern, Locale.US)
    var date: Date? = null
    var outputDate: String? = null
    try {
        date = inputFormat.parse(inputDate)
        outputDate = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return outputDate
}

fun fullDateTimeFormat(inputDate: String?): String? {
    if (!inputDate.isNullOrEmpty()) {
        val inputPattern = "dd MMM yyyy"
        val outputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS"
        val inputFormat = SimpleDateFormat(inputPattern, Locale.US)
        val outputFormat = SimpleDateFormat(outputPattern, Locale.US)
        var date: Date? = null
        var outputDate: String? = null
        try {
            date = inputFormat.parse(inputDate)
            outputDate = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputDate
    }
    else {
        return ""
    }
}