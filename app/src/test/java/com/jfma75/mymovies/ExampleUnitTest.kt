package com.jfma75.mymovies

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jfma75.mymovies.data.entities.Movie
import com.jfma75.mymovies.data.entities.MyObjectBox
import com.jfma75.mymovies.data.mappers.toDomain
import com.jfma75.mymovies.data.repositories.MoviesRepository
import com.jfma75.mymovies.di.DaggerMyMoviesComponent
import com.jfma75.mymovies.di.TestAppComponent
import com.jfma75.mymovies.server.MoviesDbResult
import com.jfma75.mymovies.server.TheMovieDb
import com.jfma75.mymovies.server.TheMovieDbService
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.DebugFlags
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Calls
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    lateinit var moviesBox: Box<Movie>
    lateinit var service: TheMovieDbService

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).addNetworkInterceptor(this).build()
    }

    private var boxStore: BoxStore? = null
    lateinit var moviesRepository: MoviesRepository

    companion object {
        val TEST_DIRECTORY = File("objectbox-example/test-db")
    }

    @Before
    fun setup() {
        // delete database files before each test to start with a clean database
        BoxStore.deleteAllFiles(TEST_DIRECTORY)
        boxStore = MyObjectBox.builder()
            // add directory flag to change where ObjectBox puts its database files
            .directory(TEST_DIRECTORY)
            // optional: add debug flags for more detailed ObjectBox log output
            .debugFlags(DebugFlags.LOG_QUERIES or DebugFlags.LOG_QUERY_PARAMETERS)
            .build()

        service = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run {
                create(TheMovieDbService::class.java)
            }
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `data is loaded from server`() {
        runBlocking {
            val result = service.listMoviesAsync("batman", "").await()

            assert(result.search?.size == 10)
        }
    }
}
