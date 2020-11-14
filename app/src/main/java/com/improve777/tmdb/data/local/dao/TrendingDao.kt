package com.improve777.tmdb.data.local.dao

import androidx.room.*
import com.improve777.tmdb.data.local.models.TrendingEntity

@Dao
interface TrendingDao {
    @Query("SELECT * FROM trending")
    suspend fun getAll(): List<TrendingEntity>

    @Query("SELECT * FROM trending WHERE id = :id")
    suspend fun findById(id: Int): TrendingEntity

    @Insert
    suspend fun insertAll(vararg trendings: TrendingEntity)

    @Insert
    suspend fun insert(trending: TrendingEntity)

    @Update
    suspend fun update(trending: TrendingEntity)

    @Delete
    suspend fun delete(trending: TrendingEntity)
}