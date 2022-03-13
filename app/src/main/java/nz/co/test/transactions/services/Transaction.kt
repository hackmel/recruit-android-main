package nz.co.test.transactions.services

import java.math.BigDecimal

data class Transaction(
    val id: Int,
    val transactionDate: String,
    val summary: String,
    val debit: BigDecimal,
    val credit: BigDecimal
)