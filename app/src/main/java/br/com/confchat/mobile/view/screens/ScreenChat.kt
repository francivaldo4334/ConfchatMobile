package br.com.confchat.mobile.view.screens

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
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.confchat.mobile.R
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.view.componets.ComponentImageProfile
import br.com.confchat.mobile.view.componets.ComponentItemListContact
import br.com.confchat.mobile.view.componets.ComponentTextFieldSearch
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@Composable
fun ScreenChat(lsContacts: List<ContactViewModel>) {
    var search by remember {
        mutableStateOf("")
    }
    Box(Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            item{
                Row (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
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
        FloatingActionButton(onClick = { /*TODO*/ },modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 16.dp, end = 16.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}
@Preview
@Composable
fun ScreenChatPreview() {
    ConfchatTheme {
        ScreenChat(emptyList())
    }
}