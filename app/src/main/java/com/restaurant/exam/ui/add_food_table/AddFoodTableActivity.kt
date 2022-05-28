package com.restaurant.exam.ui.add_food_table

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.restaurant.exam.base.BaseActivity
import com.restaurant.exam.data.model.Food
import com.restaurant.exam.ui.add_food_table.adapter.AddFoodAdapter
import com.restaurant.exam.view_model.ViewModelFactory
import restaurant.exam.R
import restaurant.exam.databinding.LayoutAddFoodTableBinding

class AddFoodTableActivity : BaseActivity<AddFoodTableViewModel, LayoutAddFoodTableBinding>(), AddFoodAdapter.IClick {

    companion object {
        fun getIntent(
            context: Context
        ): Intent {
            return Intent(context, AddFoodTableActivity::class.java)
        }
    }

    private lateinit var mAdapter : AddFoodAdapter
    private var idFloor: Int = 0
    private var idTable = 0
    override fun getContentLayout(): Int {
        return R.layout.layout_add_food_table

    }

    override fun observerLiveData() {
//        viewModel.apply {
//            loginResponse.observe(this@LoginActivity, androidx.lifecycle.Observer {
//                if (it != null) {
//                    showError(it.status)
//                    if (it.data != null) {
//                        startActivity(MainActivity.getIntent(this@LoginActivity))
//                        finish()
//                    }
//                }
//            })
//        }
    }

    fun getList(): ArrayList<Food> {
        var list: ArrayList<Food> = ArrayList()
        list.add(Food(isSelected = false, name = "Thịt bò", price = 1000, id = 1))
        list.add(Food(isSelected = false, name = "Thịt trâu", price = 1000, id = 2))
        list.add(Food(isSelected = false, name = "Thịt dê", price = 1000, id = 3))
        list.add(Food(isSelected = false, name = "Thịt ngựa", price = 1000, id = 4))
        list.add(Food(isSelected = false, name = "Thịt lồn", price = 1000, id = 5))
        return list;
    }

    override fun initView() {
        if (intent.hasExtra("id_floor")) {
            idFloor  = intent.getIntExtra("id_floor", 0)
            idTable  = intent.getIntExtra("id_table", 0)
        }

        showError = true
        mAdapter = AddFoodAdapter(this)
        binding.rcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = mAdapter
        mAdapter.setList(getList())


    }


    override fun initListener() {
        binding.fab.setOnClickListener {
            var listData = mAdapter.getList()
            listData.forEach {
                viewModel.saveToFirebase("Đồ ăn", it, idFloor, idTable)
            }
            finish()
        }
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[AddFoodTableViewModel::class.java]
    }

    override fun onClick(food: Food) {
        TODO("Not yet implemented")
    }

}