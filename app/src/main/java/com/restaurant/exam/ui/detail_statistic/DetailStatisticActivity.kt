package com.restaurant.exam.ui.detail_statistic

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.restaurant.exam.base.BaseActivity
import com.restaurant.exam.ui.login.LoginActivity
import com.restaurant.exam.view_model.ViewModelFactory
import restaurant.exam.R
import restaurant.exam.databinding.LayoutDetailFoodBinding

class DetailStatisticActivity : BaseActivity<DetailStatisticViewModel, LayoutDetailFoodBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.layout_detail_food
    }

    override fun observerLiveData() {
    }

    override fun initView() {
        showError = true
    }

    override fun initListener() {
    }


    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[DetailStatisticViewModel::class.java]
    }
}