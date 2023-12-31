package br.com.confchat.mobile.presenter.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.R
import br.com.confchat.mobile.presenter.veiwmodel.ChatViewModel
import br.com.confchat.mobile.presenter.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.presenter.view.components.ComponentImageProfile
import br.com.confchat.mobile.presenter.view.components.ComponentItemListContact
import br.com.confchat.mobile.presenter.view.components.ComponentTextFieldSearch
import br.com.confchat.mobile.presenter.view.constants.Route
import br.com.confchat.mobile.presenter.view.dialogs.AddFriendDialog
import br.com.confchat.mobile.presenter.view.ui.theme.ConfchatTheme

@Composable
fun ScreenContact(
    lsContacts: List<ContactViewModel>,
    chatViewModel:ChatViewModel = hiltViewModel(),
    navController: NavController
) {
    var search by remember {
        mutableStateOf("")
    }
    var expandDropDownMenu by remember{ mutableStateOf(false) }
    var openNewFriend by remember{
        mutableStateOf(false)
    }
    Box(Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            item{
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
//                    IconButton(onClick = {
//                        expandDropDownMenu = !expandDropDownMenu
//                    }) {
//                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
//                    }
//                    DropdownMenu(expanded = expandDropDownMenu, onDismissRequest = { expandDropDownMenu = false }) {
//
//                    }
                    ComponentTextFieldSearch(value = search, onChange = {search = it})
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Box(contentAlignment = Alignment.Center){
                            ComponentImageProfile()
                        }
                    }
                }
            }
            item {
                Text(text = stringResource(R.string.contatos),modifier = Modifier.padding(start = 16.dp, top = 16.dp))
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(lsContacts){
                ComponentItemListContact(it){
                    chatViewModel.loadMessages(it.chatId)
                    navController.navigate(Route.Chat.replace("{contactId}",it.id))
                }
            }
        }
    }
    AddFriendDialog(openNewFriend,{
        chatViewModel.sendSolicit(it)
    }){
        openNewFriend = false
    }
}
@Preview
@Composable
private fun ScreenContactPreview() {
    ConfchatTheme {
        ScreenContact(
            emptyList(),
            navController = rememberNavController()
        )
    }
}