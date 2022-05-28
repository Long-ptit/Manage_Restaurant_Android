package com.restaurant.exam.ui.detail_food

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.restaurant.exam.base.BaseActivity
import com.restaurant.exam.data.model.Staff
import com.restaurant.exam.ui.login.LoginActivity
import com.restaurant.exam.ui.login.LoginViewModel
import com.restaurant.exam.ui.main.MainActivity
import com.restaurant.exam.utils.RealPathUtils
import com.restaurant.exam.view_model.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import restaurant.exam.R
import restaurant.exam.databinding.LayoutDetailFoodBinding
import restaurant.exam.databinding.LayoutLoginBinding
import java.io.File

class DetailFoodActivity : BaseActivity<DetailFoodViewModel, LayoutDetailFoodBinding>() {

    companion object {
        fun getIntent(
            context: Context
        ): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun getContentLayout(): Int {
        return R.layout.layout_detail_food
    }

    override fun observerLiveData() {
    }

    override fun initView() {
        showError = true
    }

    override fun initListener() {
        Glide.with(this).load("http://192.168.1.5:8080/api/v1/food/getImage/1.jpg").into(binding.img)
    }


    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[DetailFoodViewModel::class.java]
    }
}