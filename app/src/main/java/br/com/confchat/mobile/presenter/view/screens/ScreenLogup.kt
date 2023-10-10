package br.com.confchat.mobile.presenter.view.screens

import android.util.Log
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
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
import br.com.confchat.mobile.presenter.view.components.ComponentText1
import br.com.confchat.mobile.presenter.view.components.ComponentTextField1
import br.com.confchat.mobile.presenter.view.components.ComponentTextLinkLogin
import br.com.confchat.mobile.presenter.view.constants.AuthDoc
import br.com.confchat.mobile.presenter.view.constants.Route
import br.com.confchat.mobile.presenter.view.enums.IconsLayout
import br.com.confchat.mobile.presenter.view.enums.TextFieldType
import br.com.confchat.mobile.presenter.view.ui.theme.ConfchatTheme
import br.com.confchat.mobile.presenter.view.utils.MyValidUtil

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScreenLogup(navController: NavController,AuthDoc:AuthDoc,viewModel: AuthViewModel = hiltViewModel()) {
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
    var isAcceptPolicyPrivate by remember { mutableStateOf(false) }
    var isAvalidConfirmPassword by remember {
        mutableStateOf(false)
    }
    var isErrorLogin by remember { mutableStateOf(false) }
    var isErrorPassword by remember { mutableStateOf(false) }
    var isErrorEmail by remember { mutableStateOf(false) }
    var isErrorName by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val MyValidUtil = MyValidUtil()
    LaunchedEffect(key1 = Unit, block = {
        name = AuthDoc.register.name
        email = AuthDoc.register.email
        password = AuthDoc.register.password
        login = AuthDoc.register.login
        if(password.isNotBlank()){
            confirPassword = password
        }
    })
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
                    onChange = {
                        login = it.replace(Regex("[\\s]+"),"_")
                        isErrorLogin = !MyValidUtil.validLogin(login)
                    },
                    type = TextFieldType.Login,
                    onFocus = {
                        focusManager.moveFocus(FocusDirection.Next)
                    },
                    isError = isErrorLogin
                )
                ComponentTextField1(
                    value = name,
                    onChange = {
                        name = it
                        isErrorName = !MyValidUtil.validName(name)
                    },
                    type = TextFieldType.UserName,
                    onFocus = {
                        focusManager.moveFocus(FocusDirection.Next)
                    },
                    isError = isErrorName
                )
                ComponentTextField1(
                    value = email,
                    onChange = {
                        email = it.replace(Regex("[\\s]+"),"_")
                        isErrorEmail = !MyValidUtil.validEmail(email)
                    },
                    type = TextFieldType.Email,
                    onFocus = {
                        focusManager.moveFocus(FocusDirection.Next)
                    },
                    isError = isErrorEmail
                )
                ComponentTextField1(
                    value = password,
                    onChange = {
                        password = it
                        isErrorPassword = !MyValidUtil.validPassword(password)
                    },
                    type = TextFieldType.Password,
                    onFocus = {
                        focusManager.moveFocus(FocusDirection.Next)
                    },
                    isError = isErrorPassword
                )
                ComponentTextField1(
                    isError = isAvalidConfirmPassword,
                    value = confirPassword,
                    onChange = {
                        confirPassword = it
                        isAvalidConfirmPassword = !(confirPassword.equals(password))
                    },
                    type = TextFieldType.ConfirmPassword,
                    onFocus = {
                        focusManager.clearFocus()
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isAcceptPolicyPrivate,
                        onCheckedChange = {
                            isAcceptPolicyPrivate = it
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    val fontSize = 12.sp
                    val annotationClick = stringResource(R.string.termos_e_condicoes)
                    var annotationsTerms = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = fontSize)){
                            append("${stringResource(R.string.eu_aceito_os_)} ")
                        }
                        withStyle(style = SpanStyle(color = Color.Blue.copy(0.5f),fontSize = fontSize)){
                            pushStringAnnotation(tag = annotationClick, annotation = annotationClick)
                            append(annotationClick)
                        }
                    }
                    ClickableText(text = annotationsTerms, onClick = {
                        annotationsTerms.getStringAnnotations(it,it)
                            .firstOrNull()?.also {span->
                                if(span.item == annotationClick){
                                    /*TODO*/
                                    Log.d("TAG","TESTEe")
                                }
                            }
                    })
                }
                ComponentButton1(
                    enabled =
                            !isAvalidConfirmPassword &&
                            isAcceptPolicyPrivate &&
                            MyValidUtil.validLogin(login) &&
                            MyValidUtil.validPassword(password) &&
                            MyValidUtil.validEmail(email) &&
                            MyValidUtil.validName(name),
                    text = stringResource(R.string.continuar_next)
                ) {
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
        ScreenLogup(rememberNavController(),AuthDoc())
    }
}