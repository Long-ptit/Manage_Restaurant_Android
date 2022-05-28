package com.restaurant.exam.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.restaurant.exam.data.DataManager
import com.restaurant.exam.data.model.*
import com.restaurant.exam.network.Api
import com.restaurant.exam.utils.*
import com.restaurant.exam.view_model.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel : BaseViewModel() {

    @Inject
    lateinit var dataManager: DataManager

    val database = Firebase.database(URL_FIREBASE).reference

    @Inject
    lateinit var api: Api
    var valueEventListener: ValueEventListener? = null
    var tableDatabase: DatabaseReference? = null
    fun saveToFirebase() {
        val restaurant: Restaurant = Restaurant()
        restaurant.address = "haha"
        database.child("ptit").child("abc").setValue(restaurant)
            .addOnSuccessListener {
            }
            .addOnFailureListener {
            }
    }

    fun getTable(id: Int, callbackSuccess: (list: ArrayList<TableFirebase>) -> Unit) {
        if (valueEventListener != null && tableDatabase != null) {
            tableDatabase!!.removeEventListener(valueEventListener!!)
            Log.d("ptit", "delete: ")
        }
        tableDatabase = database
            .child(RESTAURANT)
            .child(dataManager.getInt(PREF_RESTAURANT_ID)
                .toString())
            .child(id.toString())

        valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listWishlistAdd = ArrayList<TableFirebase>()
                for (dataSnapshot in snapshot.children) {
                    val wishlistAdd = dataSnapshot.getValue(TableFirebase::class.java)
                    wishlistAdd?.let { listWishlistAdd.add(it) }
                    Log.d("ptit", dataSnapshot.toString())

                }
                callbackSuccess.invoke(listWishlistAdd)
            }

            override fun onCancelled(error: DatabaseError) {
                tableDatabase!!.removeEventListener(this)
            }
        }
        tableDatabase!!.addValueEventListener(valueEventListener!!)
    }


    val floorResponse = MutableLiveData<List<Floor>>()

    @SuppressLint("CheckResult")
    fun getFloorByRes(id: Int) {
        api.getFloor(id)
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
                    floorResponse.postValue(result)
                },
                { throwable ->
                    handleApiError(throwable)
                }
            )
    }

    fun getIdRes(): Int {
        return dataManager.getInt(PREF_RESTAURANT_ID)
    }

    val listFoodResponse = MutableLiveData<List<Food>>()
    @SuppressLint("CheckResult")
    fun getAllFood() {
        api.getFood(dataManager.getInt(PREF_RESTAURANT_ID))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result ->
                    if (result != null) {
//                        result.data?.id?.let { dataManager.save(PREF_USER_ID, it) }
//                        dataManager.save(PREF_USER_NAME, result.username)
//                        result.id?.let { dataManager.save(PREF_USER_ID, it) }
//                        result.restaurant?.id?.let { dataManager.save(PREF_RESTAURANT_ID, it) }
                    }
                    listFoodResponse.postValue(result)
                },
                { throwable ->
                    handleApiError(throwable)
                }
            )
    }

    fun checkIsStaff() : Boolean {
        return dataManager.getString(PREF_USER_ROLE).equals("nhanvien")
    }

    fun logout() {
        dataManager.remove(PREF_USER_NAME)
        dataManager.remove(PREF_PHONE_NUMBER)
        dataManager.remove(PREF_ADDRESS)
        dataManager.remove(PREF_USER_ID)
        dataManager.remove(PREF_USER_ROLE)


//        initSubscribeToTopic()
    }



}