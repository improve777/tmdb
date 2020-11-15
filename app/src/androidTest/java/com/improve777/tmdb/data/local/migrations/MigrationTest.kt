package com.improve777.tmdb.data.local.migrations

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.improve777.tmdb.data.local.AppDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MigrationTest {

    private val TEST_DB_NAME = "test-db"

    @get:Rule
    val testHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory(),
    )

    @Before
    fun setUp() {
    }

    @Test
    @Throws(Exception::class)
    fun migrationFrom1To2_containsCorrectData() {
        val id = 82856
        val name = ""

        var db = testHelper.createDatabase(TEST_DB_NAME, 1)

        val values = ContentValues().apply {
            put("id", id)
//            put("name", name)
        }
        db.insert("trending", SQLiteDatabase.CONFLICT_REPLACE, values)
        db.close()

        // migrationTest
        testHelper.runMigrationsAndValidate(
            TEST_DB_NAME,
            2,
            true,
            AppDatabase.MIGRATION_1_2,
        )

        val room = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
            TEST_DB_NAME,
        )
            .addMigrations(AppDatabase.MIGRATION_1_2)
            .build()
        testHelper.closeWhenFinished(room)

        runBlocking {
            val trending = room.trendingDao().findById(id)
            assertTrue(id == trending.id)
            assertTrue(name == trending.name)
        }
    }
}