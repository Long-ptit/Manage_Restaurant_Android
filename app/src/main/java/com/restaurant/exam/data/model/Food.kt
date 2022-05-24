package com.restaurant.exam.data.model

data class Food (
    var id: Int? = null,
    var name: String? = null,
    var quantity: Int? = null,
    var price: Int? = null,
    var url: String? = null,
    var isSelected: Boolean = false,
)