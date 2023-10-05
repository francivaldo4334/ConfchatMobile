package br.com.confchat.mobile.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.common.MyConstants
import br.com.confchat.mobile.view.common.CreditCardDoc
import br.com.confchat.mobile.view.constants.Route
import br.com.confchat.mobile.view.constants.RoutePay
import br.com.confchat.mobile.view.screens.InsertPayCardInformScreen
import br.com.confchat.mobile.view.screens.InsertValuePaymentScreen
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfchatTheme {
                val navController = rememberNavController()
                val creditCardDoc = CreditCardDoc()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(
                        navController = navController,
                        startDestination = RoutePay.InsertValue
                    ){
                        composable(RoutePay.InsertValue){
                            InsertValuePaymentScreen(navController, creditCardDoc){
                                creditCardDoc.amont = it
                            }
                        }
                        composable(RoutePay.CardInform){
                            InsertPayCardInformScreen(navController,creditCardDoc)
                        }
                    }
                }
            }
        }
    }
}