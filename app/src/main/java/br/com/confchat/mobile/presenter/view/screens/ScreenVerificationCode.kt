package br.com.confchat.mobile.presenter.view.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.R
import br.com.confchat.mobile.presenter.veiwmodel.AuthViewModel
import br.com.confchat.mobile.presenter.view.components.ComponentButton1
import br.com.confchat.mobile.presenter.view.components.ComponentIcon1
import br.com.confchat.mobile.presenter.view.components.ComponentTextField1
import br.com.confchat.mobile.presenter.view.constants.AuthDoc
import br.com.confchat.mobile.presenter.view.constants.Route
import br.com.confchat.mobile.presenter.view.enums.IconsLayout
import br.com.confchat.mobile.presenter.view.enums.TextFieldType
import br.com.confchat.mobile.presenter.view.ui.theme.ConfchatTheme

@Composable
fun ScreenVerificationCode(navController: NavController,AuthDoc:AuthDoc,viewModel: AuthViewModel = hiltViewModel()) {

    var code by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    var isLoad by remember{ mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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
        item{
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = null,
                modifier = Modifier.size(56.dp))
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = stringResource(R.string.inserir_c_digo_de_verifica_o), fontSize = 14.sp, fontWeight = FontWeight.Bold)
            var bs = buildAnnotatedString {
                append("Insira o código que enviamos para o seu\n email ")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
                    append("#${AuthDoc.register.email}")
                }
            }
            Text(
                text = bs,
                fontSize = 10.sp,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center
            )
            TextButton(onClick = {
                viewModel.resendVerificationCode(AuthDoc){
                    if(it)
                        Toast.makeText(context,"Codigo enviado",Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(context,"Errof:Codigo nao enviado",Toast.LENGTH_LONG).show()
                }
            }) {
                Text(text = stringResource(R.string.reenviar_c_digo))
            }
            Spacer(modifier = Modifier.height(32.dp))
            ComponentTextField1(value = code, onChange = {code = it}, type = TextFieldType.Code)
        }
        item {
            Spacer(modifier = Modifier.height(32.dp))
            if(isLoad) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            else {
                ComponentButton1(text = stringResource(R.string.confirmar)) {
                    isLoad = true
                    viewModel.checkVerificationCode(AuthDoc,code) {
                        if (it) {
                            AuthDoc.login.loginOrEmail = AuthDoc.register.login
                            AuthDoc.login.password = AuthDoc.register.password
                            navController.navigate(Route.Login) { popUpTo(0) }
                        }
                        else
                            Toast.makeText(context, "Codigo invalido", Toast.LENGTH_LONG).show()
                        isLoad = false
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { navController.popBackStack() }) {
                Text(text = stringResource(R.string.voltar))
            }
        }
    }
}

@Preview
@Composable
private fun ScreenVerificationCodePreview() {
    AuthDoc().register.email = "teste@teste.com"
    ConfchatTheme {
        ScreenVerificationCode(rememberNavController(),AuthDoc())
    }
}