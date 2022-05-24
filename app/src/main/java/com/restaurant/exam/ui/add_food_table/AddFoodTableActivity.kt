package com.restaurant.exam.ui.add_food_table

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.restaurant.exam.base.BaseActivity
import com.restaurant.exam.data.model.Food
import com.restaurant.exam.data.model.FoodFirebase
import com.restaurant.exam.ui.add_food_table.adapter.AddFoodAdapter
import com.restaurant.exam.ui.table.DetailTableViewModel
import com.restaurant.exam.ui.table.adapter.FoodInTableAdapter
import com.restaurant.exam.utils.recycleview_utils.GridSpacingItemDecoration
import com.restaurant.exam.view_model.ViewModelFactory
import restaurant.exam.R
import restaurant.exam.databinding.LayoutAddFoodTableBinding
import restaurant.exam.databinding.LayoutDetailTableBinding

class AddFoodTableActivity : BaseActivity<AddFoodTableViewModel, LayoutAddFoodTableBinding>(), AddFoodAdapter.IClick {

    companion object {
        fun getIntent(
            context: Context
        ): Intent {
            return Intent(context, AddFoodTableActivity::class.java)
        }
    }

    private lateinit var mAdapter : AddFoodAdapter

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
        list.add(Food(isSelected = false, name = "Thịt bò"))
        list.add(Food(isSelected = false, name = "Thịt bò"))
        list.add(Food(isSelected = false, name = "Thịt bò"))
        list.add(Food(isSelected = false, name = "Thịt bò"))
        list.add(Food(isSelected = false, name = "Thịt bò"))
        return list;
    }

    override fun initView() {
        showError = true
        mAdapter = AddFoodAdapter(this)
        binding.rcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = mAdapter
        mAdapter.setList(getList())
    }


    override fun initListener() {
//        CommonUtils.pushDownClickAnimation(0.9F, binding.tvSignUp) {
//            startActivity(SignUpActivity.getIntent(this))
//        }
//        CommonUtils.pushDownClickAnimation(0.9F, binding.btnLogin) {
//            if (binding.edtEmail.text.isNotEmpty() && binding.edtPassword.text.isNotEmpty()) {
//                val loginRequest = LoginRequest(binding.edtEmail.text.toString(), binding.edtPassword.text.toString())
//                viewModel.login(loginRequest)
//            }
//            else {
//                showError(getString(R.string.str_alert))
//            }
//        }
//        CommonUtils.pushDownClickAnimation(0.9f, binding.btnBack) {
//            onBackPressed()
//        }
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[AddFoodTableViewModel::class.java]
    }

    override fun onClick(food: Food) {
        TODO("Not yet implemented")
    }

}