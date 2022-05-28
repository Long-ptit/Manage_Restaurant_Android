package com.restaurant.exam.ui.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.restaurant.exam.base.BaseActivity
import com.restaurant.exam.data.model.Staff
import com.restaurant.exam.ui.main.MainActivity
import com.restaurant.exam.ui.main.MainActivityStaff
import com.restaurant.exam.utils.RealPathUtils
import com.restaurant.exam.view_model.ViewModelFactory
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import restaurant.exam.R
import restaurant.exam.databinding.LayoutLoginBinding
import java.io.File


class LoginActivity : BaseActivity<LoginViewModel, LayoutLoginBinding>() {

    companion object {
        fun getIntent(
            context: Context
        ): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun getContentLayout(): Int {
        return R.layout.layout_login
    }

    override fun observerLiveData() {
        viewModel.apply {
            loginResponse.observe(this@LoginActivity, androidx.lifecycle.Observer {
                if (it != null) {
                    viewModel.initFirebase()
                    if (it.name != null ) {
                        showError("Xin chào: " + it.name!!)
                    }
                    if (viewModel.checkIsTaff()) {
                        startActivity(MainActivityStaff.getIntent(this@LoginActivity))
                        finish()
                    } else {
                        startActivity(MainActivity.getIntent(this@LoginActivity))
                        finish()
                    }

                }
            })
        }
    }

    override fun initView() {
        showError = true
    }

    override fun initListener() {
        binding.btnLogin.setOnClickListener {
            var staff = Staff();
            staff.username = binding.edtEmail.text.toString()
            staff.password = binding.edtPassword.text.toString()
            viewModel.login(staff)
        }
//        binding.btnLogin.setOnClickListener {
//            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//            startActivityForResult(gallery, pickImage)
//        }

    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[LoginViewModel::class.java]
    }

}
