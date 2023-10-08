package com.mercadolivro.gateways

import com.mercadolivro.domains.CustomerModel
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.gateways.mysql.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAllCustomersByName(name: String?): List<CustomerModel> {
        return if (!name.isNullOrBlank()) {
            customerRepository.findByNameContaining(name)
        } else {
            customerRepository.findAll().toList()
        }
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun getById(id: Int): CustomerModel? {
        return customerRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code) }
    }

    fun update(id: Int, customerRequest: CustomerModel) {
        val existingCustomerOptional: Optional<CustomerModel> = customerRepository.findById(id)

        if (existingCustomerOptional.isEmpty) {
            throw NoSuchElementException("Customer with ID $id not found")
        }
        val existingCustomer: CustomerModel = existingCustomerOptional.get()
        existingCustomer.name = customerRequest.name
        existingCustomer.email = customerRequest.email
        existingCustomer.status = existingCustomer.status
        existingCustomer.password = existingCustomer.password

        customerRepository.save(existingCustomer)
    }

    fun delete(id: Int) {
        if (!customerRepository.existsById(id)) {
            throw NoSuchElementException("Customer with ID $id not found")
        }
        val customer = getById(id)

        bookService.deleteByCustomer(customer)

        customer!!.status = CustomerStatus.INATIVO

       customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}
