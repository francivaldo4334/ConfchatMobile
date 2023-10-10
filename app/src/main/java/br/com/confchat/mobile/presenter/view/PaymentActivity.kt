package br.com.confchat.mobile.presenter.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.presenter.veiwmodel.PagBankViewModel
import br.com.confchat.mobile.presenter.veiwmodel.model.PaymentCreditCard
import br.com.confchat.mobile.presenter.view.common.CreditCardDoc
import br.com.confchat.mobile.presenter.view.constants.RoutePay
import br.com.confchat.mobile.presenter.view.screens.CustumerScreen
import br.com.confchat.mobile.presenter.view.screens.InsertPayCardInformScreen
import br.com.confchat.mobile.presenter.view.screens.InsertValuePaymentScreen
import br.com.confchat.mobile.presenter.view.ui.theme.ConfchatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfchatTheme {
                val navController = rememberNavController()
                val doc = CreditCardDoc()
                val pagBankViewModel:PagBankViewModel = hiltViewModel()
                val density = LocalDensity.current
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(
                        navController = navController,
                        startDestination = RoutePay.InsertValue,
                        modifier = Modifier.padding(top = with(density){
                            androidx.compose.foundation.layout.WindowInsets.statusBars.getTop(density).toDp()
                        } )
                    ){
                        composable(RoutePay.InsertValue){
                            InsertValuePaymentScreen(navController, doc){
                                doc.amont = it
                            }
                        }
                        composable(RoutePay.CardInform){
                            InsertPayCardInformScreen(navController,doc)
                        }
                        composable(RoutePay.Custumer){
                            CustumerScreen(navController,doc){
                                pagBankViewModel.initPaymentCreditCard(
                                    PaymentCreditCard(
                                        name = doc.name,
                                        cpf = doc.cpf,
                                        email = doc.email,
                                        amont = doc.amont.toInt(),
                                        expirationCard = doc.cardValidate,
                                        numberCard = doc.cardNumber,
                                        nameOnCard = doc.nameOnCard,
                                        cvv = doc.cvv
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}