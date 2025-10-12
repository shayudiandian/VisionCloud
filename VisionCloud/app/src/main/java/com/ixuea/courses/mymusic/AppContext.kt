package com.ixuea.courses.mymusic

import android.app.Application
import android.util.Log
import com.tencent.mmkv.MMKV

/**
 * 全局Application
 */
class AppContext : Application() {
    override fun onCreate() {
        super.onCreate()
        initMMKV()
    }

    /**
     * 初始化 腾讯开源的高性能keyValue存储，用来替代系统的SharedPreferences
     */
    private fun initMMKV() {
        val rootDir = MMKV.initialize(this)
        Log.d(TAG, "initMMKV: $rootDir")
    }

    companion object {
        const val TAG = "AppContext"
    }
}