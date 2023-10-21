package com.mercadolivro.http.response

import org.springframework.data.domain.Page

class PageResponse<T> (
    var itens: List<T>,
    var currentPages: Int,
    var totalItens: Long,
    var totalPages: Int
)

fun <T > Page<T>.toPageResponse(): PageResponse<T> {
    return PageResponse(
        this.content,
        this.number,
        this.totalElements,
        this.totalPages)
}