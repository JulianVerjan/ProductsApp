package com.products.data.di

import com.products.data.di.modules.NetworkModule
import io.mockk.mockk
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NetworkModuleTest {

    private lateinit var networkModule: NetworkModule

    @Before
    fun setUp() {
        networkModule = NetworkModule()
    }

    @Test
    fun verifyProvidedHttpLoggingInterceptor() {
        val interceptor = networkModule.provideHttpLoggingInterceptor()
        assertEquals(HttpLoggingInterceptor.Level.BODY, interceptor.level)
    }

    @Test
    fun verifyProvidedHttpClient() {
        val interceptor = mockk<HttpLoggingInterceptor>()
        val httpClient = networkModule.provideHttpClient(interceptor)

        assertEquals(1, httpClient.interceptors.size)
        assertEquals(interceptor, httpClient.interceptors.first())
    }

    @Test
    fun verifyProvidedRetrofitBuilder() {
        val retrofit = networkModule.provideRetrofitBuilder()
        assertEquals(
            "https://api.blockchain.info/charts/",
            retrofit.baseUrl().toUrl().toString()
        )
    }
}
