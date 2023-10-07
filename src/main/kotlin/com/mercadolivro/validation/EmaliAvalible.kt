package com.mercadolivro.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailAvailableValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class EmailAvailable(
    val message: String = "Email already registered",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)