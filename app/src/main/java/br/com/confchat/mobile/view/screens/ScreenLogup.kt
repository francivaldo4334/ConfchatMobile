package br.com.confchat.mobile.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.R
import br.com.confchat.mobile.view.Components.ComponentButton1
import br.com.confchat.mobile.view.Components.ComponentIcon1
import br.com.confchat.mobile.view.Components.ComponentText1
import br.com.confchat.mobile.view.Components.ComponentTextField1
import br.com.confchat.mobile.view.Components.ComponentTextLink1
import br.com.confchat.mobile.view.constants.AuthDoc
import br.com.confchat.mobile.view.constants.Route
import br.com.confchat.mobile.view.enums.IconsLayout
import br.com.confchat.mobile.view.enums.TextFieldType
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@Composable
fun ScreenLogup(navController: NavController) {
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
            ComponentIcon1(IconsLayout.Logo)
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
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
                ComponentButton1(text = stringResource(R.string.cadastrar)) {
                    AuthDoc.register.name = name
                    AuthDoc.register.email = email
                    AuthDoc.register.password = password
                    AuthDoc.register.login = login
                    navController.navigate(Route.BirthDay)
                }
            }
        }
        item {
            ComponentTextLink1 {
                navController.popBackStack()
            }
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