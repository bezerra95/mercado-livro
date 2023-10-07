package com.mercadolivro.http.request

import com.mercadolivro.domains.BookModel
import com.mercadolivro.domains.CustomerModel
import com.mercadolivro.enums.BookStatus
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class BookRequest (

    @field:NotEmpty(message = "O Nome deve ser informado")
    var name: String,

    @field:NotNull(message = "O Preço deve ser informado")
    var price: BigDecimal,

    var customerId : Int
)
{
    fun toBookModel(customer: CustomerModel): BookModel {
        return BookModel(
            name = this.name,
            price = this.price,
            status = BookStatus.ATIVO,
            customer = customer
        )
    }
}
