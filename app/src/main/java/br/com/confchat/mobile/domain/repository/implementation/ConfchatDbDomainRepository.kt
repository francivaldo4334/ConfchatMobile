package br.com.confchat.mobile.domain.repository.implementation

import br.com.confchat.mobile.data.database.repository.contract.IPaymentRepository
import br.com.confchat.mobile.domain.repository.contract.IConfchatDbDomainRepository

class ConfchatDbDomainRepository constructor(private val db: IPaymentRepository) :
    IConfchatDbDomainRepository {
}