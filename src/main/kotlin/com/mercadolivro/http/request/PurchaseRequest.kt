package com.mercadolivro.http.request

import com.sun.istack.NotNull
import javax.validation.constraints.Positive

data class PurchaseRequest(
    @field:NotNull
    @field:Positive
  //  @JsonAlias("customer_id")
    val customerId: Int,

    @field:NotNull
   // @JsonAlias("book_ids")
    val bookIds: Set<Int>
)

