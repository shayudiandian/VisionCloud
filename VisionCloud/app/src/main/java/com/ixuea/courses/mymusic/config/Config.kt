package com.ixuea.courses.mymusic.config

import com.ixuea.courses.mymusic.BuildConfig

/**
 * 配置文件
 * BuildConfig 是自动生成的，查看有什么内容
 *
 *
 * 例如：API地址，QQ等第三方服务配置信息等
 */
object Config {
    /**
     * 端点
     */
    const val ENDPOINT = BuildConfig.ENDPOINT

    /**
     * 资源端点
     */
    var RESOURCE_ENDPOINT = BuildConfig.RESOURCE_ENDPOINT
}