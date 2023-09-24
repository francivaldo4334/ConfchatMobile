package br.com.confchat.mobile.view.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.domain.IAuthDomainRepository
import br.com.confchat.mobile.veiwmodel.AuthViewModel
import br.com.confchat.mobile.view.Components.ComponentButton1
import br.com.confchat.mobile.view.Components.ComponentTextField1
import br.com.confchat.mobile.view.constants.Route

@Composable
fun ScreenVerificationCode(navController: NavController,viewModel: AuthViewModel = hiltViewModel()) {
    var code by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item{
            ComponentTextField1(value = code, onChange = {code = it})
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            ComponentButton1(text = "Confirmar") {
                viewModel.checkVerificationCode(code){
                    if(it)
                        navController.navigate(Route.Login){popUpTo(0)}
                    else
                        Toast.makeText(context,"Codigo invalido",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScreenVerificationCodePreview() {
    ScreenVerificationCode(rememberNavController())
}