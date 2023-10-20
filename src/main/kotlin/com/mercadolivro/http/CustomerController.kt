package com.mercadolivro.http

import com.mercadolivro.gateways.CustomerService
import com.mercadolivro.http.request.CustomerRequest
import com.mercadolivro.http.response.CustomerResponse
import com.mercadolivro.http.response.toResponse
import com.mercadolivro.security.UserCaOnlyAccessTheirOwnResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("customers")
class CustomerController (
    val customerService: CustomerService
) {

    @GetMapping
    @UserCaOnlyAccessTheirOwnResource
    fun getAllCustomers(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getAllCustomersByName(name).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    @UserCaOnlyAccessTheirOwnResource
    fun getCustomer(@PathVariable id: Int): ResponseEntity<CustomerResponse> {
        return try {
            val customer = customerService.getById(id) ?: return ResponseEntity.notFound().build()

            val response = customer.toResponse()

            return ResponseEntity.ok(response)

        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody @Valid request: CustomerRequest): ResponseEntity<CustomerResponse> {
        return try {
            customerService.create(request.toCustomerModel(null))

            ResponseEntity.status(HttpStatus.CREATED).build()

        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UserCaOnlyAccessTheirOwnResource
    fun updateCustomer(
        @PathVariable id: Int,
        @Valid @RequestBody request: CustomerRequest
    ): ResponseEntity<CustomerResponse> {
        return try {
            val customer = customerService.getById(id)  ?: return ResponseEntity.notFound().build()

            customerService.update(id, request.toCustomerModel(customer.id!!))
            
            val response = customer.toResponse()
            
            return ResponseEntity.ok(response)
            
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int): ResponseEntity<CustomerResponse> {
        return try {
            val customer = customerService.getById(id) ?: return ResponseEntity.notFound().build()

            customerService.delete(customer.id!!)

            val response = customer.toResponse()
           
            return ResponseEntity.ok(response)
       
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }
}
