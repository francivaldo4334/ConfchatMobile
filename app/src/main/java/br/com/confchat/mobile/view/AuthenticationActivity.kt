package br.com.confchat.mobile.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.veiwmodel.AuthViewModel
import br.com.confchat.mobile.view.constants.Route
import br.com.confchat.mobile.view.screens.ScreenBirthDay
import br.com.confchat.mobile.view.screens.ScreenLogin
import br.com.confchat.mobile.view.screens.ScreenLogup
import br.com.confchat.mobile.view.screens.ScreenVerificationCode
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme
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
                    NavHost(
                        navController = navController,
                        startDestination = Route.Login,
                        modifier = Modifier.padding(top = with(density){
                            WindowInsets.statusBars.getTop(density).toDp()
                        } )
                    ){
                        composable(Route.Login){
                            ScreenLogin(navController,viewModel)
                        }
                        composable(Route.Logup){
                            ScreenLogup(navController,viewModel)
                        }
                        composable(Route.BirthDay){
                            ScreenBirthDay(navController,viewModel)
                        }
                        composable(Route.VerificationCode){
                            ScreenVerificationCode(navController,viewModel)
                        }
                    }
                }
            }
        }
    }
}