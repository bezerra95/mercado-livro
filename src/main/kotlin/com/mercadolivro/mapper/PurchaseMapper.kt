package com.mercadolivro.mapper

import com.mercadolivro.gateways.BookService
import com.mercadolivro.gateways.CustomerService
import com.mercadolivro.http.request.PurchaseRequest
import com.mercadolivro.domains.PurchaseModel
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val bookService: BookService,
    private val customerService: CustomerService
) {
    fun toModel(request: PurchaseRequest): PurchaseModel {
        val customer = customerService.getById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        return PurchaseModel(
            customer = customer!!,
            books = books.toMutableList(),
            price = books.sumOf { it.price }
        )

    }
}