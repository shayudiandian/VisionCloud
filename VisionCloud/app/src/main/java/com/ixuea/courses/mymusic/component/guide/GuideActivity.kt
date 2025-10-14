package com.ixuea.courses.mymusic.component.guide

import android.content.Intent
import android.util.Log
import com.ixuea.courses.mymusic.MainActivity
import com.ixuea.courses.mymusic.R
import com.ixuea.courses.mymusic.activity.BaseViewModelActivity
import com.ixuea.courses.mymusic.config.Config
import com.ixuea.courses.mymusic.databinding.ActivityGuideBinding
import com.ixuea.courses.mymusic.util.Constant
import com.ixuea.courses.mymusic.util.PreferenceUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * 左右滚动的引导界面
 * 如果想实现更复杂的效果，例如：滚动时文本缩放等效果，可以使用类似这样的框架：
 * https://github.com/bingoogolapple/BGABanner-Android
 */
class GuideActivity : BaseViewModelActivity<ActivityGuideBinding>() {
    private lateinit var adapter: GuideAdapter

    override fun initDatum() {
        super.initDatum()
        //创建适配器
        adapter = GuideAdapter(this, supportFragmentManager)
        //设置适配器到ViewPager控件
        binding.list.adapter = adapter

        //让指示器根据列表控件配合工作
        binding.indicator.setViewPager(binding.list)
        //适配器注册数据源观察者
        adapter.registerDataSetObserver(binding.indicator.dataSetObserver)

        //准备数据
        val datum: MutableList<Int> = ArrayList()
        datum.add(R.drawable.guide1)
        datum.add(R.drawable.guide2)
        datum.add(R.drawable.guide3)
        datum.add(R.drawable.guide4)
        datum.add(R.drawable.guide5)

        //设置数据到适配器
        adapter.setDatum(datum)
    }

    override fun initListeners() {
        super.initListeners()
        binding.btnLoginOrRegister.setOnClickListener {
            Log.d(TAG, "btnLoginOrRegister click")
            setShowGuide()
            val intent = Intent(this, MainActivity::class.java)
            intent.action = Constant.ACTION_LOGIN
            startActivity(intent)
            finish()
        }

        binding.btnExperienceNow.setOnClickListener {
            Log.d(TAG, "btnExperienceNow click")
            setShowGuide()
            startActivityAfterFinishThis(MainActivity::class.java)
            //测试一下
            testGet()
        }
    }

    private fun testGet() {
        val client = OkHttpClient()

        val url = Config.ENDPOINT + "v1/contents"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "onFailure: " + e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "onResponse: " + response.body!!.string())
            }

        })
    }

    private fun setShowGuide() {
        PreferenceUtil.setShowGuide(false)
    }

    companion object {
        const val TAG = "GuideActivity"
    }
}