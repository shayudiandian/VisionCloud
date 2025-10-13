package com.ixuea.courses.mymusic.component.guide

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * 引导界面适配器
 * FragmentStatePagerAdapter 过时的
 */
class GuideAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    private var datum: MutableList<Int> = mutableListOf()

    /**
     * 有多少个
     *
     * @return
     */
    override fun getCount(): Int {
        return datum.size
    }

    /**
     * 获取当前位置的数据
     *
     * @param position
     * @return
     */
    override fun getItem(position: Int): Fragment {
        return GuideFragment.newInstance(datum[position])
    }

    /**
     * 设置数据
     *
     * @param datum
     */
    fun setDatum(datum: MutableList<Int>) {
        this.datum.clear()
        this.datum.addAll(datum)
        // 刷新
        notifyDataSetChanged()
    }
}