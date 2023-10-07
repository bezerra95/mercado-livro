package com.mercadolivro.gateways.mysql

import com.mercadolivro.domains.BookModel
import com.mercadolivro.domains.CustomerModel
import com.mercadolivro.enums.BookStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<BookModel, Int> {
    fun findAllByStatus(status: BookStatus, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel?): List<BookModel>
    override fun findAll(pageable: Pageable): Page<BookModel>
}
