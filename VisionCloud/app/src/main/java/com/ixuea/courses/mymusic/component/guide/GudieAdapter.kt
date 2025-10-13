package com.ixuea.courses.mymusic.component.guide

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ixuea.courses.mymusic.adapter.BaseFragmentPagerAdapter

/**
 * 引导界面适配器
 * FragmentStatePagerAdapter 过时的
 */
class GuideAdapter(context: Context, fragmentManager: FragmentManager) : BaseFragmentPagerAdapter<Int>(context, fragmentManager) {

    /**
     * 获取当前位置的数据
     *
     * @param position
     * @return
     */
    override fun getItem(position: Int): Fragment {
        return GuideFragment.newInstance(getData(position))
    }
}