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
import br.com.confchat.mobile.presenter.veiwmodel.AuthViewModel
import br.com.confchat.mobile.presenter.view.constants.AuthDoc
import br.com.confchat.mobile.presenter.view.constants.Route
import br.com.confchat.mobile.presenter.view.screens.ResetePasswordScreen
import br.com.confchat.mobile.presenter.view.screens.SendResetePasswordScreen
import br.com.confchat.mobile.presenter.view.screens.ScreenBirthDay
import br.com.confchat.mobile.presenter.view.screens.ScreenLogin
import br.com.confchat.mobile.presenter.view.screens.ScreenLogup
import br.com.confchat.mobile.presenter.view.screens.ScreenVerificationCode
import br.com.confchat.mobile.presenter.view.ui.theme.ConfchatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfchatTheme {
                val viewModel:AuthViewModel = hiltViewModel()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    var navController = rememberNavController()
                    val density = LocalDensity.current
                    var AuthDoc = AuthDoc()
                    NavHost(
                        navController = navController,
                        startDestination = Route.Login,
                        modifier = Modifier.padding(top = with(density){
                            WindowInsets.statusBars.getTop(density).toDp()
                        } )
                    ){
                        composable(Route.Login){
                            ScreenLogin(navController,AuthDoc,viewModel)
                        }
                        composable(Route.Logup){
                            ScreenLogup(navController,AuthDoc,viewModel)
                        }
                        composable(Route.BirthDay){
                            ScreenBirthDay(navController,AuthDoc,viewModel)
                        }
                        composable(Route.VerificationCode){
                            ScreenVerificationCode(navController,AuthDoc,viewModel)
                        }
                        composable(Route.SendResetePasssword){
                            SendResetePasswordScreen(navController, AuthDoc,viewModel)
                        }
                        composable(Route.ResetePasssword){
                            ResetePasswordScreen(navController,AuthDoc,viewModel)
                        }
                    }
                }
            }
        }
    }
}