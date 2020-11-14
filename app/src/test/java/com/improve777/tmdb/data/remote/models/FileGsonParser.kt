package com.improve777.tmdb.data.remote.models

import com.google.gson.Gson
import com.improve777.tmdb.data.remote.utils.JsonParser
import java.io.FileInputStream

object FileGsonParser : JsonParser {

    override fun <T> toJson(obj: T): String {
        return Gson().toJson(obj)
    }

    override fun <T> fromJson(json: String, clazz: Class<T>): T {
        return FileInputStream(json).use {
            val message = String(it.readBytes())
            // println(message)
            Gson().fromJson(message, clazz)
        }
    }
}