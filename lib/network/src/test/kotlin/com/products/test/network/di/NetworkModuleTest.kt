package com.products.test.network.di

import com.products.network.NetworkModule
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NetworkModuleTest {

    private lateinit var networkModule: NetworkModule

    @Before
    fun setUp() {
        networkModule = NetworkModule
    }

    @Test
    fun verifyProvidedHttpClient() {
        val httpClient = networkModule.provideHttpClient()
        assertEquals(20000, httpClient.connectTimeoutMillis)
    }

    @Test
    fun verifyProvidedRetrofitBuilder() {
        val retrofit = networkModule.provideRetrofitBuilder(mockk())
        assertEquals(
            "http://mobcategories.s3-website-eu-west-1.amazonaws.com/",
            retrofit.baseUrl().toUrl().toString()
        )
    }
}
