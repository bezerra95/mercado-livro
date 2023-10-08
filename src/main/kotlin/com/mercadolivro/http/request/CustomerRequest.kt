package com.mercadolivro.http.request

import com.mercadolivro.domains.CustomerModel
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CustomerRequest(

    @field:NotEmpty(message = "Name must be provided")
    var name: String,

    @field:Email(message = "Email must be valid")
    @field:EmailAvailable
    var email: String,

    @field: NotEmpty
    var password: String
)
{
    fun toCustomerModel(id: Int?): CustomerModel {
        return CustomerModel(
            name = this.name,
            email = this.email,
            status = CustomerStatus.ATIVO,
            password = this.password
        )
    }
}

