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
import com.restaurant.exam.data.model.Floor
import com.restaurant.exam.data.model.Restaurant
import com.restaurant.exam.data.model.TableFirebase
import com.restaurant.exam.network.Api
import com.restaurant.exam.view_model.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel : BaseViewModel() {
    val database = Firebase.database("https://restaurant-dd83b-default-rtdb.firebaseio.com/").reference
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

    fun getTable(id: Int, callbackSuccess: (list: ArrayList<TableFirebase>) -> Unit){
        if (valueEventListener != null && tableDatabase != null) {
            tableDatabase!!.removeEventListener(valueEventListener!!)
            Log.d("ptit", "delete: ")
        }
        tableDatabase = database.child("restaurant").child("1").child(id.toString())

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
}