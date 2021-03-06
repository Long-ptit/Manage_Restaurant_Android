package com.restaurant.exam.ui.add_food_table.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.restaurant.exam.data.model.Food
import com.restaurant.exam.data.model.FoodFirebase
import restaurant.exam.databinding.ItemFoodAddBinding
import restaurant.exam.databinding.ItemFoodTableBinding

class AddFoodAdapter(val click: IClick) : RecyclerView.Adapter<AddFoodAdapter.ViewHolder>() {
    var listFood = arrayListOf<Food>()
    fun setList(listFood: ArrayList<Food>) {
        this.listFood = listFood
        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding: ItemFoodAddBinding) : RecyclerView.ViewHolder(binding.root) {
        fun fillData(data: Food, position: Int){
            Log.d("ptit", "fillData: " + position)
            binding.layout.setOnClickListener {
                if (data.isSelected) {
                    binding.layout.setCardBackgroundColor(Color.RED)
                    listFood.get(position).isSelected = false
                    listFood.get(position).quantity = 0
                    binding.layoutAddSub.visibility = View.INVISIBLE
                } else {
                    listFood.get(position).isSelected = true
                    listFood.get(position).quantity = 1
                    binding.layoutAddSub.visibility = View.VISIBLE
                    binding.tvQuantity.text = listFood.get(position).quantity.toString()
                    binding.layout.setCardBackgroundColor(Color.BLUE)
                }
            }
            binding.btnAdd.setOnClickListener {
                listFood.get(position).quantity = listFood.get(position).quantity?.plus(1)
                binding.tvQuantity.text = listFood.get(position).quantity.toString()
            }

            binding.btnSub.setOnClickListener {
                listFood.get(position).quantity = listFood.get(position).quantity?.minus(1)
                binding.tvQuantity.text = listFood.get(position).quantity.toString()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        return listFood.size;
    }

    interface IClick {
        fun onClick(food: Food)
    }
}