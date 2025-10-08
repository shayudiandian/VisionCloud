package com.ixuea.superui.date

import java.util.Calendar

/**
 * 日期时间工具类
 */
object SuperDateUtil {
    /**
     * 当前年
     */
    fun currentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }
}