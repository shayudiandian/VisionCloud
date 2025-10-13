package com.ixuea.courses.mymusic.component.guide

import android.os.Bundle
import com.ixuea.courses.mymusic.databinding.FragmentGuideBinding
import com.ixuea.courses.mymusic.fragment.BaseViewModelFragment
import com.ixuea.courses.mymusic.util.Constant


/**
 * 引导界面Fragment
 */
class GuideFragment : BaseViewModelFragment<FragmentGuideBinding>() {

    override fun initDatum() {
        super.initDatum()
        val data = requireArguments().getInt(Constant.ID)
        binding.icon.setImageResource(data)
    }

    companion object {
        /**
         * 创建方法（推荐做法）
         */
        fun newInstance(data: Int): GuideFragment {
            val args = Bundle()
            args.putInt(Constant.ID, data)

            val fragment = GuideFragment()
            fragment.arguments = args
            return fragment
        }
    }
}