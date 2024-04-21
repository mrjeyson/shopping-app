package com.jsoft.shoppingapp.data.order

import com.jsoft.shoppingapp.data.Address
import com.jsoft.shoppingapp.data.CartProduct

data class Order(
    val orderStatus: String,
    val totalPrice: Float,
    val products: List<CartProduct>,
    val address: Address
)
