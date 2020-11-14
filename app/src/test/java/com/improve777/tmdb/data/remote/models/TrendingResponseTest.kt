package com.improve777.tmdb.data.remote.models

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class TrendingResponseTest {

    @Test
    fun `파싱`() {
        val trending = FileGsonParser.fromJson(
            "src/test/java/com/improve777/tmdb/data/remote/models/Trending.json",
            TrendingResponse::class.java
        )

        assertTrue(trending.id == 82856)
        assertTrue(trending.backdropPath == "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg")
    }
}