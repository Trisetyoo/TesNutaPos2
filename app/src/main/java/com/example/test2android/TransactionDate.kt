package com.example.test2android

class TransactionDate(val date: String) : TransactionListItem {

    override val type: Int
        get() = TransactionListItem.TYPE_DATE

    var total: Double = 0.0
}