package br.com.confchat.mobile.view.screens

import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract.Contacts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.confchat.mobile.R
import br.com.confchat.mobile.veiwmodel.AuthViewModel
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.view.AuthenticationActivity
import br.com.confchat.mobile.view.componets.ComponentImageProfile
import br.com.confchat.mobile.view.componets.ComponentItemListContact
import br.com.confchat.mobile.view.componets.ComponentTextFieldSearch
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme
import dagger.hilt.android.qualifiers.ActivityContext

@Composable
fun ScreenChat(lsContacts: List<ContactViewModel>,authViewModel:AuthViewModel = hiltViewModel()) {
    var search by remember {
        mutableStateOf("")
    }
    var expandDropDownMenu by remember{ mutableStateOf(false) }
    val context = LocalContext.current as Activity
    Box(Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            item{
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, end = 16.dp)
                ) {
                    IconButton(onClick = {
                        expandDropDownMenu = !expandDropDownMenu
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                    DropdownMenu(expanded = expandDropDownMenu, onDismissRequest = { expandDropDownMenu = false }) {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Deslogar")
                            },
                            onClick = {
                                authViewModel.logout()
                                context.startActivity(
                                    Intent(context,AuthenticationActivity::class.java)
                                )
                                context.finish()
                            }
                        )
                    }
                    ComponentTextFieldSearch(value = search, onChange = {search = it})
                    IconButton(onClick = { /*TODO*/ }) {
                        ComponentImageProfile()
                    }
                }
            }
            item {
                Text(text = stringResource(R.string.mensagens),modifier = Modifier.padding(start = 16.dp, top = 16.dp))
            }
            items(lsContacts){
                ComponentItemListContact(it)
            }
        }
        FloatingActionButton(onClick = { /*TODO*/ },modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 16.dp, end = 16.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}
@Preview
@Composable
private fun ScreenChatPreview() {
    ConfchatTheme {
        ScreenChat(emptyList())
    }
}