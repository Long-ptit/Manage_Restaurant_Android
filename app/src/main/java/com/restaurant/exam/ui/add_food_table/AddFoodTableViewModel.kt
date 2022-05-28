package com.restaurant.exam.ui.add_food_table

import android.annotation.SuppressLint
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.restaurant.exam.data.DataManager
import com.restaurant.exam.data.model.Food
import com.restaurant.exam.data.model.Restaurant
import com.restaurant.exam.network.Api
import com.restaurant.exam.utils.PREF_RESTAURANT_ID
import com.restaurant.exam.view_model.BaseViewModel
import javax.inject.Inject

@SuppressLint("CheckResult")
class AddFoodTableViewModel : BaseViewModel() {
    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var api: Api
    val database = Firebase.database("https://restaurant-dd83b-default-rtdb.firebaseio.com/").reference

    fun saveToFirebase(type: String, data: Food, idFloor: Int, idTable: Int) {
        database
            .child("restaurant")
            .child(dataManager.getInt(PREF_RESTAURANT_ID).toString())
            .child(idFloor.toString())
            .child(idTable.toString())
            .child("data")
            .child(type)
            .child(data.id.toString())
            .setValue(data)
            .addOnSuccessListener {
            }
            .addOnFailureListener {
            }
    }

}