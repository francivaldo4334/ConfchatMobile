package br.com.confchat.mobile.presenter.view.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import br.com.confchat.mobile.presenter.view.components.ComponentButton1
import br.com.confchat.mobile.presenter.view.components.ComponentTextField1
import br.com.confchat.mobile.presenter.view.constants.AuthDoc
import br.com.confchat.mobile.presenter.view.constants.Route
import br.com.confchat.mobile.presenter.view.enums.TextFieldType
import br.com.confchat.mobile.presenter.view.utils.MyValidUtil

@Composable
fun ResetePasswordScreen(navController:NavController,AuthDoc:AuthDoc,viewModel:AuthViewModel = hiltViewModel()) {
    var code by remember{mutableStateOf("")}
    var newPassword by remember { mutableStateOf("") }
    var newConfirmPassword by remember { mutableStateOf("") }
    val MyValidUtil = MyValidUtil()
    val context = LocalContext.current
    var isLoad by remember {
        mutableStateOf(false)
    }
    LazyColumn(modifier = Modifier.fillMaxSize()){
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Codigo de recuperacao", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "insira o codigo de recuperacao enviado para o email associado a conta.", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                ComponentTextField1(
                    value = code,
                    onChange = { code = it },
                    type = TextFieldType.Code,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
            }
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(
                visible = code.length > 5,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(text = "Insira sua nova senha",modifier = Modifier.padding(start = 16.dp), fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    ComponentTextField1(
                        value = newPassword,
                        onChange = { newPassword = it },
                        type = TextFieldType.Password,
                        isError = !MyValidUtil.validPassword(newPassword)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ComponentTextField1(
                        value = newConfirmPassword,
                        onChange = { newConfirmPassword = it },
                        type = TextFieldType.ConfirmPassword,
                        isError = !newConfirmPassword.equals(newPassword)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            if(isLoad){
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
            else {
                val textSucess = stringResource(R.string.senha_redefinida)
                val textException = stringResource(R.string.verifique_sua_conexao_com_a_internet)
                ComponentButton1(
                    text = stringResource(R.string.redefinir_senha),
                    enabled = MyValidUtil.validPassword(newPassword) && newConfirmPassword.equals(
                        newPassword
                    )
                ) {
                    isLoad = true
                    AuthDoc.resetPassword.code = code
                    AuthDoc.resetPassword.newPassword = newPassword
                    viewModel.RequestPassword(code, AuthDoc) {success->
                        if(success){
                            navController.navigate(Route.Login){popUpTo(0)}
                            Toast.makeText(context, textSucess, Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(context, textException, Toast.LENGTH_LONG).show()
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
private fun ResetePasswordScreenPreview() {
    ResetePasswordScreen(rememberNavController(), AuthDoc())
}