package com.improve777.tmdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.improve777.tmdb.data.local.dao.TrendingDao
import com.improve777.tmdb.data.local.models.TrendingEntity

@Database(entities = [TrendingEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trendingDao(): TrendingDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE trending_new (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL)"
                )
                database.execSQL(
                    "INSERT INTO trending_new (id, name) SELECT id, '' FROM trending"
                )
                database.execSQL("DROP TABLE trending")
                database.execSQL("ALTER TABLE trending_new RENAME TO trending")
            }
        }
    }
}