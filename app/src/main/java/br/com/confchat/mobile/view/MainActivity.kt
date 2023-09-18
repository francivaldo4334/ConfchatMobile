package br.com.confchat.mobile.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.veiwmodel.AuthViewModel
import br.com.confchat.mobile.view.constants.Route
import br.com.confchat.mobile.view.screens.ScreenBirthDay
import br.com.confchat.mobile.view.screens.ScreenLogin
import br.com.confchat.mobile.view.screens.ScreenLogup
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfchatTheme {
                val viewModel:AuthViewModel = hiltViewModel()
                LaunchedEffect(key1 = Unit, block = {
                    delay(2000)
                    if(viewModel.checkLogin()){
                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                        finish()
                    }
                })
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    var navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Route.Login){
                        composable(Route.Login){
                            ScreenLogin(navController,viewModel)
                        }
                        composable(Route.Logup){
                            ScreenLogup(navController)
                        }
                        composable(Route.BirthDay){
                            ScreenBirthDay(navController,viewModel)
                        }
                    }
                }
            }
        }
    }
}