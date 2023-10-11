package br.com.confchat.mobile.presenter.view.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.R
import br.com.confchat.mobile.presenter.veiwmodel.AuthViewModel
import br.com.confchat.mobile.presenter.view.HomeActivity
import br.com.confchat.mobile.presenter.view.components.ComponentButton1
import br.com.confchat.mobile.presenter.view.components.ComponentTextField1
import br.com.confchat.mobile.presenter.view.constants.AuthDoc
import br.com.confchat.mobile.presenter.view.constants.Route
import br.com.confchat.mobile.presenter.view.enums.TextFieldType

@Composable
fun SendResetePasswordScreen(navController: NavController,AuthDoc:AuthDoc,viewModel:AuthViewModel = hiltViewModel()) {
    var email by remember{ mutableStateOf("")}
    var isLoad by remember{ mutableStateOf(false) }
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ){
        item {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
        item {
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(stringResource(R.string.recuperacao_de_senha), fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = stringResource(R.string.insira_o_email_associado_a_sua_conta_e_verifique_sua_caixa_de_entrada), fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            ComponentTextField1(
                value = email,
                onChange = { email = it },
                type = TextFieldType.Email
            )
            Spacer(modifier = Modifier.height(32.dp))
            if(isLoad){
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
            else{
                ComponentButton1(
                    text = stringResource(R.string.enviar_email_de_recuperacao)
                ) {
                    isLoad = true
                    AuthDoc.resetPassword.email = email
                    viewModel.sendRequestPassword(email){success->
                        if(success){
                            navController.navigate(Route.ResetePasssword)
                        }
                        else{
                            Toast.makeText(context,"Verifique sua conexao com a internet", Toast.LENGTH_LONG).show()
                        }
                        isLoad = false
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun SendResetePasswordScreenPreview() {
    SendResetePasswordScreen(rememberNavController(),AuthDoc())
}