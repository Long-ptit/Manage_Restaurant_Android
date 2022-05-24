package com.restaurant.exam.ui.table.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.restaurant.exam.data.model.Floor
import com.restaurant.exam.data.model.FoodFirebase
import restaurant.exam.databinding.ItemFloorBinding
import restaurant.exam.databinding.ItemFoodTableBinding

class FoodInTableAdapter(val click: IClick) : RecyclerView.Adapter<FoodInTableAdapter.ViewHolder>() {
    var listFood = arrayListOf<FoodFirebase>()
    fun setList(listFood: ArrayList<FoodFirebase>) {
        this.listFood = arrayListOf<FoodFirebase>()
        this.listFood = listFood
        this.listFood.add(FoodFirebase())
        Log.d("ptit", "size: " + this.listFood.size)
        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding: ItemFoodTableBinding) : RecyclerView.ViewHolder(binding.root) {
        fun fillData(data: FoodFirebase, position: Int){
            Log.d("ptit", "fillData: " + position)
            when(position) {
                listFood.size - 1 -> {
                    binding.layoutAddFood.visibility = View.VISIBLE
                    binding.layoutAddFood.setOnClickListener {
                        click.onClick(data)
                    }
                }
                else -> binding.layoutFood.visibility = View.VISIBLE
            }
            binding.tvName.text = data.name
//            itemView.setOnClickListener {
//                Log.d("ptit", "fillData: " + data.name)
//                click.onClick(data)
//            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            val food = listFood[position]
            Log.d("ptit", "onBindViewHolder: " + position)
            fillData(food, position)
        }
    }

    override fun getItemCount(): Int {
        Log.d("ptit", "size reutrn: " + listFood.size)
        return listFood.size
    }

    interface IClick {
        fun onClick(food: FoodFirebase)
    }
}