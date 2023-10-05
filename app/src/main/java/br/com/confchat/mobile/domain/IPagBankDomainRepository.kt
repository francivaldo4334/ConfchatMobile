package br.com.confchat.mobile.domain

import br.com.confchat.mobile.veiwmodel.model.PaymentCreditCard

interface IPagBankDomainRepository {
    fun createOrder(data: PaymentCreditCard)
}