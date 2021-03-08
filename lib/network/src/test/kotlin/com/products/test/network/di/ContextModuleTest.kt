package com.products.network.di

import android.app.Application
import com.products.data.di.modules.ContextModule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ContextModuleTest {

    @MockK(relaxed = true)
    lateinit var application: Application
    private lateinit var contextModule: ContextModule

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        contextModule = ContextModule(application)
    }

    @Test
    fun verifyProvidedContext() {
        assertEquals(application, contextModule.provideContext())
    }
}
