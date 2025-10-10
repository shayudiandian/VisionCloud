package com.ixuea.courses.mymusic.component.splash

import android.Manifest
import android.os.Bundle
import android.util.Log
import com.ixuea.courses.mymusic.activity.BaseLogicActivity
import com.ixuea.courses.mymusic.R
import com.ixuea.superui.date.SuperDateUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import android.widget.TextView
import com.ixuea.courses.mymusic.fragment.TermServiceDialogFragment
import com.ixuea.courses.mymusic.util.DefaultPreferenceUtil
import com.ixuea.superui.util.SuperDarkUtil
import com.permissionx.guolindev.PermissionX

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

        if (DefaultPreferenceUtil.getInstance(this).isAcceptTermsServiceAgreement) {
            //用户已经同意了用户协议
            requestPermission()
        } else {
            //用户未同意用户协议
            showTermsServiceAgreementDialog()
        }
    }

    private fun requestPermission() {
        PermissionX.init(this)
            .permissions(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    //权限都同意了（这样不太好）
                    //这样可能看不到启动界面而直接进入下个界面，要延迟1s，后面再实现
                    prepareNext()
                } else {
                    //可以在这里弹出提示告诉用户为什么需要权限
                    finish()
                }
            }
    }

    private fun prepareNext() {
        Log.d(TAG, "prepareNext: ")

    }


    private fun showTermsServiceAgreementDialog() {
//        TermServiceDialogFragment.show(supportFragmentManager, object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                Log.d(TAG, "primary onClick")
//            }
//
//        })
        // Lambda
        TermServiceDialogFragment.show(supportFragmentManager) {
            Log.d(TAG, "primary onClick")
            DefaultPreferenceUtil.getInstance(this).setAcceptTermsServiceAgreement()
            requestPermission()
        }
    }

    companion object {
        const val TAG = "SplashActivity"
    }
}