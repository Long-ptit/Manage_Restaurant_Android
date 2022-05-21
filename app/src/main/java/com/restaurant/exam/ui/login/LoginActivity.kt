package com.restaurant.exam.ui.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import restaurant.exam.R
import com.restaurant.exam.base.BaseActivity
import com.restaurant.exam.data.model.Restaurant
import restaurant.exam.databinding.LayoutLoginBinding
import com.restaurant.exam.view_model.ViewModelFactory


class LoginActivity : BaseActivity<LoginViewModel, LayoutLoginBinding>() {

    companion object {
        fun getIntent(
            context: Context
        ): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun getContentLayout(): Int {
        return R.layout.layout_login
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
        binding.btnLogin.setOnClickListener {
            var restaurant: Restaurant = Restaurant();
            restaurant.username = "1"
            restaurant.password = "1"
            viewModel.getRestarant(restaurant)
        }
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[LoginViewModel::class.java]
    }

}
