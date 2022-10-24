package com.example.spacex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//@HiltAndroidApp triggers Hilt's code generation, including a base class for your application that serves as the application-level dependency container
@HiltAndroidApp
class SpacexApp: Application()