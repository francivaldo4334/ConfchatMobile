package br.com.confchat.mobile.view.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.confchat.mobile.view.Components.ComponentButton1
import br.com.confchat.mobile.view.componets.ComponentTextFieldSearch

@Composable
fun AddFriendDialog(open:Boolean, onClick:(String)->Unit, onDismiss:()->Unit) {
    var login by remember {
        mutableStateOf("")
    }
    if(open) {
        Dialog(onDismissRequest = onDismiss) {
            Card {
                Column(
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        ComponentTextFieldSearch(login) {
                            login = it
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ComponentButton1(
                        text = "Solicitar",
                        onClick = {
                            onClick(login)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddFriendDialogPreview() {
    AddFriendDialog(true,{}){}
}