package com.restaurant.exam.ui.manage_staff

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.restaurant.exam.data.DataManager
import com.restaurant.exam.data.model.Floor
import com.restaurant.exam.data.model.Staff
import com.restaurant.exam.data.model.TableFirebase
import com.restaurant.exam.data.model.TableRestaurant
import com.restaurant.exam.network.Api
import com.restaurant.exam.utils.PREF_RESTAURANT_ID
import com.restaurant.exam.utils.URL_FIREBASE
import com.restaurant.exam.view_model.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class ManageStaffViewModel : BaseViewModel() {
    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var api: Api

    val database = Firebase.database(URL_FIREBASE).reference

    val getStaffResponse = MutableLiveData<List<Staff>>()
    fun getStaff() {
        api.getStaffByIdRes(dataManager.getInt(PREF_RESTAURANT_ID))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result ->
                    if (result != null) {
                    }
                    getStaffResponse.postValue(result)
                },
                { throwable ->
                    handleApiError(throwable)
                }
            )
    }

    val saveStaffResponse = MutableLiveData<Staff>()
    fun saveStaff(data: Staff) {
        api.saveStaff(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result ->
                    if (result != null) {
                    }
                    saveStaffResponse.postValue(result)
                },
                { throwable ->
                    handleApiError(throwable)
                }
            )
    }

    fun getResId(): Int {
        return dataManager.getInt(PREF_RESTAURANT_ID)
    }


}