package com.improve777.tmdb.data.remote.utils

interface JsonParser {
    fun <T> toJson(obj: T): String

    fun <T> fromJson(json: String, clazz: Class<T>): T
}