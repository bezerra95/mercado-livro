package com.mercadolivro.gateways

import com.mercadolivro.domains.PurchaseModel

interface PurchaseGateway {
    fun save(purchaseModel: PurchaseModel)
}