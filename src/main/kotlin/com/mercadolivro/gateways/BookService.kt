package com.mercadolivro.gateways

import com.mercadolivro.domains.BookModel
import com.mercadolivro.domains.CustomerModel
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.gateways.mysql.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service

class BookService(
    val bookRespository: BookRepository
) {

    fun create(book: BookModel) {
        bookRespository.save(book)
    }

    fun getAllBooks(pageable: Pageable): Page<BookModel> {
        return bookRespository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel>{
        return bookRespository.findAllByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookModel? {
        return bookRespository.findById(id).orElseThrow{ NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code)}
    }

    fun delete(id: Int) {
        val book = findById(id) ?:throw Exception("Livro não encontrado")

        book.status = BookStatus.DELETADO

        bookRespository.save(book)
    }

    fun update(existingBook: BookModel, bookRequest: BookModel) {

        existingBook.name = bookRequest.name
        existingBook.price = bookRequest.price

        bookRespository.save(existingBook)
    }

    fun deleteByCustomer(customer: CustomerModel?) {
        val booksCustomer = bookRespository.findByCustomer(customer)

        for (book in booksCustomer) {
            book.status = BookStatus.CANCELADO
        }
        bookRespository.saveAll(booksCustomer)
    }
}
