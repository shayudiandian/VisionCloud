package com.ixuea.courses.mymusic.component.splash

import android.os.Bundle
import com.ixuea.courses.mymusic.activity.BaseLogicActivity
import com.ixuea.courses.mymusic.R
import com.ixuea.superui.date.SuperDateUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import android.widget.TextView
import com.ixuea.superui.util.SuperDarkUtil

/**
 * 启动界面
 */
class SplashActivity : BaseLogicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun initViews() {
        super.initViews()
        //设置沉浸式状态栏
        QMUIStatusBarHelper.translucent(this)

        if (SuperDarkUtil.isDark(this)) {
            //状态栏文字白色
            QMUIStatusBarHelper.setStatusBarDarkMode(this)
        } else {
            //状态栏文字黑色
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        }
    }

    override fun initDatum() {
        super.initDatum()
        //设置版本年份(这个找View应该放到initViews里，这里为了方便)
        findViewById<TextView>(R.id.copyright).text = getString(R.string.copyright, SuperDateUtil.currentYear())
    }
}