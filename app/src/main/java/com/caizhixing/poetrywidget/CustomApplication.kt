package com.caizhixing.poetrywidget

import android.app.Application
import android.content.Context
import com.jinrishici.sdk.android.factory.JinrishiciFactory

/**
 * Created on 2019/2/11.
 * Author caizhixing
 */
class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        JinrishiciFactory.init(this)
        context = applicationContext
    }

    companion object {
        private lateinit var context: Context
        fun getContext(): Context {
            return context
        }
    }
}