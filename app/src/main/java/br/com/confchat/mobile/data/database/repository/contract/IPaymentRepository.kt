package br.com.confchat.mobile.data.database.repository.contract

import br.com.confchat.mobile.data.database.entitys.Payment

interface IPaymentRepository {
    fun create(it:Payment):Payment
}