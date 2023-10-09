package br.com.confchat.mobile.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import br.com.confchat.mobile.view.componets.ComponentTextLinkLogin
import br.com.confchat.mobile.view.constants.AuthDoc
import br.com.confchat.mobile.view.constants.Route
import br.com.confchat.mobile.view.enums.IconsLayout
import br.com.confchat.mobile.view.enums.TextFieldType
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScreenLogup(navController: NavController,viewModel: AuthViewModel = hiltViewModel()) {
    var login by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirPassword by remember {
        mutableStateOf("")
    }
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.CenterStart
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ComponentIcon1(IconsLayout.Logo)
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 18.sp
                    )
                }
                IconButton(
                    onClick = {navController.popBackStack()},
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.imePadding()
            ) {
                ComponentText1(text = stringResource(R.string.logup))
                Divider(color = MaterialTheme.colorScheme.onBackground)
                ComponentTextField1(
                    value = login,
                    onChange = {login = it},
                    type = TextFieldType.Login
                )
                ComponentTextField1(
                    value = name,
                    onChange = {name = it},
                    type = TextFieldType.UserName
                )
                ComponentTextField1(
                    value = email,
                    onChange = {email = it},
                    type = TextFieldType.Email
                )
                ComponentTextField1(
                    value = password,
                    onChange = {password = it},
                    type = TextFieldType.Password
                )
                ComponentTextField1(
                    value = confirPassword,
                    onChange = {confirPassword = it},
                    type = TextFieldType.ConfirmPassword
                )
                ComponentButton1(text = stringResource(R.string.continuar_next)) {
                    AuthDoc.register.name = name
                    AuthDoc.register.email = email
                    AuthDoc.register.password = password
                    AuthDoc.register.login = login
                    navController.navigate(Route.BirthDay)
                }
            }
        }
        item {
            ComponentTextLinkLogin {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun ScreenLogupPreview() {
    ConfchatTheme {
        ScreenLogup(rememberNavController())
    }
}