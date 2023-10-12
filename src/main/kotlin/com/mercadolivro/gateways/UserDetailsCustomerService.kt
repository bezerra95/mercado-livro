package com.mercadolivro.gateways

import com.mercadolivro.exception.AuthenticationException
import com.mercadolivro.gateways.mysql.CustomerRepository
import com.mercadolivro.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomerService(
    private val customerRepository: CustomerRepository

): UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        var customer = customerRepository.findById(id.toInt())
            .orElseThrow{ AuthenticationException("Usuário não encontrado", "999") }
        return UserCustomDetails(customer)
    }

}
