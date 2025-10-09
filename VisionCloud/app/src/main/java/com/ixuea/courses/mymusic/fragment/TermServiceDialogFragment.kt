package com.ixuea.courses.mymusic.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.FragmentManager
import com.ixuea.courses.mymusic.R
import com.ixuea.superui.process.SuperProcessUtil
import com.ixuea.superui.util.ScreenUtil
import com.ixuea.superui.util.SuperTextUtil

/**
 * 服务条款和隐私协议对话框
 */
class TermServiceDialogFragment : BaseCommonFragment() {
    private lateinit var disagreeView: Button
    private lateinit var onAgreementClickListener: View.OnClickListener

    /**
     * 同意按钮
     */
    private lateinit var primaryView: Button

    /**
     * 内容文本
     */
    private lateinit var contentView: TextView

    override fun initViews() {
        super.initViews()
        //点击弹窗外边不能关闭
        isCancelable = false

        contentView = findViewById<TextView>(R.id.content)
        SuperTextUtil.setLinkColor(contentView, getColor(requireContext(), R.color.link))

        primaryView = findViewById(R.id.primary)
        disagreeView = findViewById(R.id.disagree)
    }

    override fun initDatum() {
        super.initDatum()
        //转换成Html链接
        val content = Html.fromHtml(getString(R.string.term_service_privacy_content))
        contentView.text = content
    }

    override fun initListeners() {
        super.initListeners()
        //同意按钮点击
        primaryView.setOnClickListener {
            dismiss()
            onAgreementClickListener.onClick(it)
        }

        //不同意按钮点击
        disagreeView.setOnClickListener {
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

    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_term_service, container, false)
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