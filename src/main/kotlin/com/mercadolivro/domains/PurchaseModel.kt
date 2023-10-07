package com.mercadolivro.model

import com.mercadolivro.domains.BookModel
import com.mercadolivro.domains.CustomerModel
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "purchase")
data class PurchaseModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel,

    @ManyToMany
    @JoinTable(
        name = "purchase_book",
        joinColumns = [JoinColumn(name = "purchase_id")],
        inverseJoinColumns = [JoinColumn(name = "book_id")]
    )
    val books: MutableList<BookModel>,

    @Column(name = "create_at")
    val createAt: LocalDateTime = LocalDateTime.now(),

    val nfe: String? = null,
    val price: BigDecimal
)
