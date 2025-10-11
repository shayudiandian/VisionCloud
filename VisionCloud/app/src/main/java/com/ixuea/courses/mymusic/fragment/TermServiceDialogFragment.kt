package com.ixuea.courses.mymusic.fragment

import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.FragmentManager
import com.ixuea.courses.mymusic.R
import com.ixuea.courses.mymusic.databinding.FragmentDialogTermServiceBinding
import com.ixuea.superui.process.SuperProcessUtil
import com.ixuea.superui.util.ScreenUtil
import com.ixuea.superui.util.SuperTextUtil

/**
 * 服务条款和隐私协议对话框
 */
class TermServiceDialogFragment : BaseViewModelDialogFragment<FragmentDialogTermServiceBinding>() {

    private lateinit var onAgreementClickListener: View.OnClickListener

    override fun initViews() {
        super.initViews()
        //点击弹窗外边不能关闭
        isCancelable = false

        SuperTextUtil.setLinkColor(binding.content, getColor(requireContext(), R.color.link))
    }

    override fun initDatum() {
        super.initDatum()
        //转换成Html链接
        val content = Html.fromHtml(getString(R.string.term_service_privacy_content))
        binding.content.text = content
    }

    override fun initListeners() {
        super.initListeners()
        //同意按钮点击
        binding.primary.setOnClickListener {
            dismiss()
            onAgreementClickListener.onClick(it)
        }

        //不同意按钮点击
        binding.disagree.setOnClickListener {
            dismiss()
            SuperProcessUtil.killApp()
        }
    }

    override fun onResume() {
        super.onResume()
        //修改宽度，默认比AlertDialog.Builder显示对话框宽度窄，看着不好看
        //参考：https://stackoverflow.com/questions/12478520/how-to-set-dialogfragments-width-and-height
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ((ScreenUtil.getScreenWith(requireContext()) * 0.9).toInt())
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    companion object {
        /**
         * 显示对话框
         */
        fun show(fragmentManager: FragmentManager, onAgreementClickListener: View.OnClickListener) {
            val dialogFragment = TermServiceDialogFragment()

            dialogFragment.onAgreementClickListener = onAgreementClickListener

            dialogFragment.show(fragmentManager, "TermServiceDialogFragment")
        }
    }
}