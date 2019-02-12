package com.caizhixing.poetrywidget

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created on 2019/2/12.
 * Author caizhixing
 */
class PoetryAdapter(private val data: List<Poetery>) : RecyclerView.Adapter<PoetryAdapter.PoetryViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): PoetryViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.view_poetry_item, viewGroup, false)
        return PoetryViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PoetryViewHolder, index: Int) {
        holder.textView.text = data[index].displayContent
        holder.bgView.setOnClickListener {
            val intent = Intent(holder.textView.context,PoetryDetailContentActivity::class.java)
            intent.putExtra("data",data[index])
            holder.textView.context.startActivity(intent)
        }
//        holder.textView.setOnLongClickListener({
//            Toast.makeText(it.context, "确定删除？", Toast.LENGTH_LONG).show()
//            return false
//        })

    }

    class PoetryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.text)
        val bgView = itemView.findViewById<View>(R.id.poetry_item)
    }
}