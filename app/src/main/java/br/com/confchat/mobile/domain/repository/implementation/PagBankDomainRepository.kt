package br.com.confchat.mobile.domain.repository.implementation

import android.util.Log
import br.com.confchat.mobile.data.database.entitys.Payment
import br.com.confchat.mobile.data.database.entitys.getReference
import br.com.confchat.mobile.data.database.repository.contract.IPaymentRepository
import br.com.confchat.mobile.data.network.dto.pagbank.Amount
import br.com.confchat.mobile.data.network.dto.pagbank.Card
import br.com.confchat.mobile.data.network.dto.pagbank.Charge
import br.com.confchat.mobile.data.network.dto.pagbank.CreateOrderDto
import br.com.confchat.mobile.data.network.dto.pagbank.Customer
import br.com.confchat.mobile.data.network.dto.pagbank.Holder
import br.com.confchat.mobile.data.network.dto.pagbank.Item
import br.com.confchat.mobile.data.network.dto.pagbank.PaymentMethod
import br.com.confchat.mobile.data.network.repository.pagbank.IApiPagBankRepository
import br.com.confchat.mobile.domain.repository.contract.IPagBankDomainRepository
import br.com.confchat.mobile.presenter.veiwmodel.model.PaymentCreditCard
import java.util.Date

class PagBankDomainRepository constructor(private val doc: IApiPagBankRepository,private val db: IPaymentRepository) :
    IPagBankDomainRepository {
    override fun createOrder(data: PaymentCreditCard) {
        //tratar validade do cartao
        val expMonth = data.expirationCard.substring(0,2).toInt()
        val expYear = "20${data.expirationCard.substring(2,4)}".toInt()
        val payment = db.create(
            Payment(
                clientName = data.name,
                createAt = Date(),
                amount = data.amont,
                order = ""
            )
        )
        val dto = CreateOrderDto(
            charges = buildList {
                add(Charge(
                    amount = Amount(
                        currency = "BRL",
                        value = data.amont
                    ),
                    description = "",
                    payment_method = PaymentMethod(
                        capture = false,
                        card = Card(
                            exp_month = expMonth,
                            exp_year = expYear,
                            holder = Holder(
                                name = data.nameOnCard
                            ),
                            number = data.numberCard,
                            security_code = data.cvv
                        ),
                        installments = 1,
                        type = "CREDIT_CARD"
                    ),
                    reference_id = payment.getReference()
                ))
            },
            customer = Customer(
                email = data.email,
                name = data.name,
                tax_id = data.cpf
            ),
            items = buildList {
                add(Item(
                    name = "Venda",
                    quantity = 1,
                    unit_amount = data.amont
                ))
            },
            reference_id = payment.getReference()
        )
        val response = doc.createOrder(dto)//TODO : obter estado do apagamento e armazenar ID
        Log.d(this@PagBankDomainRepository::class.java.simpleName,response.toString())
    }
}