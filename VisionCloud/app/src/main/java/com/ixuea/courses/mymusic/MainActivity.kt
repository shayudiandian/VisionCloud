package com.ixuea.courses.mymusic

import com.ixuea.courses.mymusic.activity.BaseViewModelActivity
import com.ixuea.courses.mymusic.component.login.LoginHomeActivity
import com.ixuea.courses.mymusic.databinding.ActivityMainBinding
import com.ixuea.courses.mymusic.util.Constant

class MainActivity : BaseViewModelActivity<ActivityMainBinding>() {

    override fun initDatum() {
        super.initDatum()

        val action = intent.action
        if (Constant.ACTION_LOGIN == action) {
            //跳转到启动界面
            startActivity(LoginHomeActivity::class.java)
        }
    }
}