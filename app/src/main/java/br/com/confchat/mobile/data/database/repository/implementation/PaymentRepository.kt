package br.com.confchat.mobile.data.database.repository.implementation

import br.com.confchat.mobile.data.database.dao.PaymentDao
import br.com.confchat.mobile.data.database.entitys.Payment
import br.com.confchat.mobile.data.database.repository.contract.IPaymentRepository

class PaymentRepository constructor(private val db:PaymentDao) : IPaymentRepository {
    override fun create(it: Payment): Payment {
        db.insert(it)
        var response = db.getlast()
        return response
    }
}