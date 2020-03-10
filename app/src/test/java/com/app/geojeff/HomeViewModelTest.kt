package com.app.geojeff

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.geojeff.data.entities.db.CityDB
import com.app.geojeff.data.repository.local.LocalDatabase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val database: LocalDatabase = mockk()

    @Test
    fun `city inserts correctly`() {
        //launch coroutine block
        coEvery {
            database.getCityDao().insert(getCityDB())
            val cities: List<CityDB> = database.getCityDao().getSearchHistory()
            assertNotNull(cities)
            cities.forEach { city ->
                assertEquals(getCityDB(), city)
            }
        }
    }

    private fun getCityDB(): CityDB {
        return CityDB(
            "Toronto",
            "Canada",
            "CA",
            "NA",
            "43.7184038",
            "-79.5181407",
            375900000
        )
    }

}