package br.com.confchat.mobile.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.confchat.mobile.veiwmodel.AuthViewModel
import br.com.confchat.mobile.view.screens.SplashScreen
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
@AndroidEntryPoint
class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: AuthViewModel = hiltViewModel()
            LaunchedEffect(key1 = Unit, block = {
                delay(2000)
                viewModel.checkLogin(){
                    if(it){
                        startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
                        finish()
                    }
                    else{
                        startActivity(Intent(this@SplashScreenActivity, AuthenticationActivity::class.java))
                        finish()
                    }
                }
            })
            ConfchatTheme {
                Surface {
                    SplashScreen()
                }
            }
        }
    }
}