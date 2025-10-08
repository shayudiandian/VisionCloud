package com.ixuea.courses.mymusic.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ixuea.courses.mymusic.R
import com.ixuea.superui.date.SuperDateUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import android.widget.TextView
import com.ixuea.superui.util.SuperDarkUtil

/**
 * 启动界面
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //设置沉浸式状态栏
        QMUIStatusBarHelper.translucent(this)

        if (SuperDarkUtil.isDark(this)) {
            //状态栏文字白色
            QMUIStatusBarHelper.setStatusBarDarkMode(this)
        } else {
            //状态栏文字黑色
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        }

        //设置版本年份
        findViewById<TextView>(R.id.copyright).text = "Copyright © ${SuperDateUtil.currentYear()} SYDD. All Rights Reserved"
    }
}