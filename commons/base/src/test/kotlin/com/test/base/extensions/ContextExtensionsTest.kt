package com.test.base.extensions

import android.content.Context
import com.products.base.extensions.getStringOrEmpty
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ContextExtensionsTest {

    @MockK(relaxed = true)
    lateinit var context: Context

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `return resource when id is not null`() {
        val resId = 0
        val expectedString = "test"

        every { context.getString(any()) } returns expectedString

        assertEquals(expectedString, context.getStringOrEmpty(resId))
    }

    @Test
    fun `return empty string when id is null`() {
        val resId = null
        val expectedString = ""

        assertEquals(expectedString, context.getStringOrEmpty(resId))
    }
}
