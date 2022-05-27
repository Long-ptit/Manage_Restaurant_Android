package com.restaurant.exam.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.restaurant.exam.base.BaseRecyclerAdapter
import com.restaurant.exam.data.model.Floor
import com.restaurant.exam.data.model.TableFirebase
import restaurant.exam.R
import restaurant.exam.databinding.ItemTableBinding

class TableAdapter(val click: IClick) : RecyclerView.Adapter<TableAdapter.ViewHolder>() {
    var listTable: List<TableFirebase> = listOf()

    fun setData(listTable: List<TableFirebase>) {
        this.listTable = listTable
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemTableBinding) : RecyclerView.ViewHolder(binding.root) {
        fun fillData(data: TableFirebase, position: Int){
            binding.tvName.text = data.name
            binding.tvStatus.text = data.status
            itemView.setOnClickListener {
                Log.d("ptit", "fillData: " + data.name)
                click.onClick(data)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
           val table = listTable[position]
            fillData(table, position)
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return listTable!!.size
    }

    interface IClick {
        fun onClick(table: TableFirebase)
    }
}