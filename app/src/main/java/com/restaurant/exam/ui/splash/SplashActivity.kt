package com.restaurant.exam.ui.splash

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import restaurant.exam.R
import com.restaurant.exam.base.BaseActivity
import restaurant.exam.databinding.LayoutSplashBinding
import com.restaurant.exam.ui.main.MainActivity
import com.restaurant.exam.ui.main.MainViewModel
import com.restaurant.exam.view_model.ViewModelFactory


class SplashActivity : BaseActivity<MainViewModel, LayoutSplashBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.layout_splash
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[MainViewModel::class.java]
    }

    override fun initView() {

    }

    override fun initListener() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun observerLiveData() {

    }
}
