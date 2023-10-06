package br.com.confchat.mobile.veiwmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.confchat.mobile.domain.IPagBankDomainRepository
import br.com.confchat.mobile.veiwmodel.model.PaymentCreditCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagBankViewModel @Inject constructor(private val domain: IPagBankDomainRepository) : ViewModel(){
    fun initPaymentCreditCard(data: PaymentCreditCard){
        viewModelScope.launch(Dispatchers.IO) {
            domain.createOrder(data)
        }
    }
}