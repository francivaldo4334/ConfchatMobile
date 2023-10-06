package br.com.confchat.mobile.data.database.repository

import br.com.confchat.mobile.data.database.entitys.Payment

interface IDatabaseRepository {
    fun create(it:Payment):Payment
}