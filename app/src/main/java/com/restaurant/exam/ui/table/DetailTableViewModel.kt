package com.restaurant.exam.ui.table

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.restaurant.exam.data.DataManager
import com.restaurant.exam.data.model.FoodFirebase
import com.restaurant.exam.data.model.TableFirebase
import com.restaurant.exam.network.Api
import com.restaurant.exam.view_model.BaseViewModel
import javax.inject.Inject

@SuppressLint("CheckResult")
class DetailTableViewModel : BaseViewModel() {
    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var api: Api
    var valueEventListener: ValueEventListener? = null
    var tableDatabase: DatabaseReference? = null
    val database = Firebase.database("https://restaurant-dd83b-default-rtdb.firebaseio.com/").reference

    fun getObserveDetailTable(id: Int, callbackSuccess: (list: List<FoodFirebase>) -> Unit){
        if (valueEventListener != null && tableDatabase != null) {
            tableDatabase!!.removeEventListener(valueEventListener!!)
            Log.d("ptit", "delete: ")
        }
        tableDatabase = database.child("restaurant").child("1").child("1").child("1").child("data").child("Đồ ăn")

        valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listWishlistAdd = ArrayList<FoodFirebase>()
                for (dataSnapshot in snapshot.children) {
                    val wishlistAdd = dataSnapshot.getValue(FoodFirebase::class.java)
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

}