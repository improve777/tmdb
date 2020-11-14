package com.improve777.tmdb.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending")
data class TrendingEntity(
    @PrimaryKey val id: Int,
)