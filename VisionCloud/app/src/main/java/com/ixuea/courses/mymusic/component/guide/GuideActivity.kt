package com.ixuea.courses.mymusic.component.guide

import android.util.Log
import com.ixuea.courses.mymusic.activity.BaseViewModelActivity
import com.ixuea.courses.mymusic.databinding.ActivityGuideBinding


/**
 * 左右滚动的引导界面
 * 如果想实现更复杂的效果，例如：滚动时文本缩放等效果，可以使用类似这样的框架：
 * https://github.com/bingoogolapple/BGABanner-Android
 */
class GuideActivity : BaseViewModelActivity<ActivityGuideBinding>() {
    override fun initListeners() {
        super.initListeners()
        binding.btnLoginOrRegister.setOnClickListener {
            Log.d(TAG, "btnLoginOrRegister click")
        }

        binding.btnExperienceNow.setOnClickListener {
            Log.d(TAG, "btnExperienceNow click")
        }
    }

    companion object {
        const val TAG = "GuideActivity"
    }
}