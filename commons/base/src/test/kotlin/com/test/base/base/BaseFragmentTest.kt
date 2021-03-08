package com.test.base.base

import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario
import com.products.test.TestCompatActivity
import com.products.test.TestFragmentActivity
import com.products.test.robolectric.TestRobolectric
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class BaseFragmentTest : TestRobolectric() {

    @SpyK
    var baseFragment = TestBaseFragment()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `success invoke initDependencyInjection on attach`() {
        baseFragment.onAttach(context)

        verify { baseFragment.onInitDependencyInjection() }
    }

    @Test
    fun `successfully return compact activity when requireCompatActivity is called`() {
        val scenario = ActivityScenario.launch(TestCompatActivity::class.java)
        scenario.onActivity {
            it.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, baseFragment)
                .commitNow()

            val compatActivity = baseFragment.requireCompatActivity()
            assertNotNull(compatActivity)
            assertThat(compatActivity, instanceOf(AppCompatActivity::class.java))
        }
    }

    @Test(expected = TypeCastException::class)
    fun `not return compact activity when requireCompatActivity is called`() {
        val scenario = ActivityScenario.launch(TestFragmentActivity::class.java)
        scenario.onActivity {
            it.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, baseFragment)
                .commitNow()

            baseFragment.requireCompatActivity()
        }
    }
}
