//package com.acom.service.user.ui.login
//
//import android.content.Context
//import android.content.Intent
//import androidx.lifecycle.ViewModelProvider
//import com.acom.service.user.R
//import com.acom.service.user.base.BaseActivity
//import com.acom.service.user.data.request.CreateAccountRequest
//import com.acom.service.user.databinding.ActivitySignUpBinding
//import com.acom.service.user.utils.CommonUtils
//import com.acom.service.user.view_model.ViewModelFactory
//import com.restaurant.exam.ui.login.LoginActivity
//
//class SignUpActivity : BaseActivity<LoginViewModel, ActivitySignUpBinding>() {
//
//    companion object {
//        fun getIntent(
//            context: Context
//        ): Intent {
//            return Intent(context, SignUpActivity::class.java)
//        }
//    }
//
//    override fun getContentLayout(): Int {
//        return R.layout.activity_sign_up
//    }
//
//    override fun observerLiveData() {
//        viewModel.apply {
//            registerResponse.observe(this@SignUpActivity, androidx.lifecycle.Observer {
//                if (it != null) {
//                    showError(it)
//                }
//                if (it == "Đăng kí thành công!") {
//                    startActivity(LoginActivity.getIntent(this@SignUpActivity))
//                    finish()
//                }
//            })
//        }
//    }
//
//    override fun initView() {
//        showError = true
//    }
//
//    override fun initListener() {
//        CommonUtils.pushDownClickAnimation(0.9f, binding.btnRegister) {
//            if (binding.edtAddress.text.isNotEmpty() && binding.edtEmail.text.isNotEmpty() && binding.edtName.text.isNotEmpty() && binding.edtNumberPhone.text.isNotEmpty() && binding.edtPassword.text.isNotEmpty()) {
//                val createAccountRequest = CreateAccountRequest(
//                    binding.edtPassword.text.toString(),
//                    binding.edtAddress.text.toString(),
//                    binding.edtEmail.text.toString(),
//                    binding.edtName.text.toString(),
//                    binding.edtPassword.text.toString(),
//                    binding.edtNumberPhone.text.toString(),
//                    "ROLE_MEMBER"
//                )
//                viewModel.register(createAccountRequest)
//            } else {
//                showError(getString(R.string.str_alert))
//            }
//        }
//
//        CommonUtils.pushDownClickAnimation(0.9f, binding.btnBack) {
//            onBackPressed()
//        }
//    }
//
//    override fun initViewModel() {
//        viewModel = ViewModelProvider(this, ViewModelFactory(this))[LoginViewModel::class.java]
//    }
//
//}