package br.com.confchat.mobile.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.veiwmodel.ChatViewModel
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.veiwmodel.model.Message
import br.com.confchat.mobile.view.Components.ComponentChat1
import br.com.confchat.mobile.view.componets.ComponentImageMinContact
import br.com.confchat.mobile.view.componets.ComponentTextFieldMessage
import br.com.confchat.mobile.view.enums.ChatDirection

@Composable
fun ScreenChat(
    contact:ContactViewModel,
    navController: NavController,
    chatViewModel: ChatViewModel = hiltViewModel()
) {
    val listMessage by chatViewModel.listMessage.collectAsState()
    var message by remember{
        mutableStateOf("")
    }
    LaunchedEffect(Unit){
        chatViewModel.loadMessages(contact.chatId)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft,contentDescription = null)
            }
            ComponentImageMinContact(contact.urlImg)
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = contact.name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
            IconButton(onClick = {
                chatViewModel.loadMessages(contact.chatId)
            }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
//            }
        }
        LazyColumn(
            Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ){
            items(listMessage) {
                ComponentChat1(
                    text = it.message,
                    dir = if(it.fromUserId == contact.id){
                        ChatDirection.Left
                    }
                    else{
                        ChatDirection.Rigth
                    }
                )
            }
        }
        Row {
            ComponentTextFieldMessage(value = message, onChange = {message = it}){
                chatViewModel.sendMessage(contact.id,message)
            }
        }
    }
}
@Preview
@Composable
fun ScreenChatPreview() {
    ScreenChat(
//        buildList {
//            this.add(Message("d","teste"))
//            this.add(Message("","teasete"))
//        },
        ContactViewModel("d","","Teste","teste",2,true,false),
        rememberNavController()
    )
}