package br.com.confchat.mobile.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.confchat.mobile.veiwmodel.AuthViewModel
import br.com.confchat.mobile.veiwmodel.ChatViewModel
import br.com.confchat.mobile.veiwmodel.UserViewModel
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.veiwmodel.model.Device
import br.com.confchat.mobile.view.screens.ScreenChat
import br.com.confchat.mobile.view.screens.ScreenHome
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel : ChatViewModel = hiltViewModel()
            val viewModelAuth : AuthViewModel = hiltViewModel()
            val listContact : List<ContactViewModel> by viewModel.listContact.collectAsState()
            viewModel.loadContacts()
            ConfchatTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ScreenChat(lsContacts = listContact,viewModelAuth)
                }
            }
        }
    }
}