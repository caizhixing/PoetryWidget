package com.caizhixing.poetrywidget

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_poetry_detail_content.*

class PoetryDetailContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getParcelableExtra<Poetery>("data")
        if(data.content.length > 120){
            setContentView(R.layout.activity_poetry_detail_content_max)
        }else{
            setContentView(R.layout.activity_poetry_detail_content)
        }
        val author = String.format("%s·%s", data.dynasty, data.author)
        poetry_title.text = data.title
        poetry_author.text = author
        poetry_content.text = data.content
    }
}
