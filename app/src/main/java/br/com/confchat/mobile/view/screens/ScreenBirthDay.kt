package br.com.confchat.mobile.view.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import br.com.confchat.mobile.view.Components.ComponentTextField1
import br.com.confchat.mobile.view.Components.ComponentTextLink1
import br.com.confchat.mobile.view.AuthenticationActivity
import br.com.confchat.mobile.view.constants.AuthDoc
import br.com.confchat.mobile.view.constants.Route
import br.com.confchat.mobile.view.enums.IconsLayout
import br.com.confchat.mobile.view.enums.TextFieldType
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@Composable
fun ScreenBirthDay(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    var birthDay by remember{
        mutableStateOf("")
    }
    val context = LocalContext.current as AuthenticationActivity
    var isLoad by remember{ mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
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
        item {
            Icon(imageVector = Icons.Outlined.DateRange,contentDescription = null,modifier = Modifier.size(56.dp))
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = stringResource(R.string.adicione_sua_data_de_nascimento), fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(
                text = stringResource(R.string.n_o_ser_mostrado_no_seu_perfil_p_blico).replace("\\n","\n"),
                fontSize = 10.sp,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            ComponentTextField1(value = birthDay, onChange = {birthDay = it}, type = TextFieldType.Date)
        }
        item {
            if(isLoad){
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
            else{
                ComponentButton1(text = stringResource(R.string.continuar_next)) {
                    AuthDoc.register.birthDay = birthDay
                    isLoad = true
                    viewModel.register(){isSuccess,messge ->
                        if(isSuccess){
                            navController.navigate(Route.VerificationCode)
                        }
                        else{
                            Toast.makeText(context,messge,Toast.LENGTH_LONG).show()
                        }
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
private fun ScreenBirthDayPreview() {
    ConfchatTheme {
        ScreenBirthDay(rememberNavController())
    }
}