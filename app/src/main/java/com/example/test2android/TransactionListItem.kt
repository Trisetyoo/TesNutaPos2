package com.example.test2android

interface TransactionListItem {
    val type: Int

    companion object {
        const val TYPE_DATE = 0
        const val TYPE_TRANSACTION = 1
    }
}