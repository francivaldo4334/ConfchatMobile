package br.com.confchat.mobile.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.veiwmodel.AuthViewModel
import br.com.confchat.mobile.veiwmodel.ChatViewModel
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.veiwmodel.model.Message
import br.com.confchat.mobile.view.constants.Route
import br.com.confchat.mobile.view.screens.ScreenChat
import br.com.confchat.mobile.view.screens.ScreenContact
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
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = Route.Contact){
                        composable(Route.Contact){
                            ScreenContact(
                                lsContacts = listContact,
                                authViewModel = viewModelAuth,
                                chatViewModel = viewModel,
                                navController = navController
                            )
                        }
                        composable(Route.Chat){
                            val contactId = it.arguments?.getString("contactId")?:""
                            ScreenChat(
                                contact = listContact.first { it.id == contactId },
                                navController = navController,
                                chatViewModel = viewModel
                            )
                        }
                    }

                }
            }
        }
    }
}