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
class PoetryAdapter(private var data: List<Poetry>) : RecyclerView.Adapter<PoetryAdapter.PoetryViewHolder>() {

    private lateinit var mListener: RemovePoetryListener

    interface RemovePoetryListener {
        fun delete(poetry: Poetry)
        fun deleteAll()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): PoetryViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.view_poetry_item, viewGroup, false)
        return PoetryViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PoetryViewHolder, index: Int) {
        holder.textView.text = data[index].displayContent
        holder.bgView.setOnClickListener {
            val intent = Intent(holder.textView.context, PoetryDetailContentActivity::class.java)
            intent.putExtra("data", data[index])
            intent.putExtra("position", index)
            holder.textView.context.startActivity(intent)
        }
        holder.bgView.setOnLongClickListener {
            onLongClick(index)
        }

    }

    private fun onLongClick(index: Int): Boolean {
        mListener.delete(data[index])
        return true
    }

    fun setListener(listener: RemovePoetryListener) {
        mListener = listener
    }

    class PoetryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.text)
        val bgView = itemView.findViewById<View>(R.id.poetry_item)
    }
}