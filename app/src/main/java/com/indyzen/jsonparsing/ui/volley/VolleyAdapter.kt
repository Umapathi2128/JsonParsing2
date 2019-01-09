package com.indyzen.jsonparsing.ui.volley

import android.databinding.DataBindingUtil
import android.databinding.DataBindingUtil.inflate
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.indyzen.jsonparsing.R
import com.indyzen.jsonparsing.databinding.VolleyViewBinding

/**
 * Created by Umapathi on 03/01/19.
 * Copyright Indyzen Inc, @2019
 */
class VolleyAdapter(var list: ArrayList<VolleyDataModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var volleyViewBinding: VolleyViewBinding

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        volleyViewBinding=DataBindingUtil.inflate(
            LayoutInflater.from(p0.context),
            R.layout.volley_view, p0, false
        )
        return MyViewHolder(volleyViewBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vHolder = holder as VolleyAdapter.MyViewHolder
        vHolder.onBind(list[position])
    }

    class MyViewHolder(var view: VolleyViewBinding) : RecyclerView.ViewHolder(view.root) {

        fun onBind(item: VolleyDataModel) {
            view.volleyData = item
        }
    }

    /**
     * This method for adding item to the recycler...
     */
    fun addItem(item: VolleyDataModel) {
        list.add(item)
        notifyDataSetChanged()
    }

    /**
     * This method for removing item from recycler based on position....
     */
    fun remove(position:Int)
    {
        notifyItemRemoved(position)
    }
}