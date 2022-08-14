package com.pdpano.ainukafka.domain

import java.math.BigDecimal

data class Order(
    val orderUuid: String,
    val userUuid: String,
    val price: BigDecimal
)
