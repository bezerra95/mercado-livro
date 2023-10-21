package com.mercadolivro.gateways

import com.mercadolivro.domains.BookModel
import com.mercadolivro.domains.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BookGateway {

    fun save(book: BookModel)
    fun findAll(pageable: Pageable): Page<BookModel>
    fun findAllByStatus(pageable: Pageable): Page<BookModel>
    fun findById(id: Int): BookModel?
    fun findByCustomer(customer: CustomerModel?): List<BookModel>
    fun saveAll(booksCustomer: BookModel): List<BookModel>

}