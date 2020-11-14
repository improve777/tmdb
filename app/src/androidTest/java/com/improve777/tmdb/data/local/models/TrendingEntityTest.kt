package com.improve777.tmdb.data.local.models

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.improve777.tmdb.data.local.AppDatabase
import com.improve777.tmdb.data.local.dao.TrendingDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
internal class TrendingEntityTest {
    private lateinit var trendingDao: TrendingDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()
        trendingDao = db.trendingDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadTrending() {
        val id = 82856
        val trending = TrendingEntity(id, "avatar")

        runBlocking {
            trendingDao.insert(trending)
            val byId = trendingDao.findById(id)

            assertTrue(id == byId.id)
        }
    }
}