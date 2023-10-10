package br.com.confchat.mobile.domain.repository.contract

import br.com.confchat.mobile.presenter.veiwmodel.model.PaymentCreditCard

interface IPagBankDomainRepository {
    fun createOrder(data: PaymentCreditCard)
}