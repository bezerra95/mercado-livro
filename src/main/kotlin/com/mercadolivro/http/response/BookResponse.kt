package com.mercadolivro.http.response

import com.mercadolivro.domains.BookModel
import com.mercadolivro.domains.CustomerModel
import com.mercadolivro.enums.BookStatus
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var status: BookStatus,
    var customer: CustomerModel?
)

fun BookModel.toResponse(): BookResponse{
    return BookResponse(
        id = this.id!!,
        name = this.name,
        price = this.price,
        status = this.status!!,
        customer = this.customer
    )
}
