package br.com.confchat.mobile.view.screens

import android.content.Intent
import android.widget.Spinner
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.R
import br.com.confchat.mobile.veiwmodel.AuthViewModel
import br.com.confchat.mobile.view.Components.ComponentButton1
import br.com.confchat.mobile.view.Components.ComponentIcon1
import br.com.confchat.mobile.view.Components.ComponentText1
import br.com.confchat.mobile.view.Components.ComponentTextField1
import br.com.confchat.mobile.view.Components.ComponentTextLink1
import br.com.confchat.mobile.view.HomeActivity
import br.com.confchat.mobile.view.AuthenticationActivity
import br.com.confchat.mobile.view.componets.ComponentTextLinkLogout
import br.com.confchat.mobile.view.constants.AuthDoc
import br.com.confchat.mobile.view.constants.Route
import br.com.confchat.mobile.view.enums.IconsLayout
import br.com.confchat.mobile.view.enums.TextFieldType
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@Composable
fun ScreenLogin(navController: NavController,viewModel:AuthViewModel = hiltViewModel()) {
    var loginOrEmai by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current as AuthenticationActivity
    var isLoad by remember{ mutableStateOf(false) }
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                ComponentIcon1(IconsLayout.Logo)
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 18.sp
                )
            }
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.imePadding()
            ) {
                Text(text = stringResource(R.string.login), fontSize = 18.sp, modifier = Modifier.padding(start = 32.dp))
                ComponentTextField1(value = loginOrEmai, onChange = {loginOrEmai = it}, type = TextFieldType.Email)
                ComponentTextField1(value = password, onChange = {password = it}, type = TextFieldType.Password)
                Spacer(modifier = Modifier.height(16.dp))
                if(isLoad){
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }
                else{
                    ComponentButton1(text = stringResource(R.string.logar)) {
                        AuthDoc.login.loginOrEmail = loginOrEmai
                        AuthDoc.login.password = password
                        isLoad = true
                        viewModel.login(){
                            if(it){
                                context.startActivity(Intent(context, HomeActivity::class.java))
                                context.finish()
                            }
                            else{
                                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
                            }
                            isLoad = false
                        }
                    }
                }
            }
        }
        item {
            ComponentTextLinkLogout {
                navController.navigate(Route.Logup)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun ScreenLoginPreview() {
    ConfchatTheme {
        ScreenLogin(rememberNavController(), hiltViewModel())
    }
}