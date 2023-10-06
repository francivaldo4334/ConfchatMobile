package br.com.confchat.mobile.domain

import br.com.confchat.mobile.data.database.AppDatabase
import br.com.confchat.mobile.data.database.repository.IDatabaseRepository

class ConfchatDbDomainRepository constructor(private val db: IDatabaseRepository) : IConfchatDbDomainRepository {
}