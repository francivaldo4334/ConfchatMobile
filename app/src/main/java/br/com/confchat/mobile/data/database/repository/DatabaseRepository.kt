package br.com.confchat.mobile.data.database.repository

import br.com.confchat.mobile.data.database.AppDatabase
import br.com.confchat.mobile.data.database.dao.PaymentDao
import br.com.confchat.mobile.data.database.entitys.Payment

class DatabaseRepository constructor(private val db:PaymentDao) : IDatabaseRepository {
    override fun create(it: Payment): Payment {
        db.insert(it)
        var response = db.getlast()
        return response
    }
}