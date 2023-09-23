package br.com.confchat.mobile.view.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.R
import br.com.confchat.mobile.veiwmodel.AuthViewModel
import br.com.confchat.mobile.view.Components.ComponentButton1
import br.com.confchat.mobile.view.Components.ComponentIcon1
import br.com.confchat.mobile.view.Components.ComponentTextField1
import br.com.confchat.mobile.view.Components.ComponentTextLink1
import br.com.confchat.mobile.view.AuthenticationActivity
import br.com.confchat.mobile.view.constants.AuthDoc
import br.com.confchat.mobile.view.constants.Route
import br.com.confchat.mobile.view.enums.IconsLayout
import br.com.confchat.mobile.view.enums.TextFieldType

@Composable
fun ScreenBirthDay(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    var birthDay by remember{
        mutableStateOf("yyyy-dd-mm")
    }
    val context = LocalContext.current as AuthenticationActivity
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item{
            ComponentIcon1(IconsLayout.Logo)
        }
        item {
            ComponentTextField1(value = birthDay, onChange = {birthDay = it}, type = TextFieldType.Date)
        }
        item {
            ComponentButton1(text = stringResource(R.string.continuar)) {
                AuthDoc.register.birthDay = birthDay
                viewModel.register(){isSuccess,messge ->
                    if(isSuccess){
                        navController.navigate(Route.VerificationCode)
                    }
                    else{
                        Toast.makeText(context,messge,Toast.LENGTH_LONG).show()
                    }
                }
            }
            ComponentTextLink1 {
                navController.popBackStack()
            }
        }
    }
}

@Preview
@Composable
private fun ScreenBirthDayPreview() {
    ScreenBirthDay(rememberNavController())
}