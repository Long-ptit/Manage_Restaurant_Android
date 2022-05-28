package com.restaurant.exam.data.model

data class Staff(
    var id: Int? = null,
    var username: String? = null,
    var password: String? = null,
    var role: String? = null,
    var name: String? = null,
    var address: String? = null,
    var phone: String? = null,
    var restaurant: Restaurant? = null
)
