package com.caizhixing.poetrywidget

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_poetry_view_detail.*

class PoetryDetailContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poetry_view_detail)
        Utils.fixStatusBar(this)
        val position = intent.getIntExtra("position", -1)
        val adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val poetryFragment = PoetryFragment()
                val bundle = Bundle()
                bundle.putParcelable("poetry", MainActivity.data[position])
                poetryFragment.arguments = bundle
                return poetryFragment
            }

            override fun getCount(): Int = MainActivity.data.size

        }
        view_pager.adapter = adapter
        if (position != -1) {
            view_pager.currentItem = position
        } else {
            view_pager.currentItem = 0
        }

    }
}
