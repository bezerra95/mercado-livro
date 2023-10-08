package com.mercadolivro.gateways.mysql

import com.mercadolivro.domains.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository: CrudRepository<PurchaseModel, Int> {

}
