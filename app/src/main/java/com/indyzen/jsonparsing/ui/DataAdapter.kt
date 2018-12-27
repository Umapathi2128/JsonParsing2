package com.indyzen.jsonparsing.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.indyzen.jsonparsing.R
import com.indyzen.jsonparsing.databinding.ListViewBinding

/**
 * Created by Umapathi on 24/12/18.
 * Copyright Indyzen Inc, @2018
 */
class DataAdapter( var list: ArrayList<DataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        lateinit var listDataViewBinding: ListViewBinding
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        listDataViewBinding=DataBindingUtil.inflate(LayoutInflater.from(p0.context),
            R.layout.list_view,p0,false)

        return MyViewHolder(listDataViewBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vHolder=holder as MyViewHolder
        vHolder.onBind(list[position])
    }

    class MyViewHolder(var view: ListViewBinding) : RecyclerView.ViewHolder(view.root) {

        fun onBind(item: DataModel){
            view.setData=item
        }
    }
}