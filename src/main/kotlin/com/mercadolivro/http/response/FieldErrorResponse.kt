package com.mercadolivro.http.response

data class FieldErrorResponse (
    var message: String,
    var field: String
)
