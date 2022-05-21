package com.restaurant.exam.ui.login

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.restaurant.exam.data.DataManager
import com.restaurant.exam.data.model.Restaurant
import com.restaurant.exam.network.Api
import com.restaurant.exam.view_model.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@SuppressLint("CheckResult")
class LoginViewModel : BaseViewModel() {
    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var api: Api

//    val registerResponse = MutableLiveData<String?>()
//    fun register(createAccountRequest: CreateAccountRequest) {
//        api.register(createAccountRequest)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onRetrievePostListStart() }
//            .doOnTerminate { onRetrievePostListFinish() }
//            .subscribe(
//                { result ->
//                    when {
//                        result.errorMessages?.confirmPassword != null -> {
//                            registerResponse.postValue(result.errorMessages?.confirmPassword)
//                        }
//                        result.errorMessages?.email != null -> {
//                            registerResponse.postValue(result.errorMessages?.email)
//                        }
//                        result.errorMessages?.password != null -> {
//                            registerResponse.postValue(result.errorMessages?.password)
//                        }
//                        result.errorMessages == null -> {
//                            registerResponse.postValue("Đăng kí thành công!")
//                        }
//                    }
//                },
//                { throwable ->
//                    handleApiError(throwable)
//                }
//            )
//    }

    val loginResponse = MutableLiveData<Restaurant>()
    fun login(loginRequest: Restaurant) {
        api.login(loginRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result ->
                    if (result != null) {
//                        result.data?.id?.let { dataManager.save(PREF_USER_ID, it) }
//                        dataManager.save(PREF_USER_NAME, result?.data?.hoTen)
//                        dataManager.save(PREF_PHONE_NUMBER, result?.data?.soDienThoai)
//                        dataManager.save(PREF_ADDRESS, result?.data?.diaChi)
                    }
                    loginResponse.postValue(result)
                },
                { throwable ->
                    handleApiError(throwable)
                }
            )
    }

//    fun setStart(isStart: Boolean) {
//        dataManager.save(PREF_START, isStart)
//    }
//
//    fun isStart(): Boolean {
//        return dataManager.getBoolean(PREF_START)
//    }

    val getResponse = MutableLiveData<Restaurant>()
    fun getRestarant(getRequest: Restaurant) {
        api.getId()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result ->
                    if (result != null) {
//                        result.data?.id?.let { dataManager.save(PREF_USER_ID, it) }
//                        dataManager.save(PREF_USER_NAME, result?.data?.hoTen)
//                        dataManager.save(PREF_PHONE_NUMBER, result?.data?.soDienThoai)
//                        dataManager.save(PREF_ADDRESS, result?.data?.diaChi)
                    }
                    loginResponse.postValue(result)
                },
                { throwable ->
                    handleApiError(throwable)
                }
            )
    }

}