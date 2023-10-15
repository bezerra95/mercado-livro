package com.mercadolivro.gateways

import com.mercadolivro.domains.PurchaseModel
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.gateways.mysql.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService (
    private val purchaseRepository: PurchaseRepository,

    private val applicationEventPublisher: ApplicationEventPublisher,
){
    fun create(purchaseModel: PurchaseModel) {
        val books = purchaseModel.books

        for (book in books) {
            if (book.status == BookStatus.ATIVO) {

                purchaseRepository.save(purchaseModel)

                applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
            }
            print("was not sold")
        }
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }
}
