package com.restaurant.exam.view_model

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.restaurant.exam.ui.login.LoginViewModel
import restaurant.exam.R
import com.restaurant.exam.injection.DaggerViewModelInjector
import com.restaurant.exam.injection.NetworkModule
import com.restaurant.exam.injection.ViewModelInjector
import com.restaurant.exam.ui.add_food_table.AddFoodTableViewModel
import com.restaurant.exam.ui.confirm_bill.ConfirmViewModel
import com.restaurant.exam.ui.detail_food.DetailFoodViewModel
import com.restaurant.exam.ui.home.HomeViewModel
import com.restaurant.exam.ui.main.MainViewModel
import com.restaurant.exam.ui.manage.ManageRestaurantViewModel
import com.restaurant.exam.ui.manage_staff.ManageStaffViewModel
import com.restaurant.exam.ui.table.DetailTableViewModel
import com.restaurant.exam.utils.myapp
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

abstract class BaseViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val responseMessage: MutableLiveData<String?> = MutableLiveData()

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .applicationComponent(myapp!!)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {

            is MainViewModel -> injector.inject(this)
            is HomeViewModel -> injector.inject(this)
            is LoginViewModel -> injector.inject(this)
            is DetailTableViewModel -> injector.inject(this)
            is AddFoodTableViewModel -> injector.inject(this)
            is ConfirmViewModel -> injector.inject(this)
            is DetailFoodViewModel -> injector.inject(this)
            is ManageRestaurantViewModel -> injector.inject(this)
            is ManageStaffViewModel -> injector.inject(this)
        }
    }

    protected fun onRetrievePostListStart() {
        isLoading.value = true
        errorMessage.value = null
    }

    protected fun onRetrievePostListFinish() {
        Handler().postDelayed({
            isLoading.value = false
        }, 1000);
    }

    protected fun handleApiError(error: Throwable?) {
        if (error == null) {
            errorMessage.value = R.string.api_default_error
            return
        }
//
//        if (error is HttpException) {
//            Log.w("ERROR", error.message() + error.code())
//            when (error.code()) {
//                HttpURLConnection.HTTP_BAD_REQUEST -> try {
//                    val message: Message = GsonUtils.deserialize(
//                        error.response().errorBody()!!.string(),
//                        Message::class.java
//                    )
//                    responseMessage.value = message.message
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                    responseMessage.value = error.message
//                }
//                HttpsURLConnection.HTTP_UNAUTHORIZED -> errorMessage.value = R.string.str_authe
//                HttpsURLConnection.HTTP_FORBIDDEN, HttpsURLConnection.HTTP_INTERNAL_ERROR, HttpsURLConnection.HTTP_NOT_FOUND -> responseMessage.value =
//                    error.message
//                else -> responseMessage.value = error.message
//            }
//        } else if (error is SocketTimeoutException) {
//            errorMessage.value = R.string.text_all_has_error_timeout
//        } else if (error is IOException) {
//            errorMessage.value = R.string.text_all_has_error_network
//        } else {
//            if (!TextUtils.isEmpty(error.message)) {
//                responseMessage.value = error.message
//            } else {
//                errorMessage.value = R.string.text_all_has_error_please_try
//            }
//        }
    }

    fun toMultipartBody(name: String, file: File): MultipartBody.Part {
        val reqFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData(name, file.name, reqFile)
    }

    fun toMultipartBody1(name: String, file: File): MultipartBody.Part {
        val reqFile = RequestBody.create("video/*, image/*".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData(name, file.name, reqFile)
    }

}