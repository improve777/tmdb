package com.improve777.tmdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.improve777.tmdb.data.local.dao.TrendingDao
import com.improve777.tmdb.data.local.models.TrendingEntity

@Database(entities = [TrendingEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trendingDao(): TrendingDao
}