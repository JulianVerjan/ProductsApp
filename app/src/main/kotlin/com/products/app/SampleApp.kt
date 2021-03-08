package com.products.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Base class for maintaining global application state.
 */
@HiltAndroidApp
class SampleApp : Application()
