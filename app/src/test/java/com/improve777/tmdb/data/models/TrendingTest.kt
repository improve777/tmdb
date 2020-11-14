package com.improve777.tmdb.data.models

import com.improve777.tmdb.data.remote.mapper.toModel
import com.improve777.tmdb.data.remote.models.FileGsonParser
import com.improve777.tmdb.data.remote.models.TrendingResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TrendingTest {

    @Test
    fun `파싱`() {
        val trendingResponse = FileGsonParser.fromJson(
            "src/test/java/com/improve777/tmdb/data/remote/models/Trending.json",
            TrendingResponse::class.java
        )
        val trending = trendingResponse.toModel()

        assertTrue(trending.id == 82856)
    }
}