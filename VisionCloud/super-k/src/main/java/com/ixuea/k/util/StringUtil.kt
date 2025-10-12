package com.ixuea.k.util

object StringUtil {
    /**
     * 是否符合昵称格式
     *
     * @param value
     * @return
     */
    fun isNickname(value: String): Boolean {
        return value.length in 2..10
    }
}