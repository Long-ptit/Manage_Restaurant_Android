package com.restaurant.exam.ui.table

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.restaurant.exam.base.BaseActivity
import com.restaurant.exam.data.model.Floor
import com.restaurant.exam.data.model.FoodFirebase
import com.restaurant.exam.data.model.Restaurant
import com.restaurant.exam.ui.add_food_table.AddFoodTableActivity
import com.restaurant.exam.ui.home.adapter.TableAdapter
import com.restaurant.exam.ui.login.LoginViewModel
import com.restaurant.exam.ui.table.adapter.FoodInTableAdapter
import com.restaurant.exam.utils.recycleview_utils.GridSpacingItemDecoration
import com.restaurant.exam.view_model.ViewModelFactory
import restaurant.exam.R
import restaurant.exam.databinding.LayoutDetailTableBinding
import restaurant.exam.databinding.LayoutLoginBinding

class DetailTableActivity : BaseActivity<DetailTableViewModel, LayoutDetailTableBinding>(), FoodInTableAdapter.IClick {

    companion object {
        fun getIntent(
            context: Context
        ): Intent {
            return Intent(context, DetailTableActivity::class.java)
        }
    }

    private lateinit var mAdapter : FoodInTableAdapter

    override fun getContentLayout(): Int {
        return R.layout.layout_detail_table
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

    override fun initView() {
        showError = true
        mAdapter = FoodInTableAdapter(this)

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 3)
        binding.rcv.layoutManager = mLayoutManager
        binding.rcv.itemAnimator = DefaultItemAnimator()
        binding.rcv.adapter = mAdapter
        binding.rcv.setHasFixedSize(true)
        binding.rcv.addItemDecoration(
            GridSpacingItemDecoration(
                3,
                25,
                true,
                0
            )
        )
        mAdapter.setList(getList())
        viewModel.getObserveDetailTable(1) {
            mAdapter.setList(it as ArrayList<FoodFirebase>)
        }
    }

    fun getList(): ArrayList<FoodFirebase> {
        var list = arrayListOf<FoodFirebase>()
        list.add(FoodFirebase(name = "Thịt chó"))
        list.add(FoodFirebase(name = "Thịt chó"))
        list.add(FoodFirebase(name = "Thịt chó"))
        list.add(FoodFirebase(name = "Thịt chó"))

        return list
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
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[DetailTableViewModel::class.java]
    }

    override fun onClick(food: FoodFirebase) {
        var intent =  Intent(
        this,
        AddFoodTableActivity::class.java
    )
        startActivity(intent)
    }


}