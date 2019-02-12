package com.caizhixing.poetrywidget

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * Created on 2019/2/11.
 * Author caizhixing
 */
class SettingActivity : AppCompatActivity() {

    companion object {
        val SETTING = "poetry_widget_setting"
        val IS_ENABLE_CLICK = "is_enable_click"
        val IS_ENABLE_AUTO_FRESH = "is_enable_auto_fresh"
        val REFRESH_PERIOD = "refresh_period"
        val TEXT_SIZE = "text_size"
        val TEXT_COLOR = "text_color"
    }

    private var mIsClick: Boolean = false
    private var mIsAutoFresh: Boolean = false
    private var mRefreshPeriod: Int = -1
    private var mTextSize: Int = -1
    private var mTextColor: Int = -1
    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mSharedPreferences = getSharedPreferences(SETTING, Context.MODE_PRIVATE)
        mIsClick = mSharedPreferences.getBoolean(IS_ENABLE_CLICK, false)
        mIsAutoFresh = mSharedPreferences.getBoolean(IS_ENABLE_AUTO_FRESH, true)
        mRefreshPeriod = mSharedPreferences.getInt(REFRESH_PERIOD, -1)
        mTextSize = mSharedPreferences.getInt(TEXT_SIZE, -1)
        mTextColor = mSharedPreferences.getInt(TEXT_COLOR, -1)

        initViewState()
        initListener()

    }

    private fun initListener() {
        setting_click.setOnCheckedChangeListener { buttonView, isChecked ->
            mSharedPreferences.edit().putBoolean(IS_ENABLE_CLICK, isChecked).apply()
        }
        setting_auto_fresh.setOnCheckedChangeListener { buttonView, isChecked ->
            mSharedPreferences.edit().putBoolean(IS_ENABLE_AUTO_FRESH, isChecked).apply()
        }
        radio_group_period.setOnCheckedChangeListener { radioGroup: RadioGroup, id: Int ->
            var index = -1
            when (id) {
                radio_button_period_0.id -> index = 0
                radio_button_period_1.id -> index = 1
                radio_button_period_2.id -> index = 2
            }
            if (index != -1) {
                mSharedPreferences.edit().putInt(REFRESH_PERIOD, index).apply()
            }
        }
        radio_group_text_size.setOnCheckedChangeListener { radioGroup: RadioGroup, id: Int ->
            var index = -1
            when (id) {
                radio_button_text_size_0.id -> index = 0
                radio_button_text_size_1.id -> index = 1
                radio_button_text_size_2.id -> index = 2
            }
            if (index != -1) {
                mSharedPreferences.edit().putInt(TEXT_SIZE, index).apply()
            }
        }
        radio_group_text_color.setOnCheckedChangeListener { radioGroup: RadioGroup, id: Int ->
            var index = -1
            when (id) {
                radio_button_text_color_0.id -> index = 0
                radio_button_text_color_1.id -> index = 1
                radio_button_text_color_2.id -> index = 2
            }
            if (index != -1) {
                mSharedPreferences.edit().putInt(TEXT_COLOR, index).apply()
            }
        }
    }

    private fun initViewState() {
        setting_click.isChecked = mIsClick
        setting_auto_fresh.isChecked = mIsAutoFresh
        when (mRefreshPeriod) {
            0 -> radio_button_period_0.isChecked = true
            1 -> radio_button_period_1.isChecked = true
            2 -> radio_button_period_2.isChecked = true
        }
        when (mTextSize) {
            0 -> radio_button_text_size_0.isChecked = true
            1 -> radio_button_text_size_1.isChecked = true
            2 -> radio_button_text_size_2.isChecked = true
        }
        when (mTextColor) {
            0 -> radio_button_text_color_0.isChecked = true
            1 -> radio_button_text_color_1.isChecked = true
            2 -> radio_button_text_color_2.isChecked = true
        }
    }

}