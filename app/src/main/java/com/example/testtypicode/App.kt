package com.example.testtypicode

import android.app.Application
import android.content.Context
import android.content.res.Resources

class App : Application() {
    companion object {
        private var instance: App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    init {
        instance = this
    }
}