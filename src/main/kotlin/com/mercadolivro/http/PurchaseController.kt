package com.mercadolivro.http

import com.mercadolivro.gateways.BookService
import com.mercadolivro.gateways.PurchaseService
import com.mercadolivro.http.request.PurchaseRequest
import com.mercadolivro.mapper.PurchaseMapper
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("purchase")
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper,
    private val bookService: BookService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PurchaseRequest){
        val books = bookService.findAllByIds(request.bookIds)
        purchaseService.create(purchaseMapper.toModel(request))
    }
}
