package com.mercadolivro.http

import com.mercadolivro.gateways.BookService
import com.mercadolivro.gateways.CustomerService
import com.mercadolivro.http.request.BookRequest
import com.mercadolivro.http.response.BookResponse
import com.mercadolivro.http.response.PageResponse
import com.mercadolivro.http.response.toPageResponse
import com.mercadolivro.http.response.toResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: BookRequest) {
        val customer = customerService.getById(request.customerId)

        bookService.create(request.toBookModel(customer!!))
    }

    @GetMapping
    fun getAllBooks(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookResponse> {
        return bookService.getAllBooks(pageable).map { it.toResponse()}.toPageResponse()
    }

    @GetMapping("/actives")
    fun findByActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookResponse> {
        return bookService.findActives(pageable).map { it.toResponse()}.toPageResponse()
    }

    @GetMapping("/{id}")
    fun findByBook(@PathVariable id: Int): BookResponse {
        val book = bookService.findById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")

        return book.toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun delete(@PathVariable id: Int) {
        return bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBook(@PathVariable id: Int, @RequestBody request: BookRequest) {
        val book = bookService.findById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")

        val bookRequest = request.toBookModel(book.customer!!)

        bookService.update(book, bookRequest)
    }
}
