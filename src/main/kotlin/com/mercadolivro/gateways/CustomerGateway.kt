package com.mercadolivro.gateways

import com.mercadolivro.domains.CustomerModel

interface CustomerGateway {
    fun findByNameContaining(name: String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): CustomerModel
    fun findAll(customerModel: CustomerModel): List<CustomerModel>
    fun save(customerModel: CustomerModel): CustomerModel
    fun findById(id: Int): CustomerModel
}