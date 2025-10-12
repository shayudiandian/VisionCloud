package com.ixuea.courses.mymusic.util

import com.tencent.mmkv.MMKV

/**
 * 偏好设置工具类
 */
object PreferenceUtil {
    val p: MMKV by lazy {
        MMKV.defaultMMKV()
    }

    /**
     * 是否显示引导界面
     *
     * @return
     */
    fun isShowGuide(): Boolean {
        return getBoolean(SHOW_GUIDE, true)
    }

    /**
     * 设置是否显示引导界面
     *
     * @param value
     */
    fun setShowGuide(value: Boolean) {
        putBoolean(SHOW_GUIDE, value)
    }

    //region 辅助方法
    /**
     * 获取boolean
     *
     * @param key
     * @param defaultValue
     * @return
     */
    private fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return p.getBoolean(key, defaultValue)
    }

    /**
     * 保存boolean
     *
     * @param key
     * @param value
     */
    private fun putBoolean(key: String, value: Boolean) {
        p.edit().putBoolean(key, value).apply()
    }
    //endregion

    /**
     * 是否显示引导界面key
     */
    private const val SHOW_GUIDE = "SHOW_GUIDE"
}