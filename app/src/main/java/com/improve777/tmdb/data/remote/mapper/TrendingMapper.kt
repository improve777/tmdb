package com.improve777.tmdb.data.remote.mapper

import com.improve777.tmdb.data.models.Trending
import com.improve777.tmdb.data.remote.models.TrendingResponse

fun TrendingResponse.toModel(): Trending {
    return Trending(
        id = this.id ?: 0
    )
}