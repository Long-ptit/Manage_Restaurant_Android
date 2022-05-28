package com.restaurant.exam.data.model

import java.util.*

data class Bill(
var id: Int? = null,
var restaurant: Restaurant? = null,
var staff: Staff? = null,
var newDate: Date? = null,
var totalPrice: Int? = null
)
