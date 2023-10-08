package com.mercadolivro.gateways

import com.mercadolivro.domains.PurchaseModel
import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.gateways.mysql.PurchaseRepository
import org.springframework.stereotype.Service
import org.springframework.context.ApplicationEventPublisher

@Service
class PurchaseService (
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
){
    fun create(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)

        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)

    }
}
