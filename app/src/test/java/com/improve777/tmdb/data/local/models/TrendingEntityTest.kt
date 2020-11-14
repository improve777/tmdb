package com.improve777.tmdb.data.local.models

import com.improve777.tmdb.data.local.dao.TrendingDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class TrendingEntityUnitTest {

    @RelaxedMockK
    private lateinit var mockDao: TrendingDao

    @BeforeEach
    internal fun setUp() {
    }

    @Test
    fun writeTrendingUsingSpy() = runBlocking {
        val id = 82856
        val trending = TrendingEntity(id, "avatar")

        // 행위 테스트 방법
        // 1. answer 를 직접 생성
//        val spykDao = mockk<TrendingDao>()                   // no answer found for
//        coEvery { spykDao.insert(trending) } returns Unit    // solution1

        // 2. relaxedMock 사용
//        val spykDao = mockk<TrendingDao>(relaxed = true)     // solution2

        // 3. Spy 사용
        val spykDao = spyk<TrendingDao>()         // solution3

        // 행위 테스트
        spykDao.insert(trending)
        coVerify { spykDao.insert(trending) }

        spykDao.insert(trending)

        // 횟수 테스트
        coVerify(atLeast = 2) { spykDao.insert(trending) }
    }

    @Test
    fun writeTrendingUsingMock() = runBlocking {
        val id = 82856
        val trending = TrendingEntity(id, "avatar")

        mockDao.insert(trending)
        coVerify { mockDao.insert(trending) }
    }

    @Test
    fun readTrending() {
        val id = 82856
        val name = "avatar"
        val trending = TrendingEntity(id, name)

        coEvery { mockDao.findById(id) } returns trending

        val mock: TrendingEntity
        runBlocking {
            mock = mockDao.findById(id)
            assertTrue(id == mock.id)
            assertTrue(name == mock.name)
        }
    }
}