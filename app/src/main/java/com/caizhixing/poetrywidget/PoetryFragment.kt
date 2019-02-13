package com.caizhixing.poetrywidget

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_poetry_detail_content.*

/**
 * Created on 2019/2/13.
 * Author caizhixing
 */
class PoetryFragment : Fragment() {

    private lateinit var mData :Poetery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments == null){
            return
        }
        mData = arguments!!.getParcelable<Poetery>("poetry")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_poetry_detail_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val author = String.format("%s·%s", mData.dynasty, mData.author)
        poetry_title.text = mData.title
        poetry_author.text = author
        poetry_content.text = mData.content
    }
}