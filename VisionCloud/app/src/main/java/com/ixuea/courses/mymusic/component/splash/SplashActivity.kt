package com.ixuea.courses.mymusic.component.splash

import android.Manifest
import android.os.Build
import android.util.Log
import com.ixuea.courses.mymusic.R
import com.ixuea.courses.mymusic.activity.BaseViewModelActivity
import com.ixuea.courses.mymusic.component.guide.GuideActivity
import com.ixuea.superui.date.SuperDateUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.ixuea.courses.mymusic.databinding.ActivitySplashBinding
import com.ixuea.courses.mymusic.fragment.TermServiceDialogFragment
import com.ixuea.courses.mymusic.util.DefaultPreferenceUtil
import com.ixuea.courses.mymusic.util.PreferenceUtil
import com.ixuea.superui.util.SuperDarkUtil
import com.permissionx.guolindev.PermissionX

/**
 * 启动界面
 */
class SplashActivity : BaseViewModelActivity<ActivitySplashBinding>() {

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
        //设置版本年份
        binding.copyright.text = getString(R.string.copyright, SuperDateUtil.currentYear())

        if (DefaultPreferenceUtil.getInstance(this).isAcceptTermsServiceAgreement) {
            //用户已经同意了用户协议
            requestPermission()
        } else {
            //用户未同意用户协议
            showTermsServiceAgreementDialog()
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionX.init(this).permissions (
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_MEDIA_AUDIO,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
            )
        } else {
            PermissionX.init(this).permissions (
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        }.request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    //权限都同意了（这样不太好）
                    //这样可能看不到启动界面而直接进入下个界面，要延迟1s
                    binding.root.postDelayed({
                        prepareNext()
                    }, 1000)
                } else {
                    //可以在这里弹出提示告诉用户为什么需要权限
                    finish()
                }
        }
    }

    private fun prepareNext() {
        Log.d(TAG, "prepareNext: ")

//        val intent = Intent(this,GuideActivity::class.java)
//        startActivity(intent)
//        finish()

        if (PreferenceUtil.isShowGuide()) {
            startActivityAfterFinishThis(GuideActivity::class.java)
            return
        }

        //跳转到下一个界面
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