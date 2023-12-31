package br.com.confchat.mobile.presenter.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.presenter.veiwmodel.AuthViewModel
import br.com.confchat.mobile.presenter.veiwmodel.ChatViewModel
import br.com.confchat.mobile.presenter.veiwmodel.ProductViewModel
import br.com.confchat.mobile.presenter.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.presenter.veiwmodel.model.ProductModeltViewModel
import br.com.confchat.mobile.presenter.view.common.ProfileInformations
import br.com.confchat.mobile.presenter.view.components.ComponentBottomNavigate
import br.com.confchat.mobile.presenter.view.constants.Route
import br.com.confchat.mobile.presenter.view.screens.ProfileScreen
import br.com.confchat.mobile.presenter.view.screens.ScreenChat
import br.com.confchat.mobile.presenter.view.screens.ScreenContact
import br.com.confchat.mobile.presenter.view.screens.registerEditProductScreen
import br.com.confchat.mobile.presenter.view.screens.registerNewProductScreen
import br.com.confchat.mobile.presenter.view.ui.theme.ConfchatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel : ChatViewModel = hiltViewModel()
            val viewModelAuth : AuthViewModel = hiltViewModel()
            val viewModelProduct : ProductViewModel = hiltViewModel()
            val listContact : List<ContactViewModel> by viewModel.listContact.collectAsState()
            val profileInformations = ProfileInformations()
            var openScreenNewProduct by remember {
                mutableStateOf(false)
            }
            var openScreenEditProduct by remember {
                mutableStateOf(false)
            }
            var editProduct:ProductModeltViewModel = ProductModeltViewModel("","",0)
            val density = LocalDensity.current
            viewModel.loadContacts()
            ConfchatTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.Contact,
                        modifier = Modifier
                                .padding(bottom = 56.dp, top = with(density){
                                    WindowInsets.statusBars.getTop(density).toDp()
                                })
                        ){
                        composable(Route.Contact){
                            ScreenContact(
                                lsContacts = listContact,
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
                        composable(Route.Profile){
                            ProfileScreen(
                                authViewModel = viewModelAuth,
                                navController = navController,
                                profileInformations = profileInformations,
                                viewModelProduct = viewModelProduct,
                                onNewProduct ={
                                    openScreenNewProduct = true
                                },
                                onEditProduct = {
                                    editProduct = it
                                    openScreenEditProduct = true
                                }
                            )
                        }
                        composable(Route.Merchant){

                        }
                        composable(Route.AnonymousChat){

                        }
                    }
                    ComponentBottomNavigate(
                        onNewProduct ={
                            openScreenNewProduct = true
                        }
                    ){
                        navController.navigate(it){
                            popUpTo(it){
                                inclusive = true
                            }
                        }
                    }
                    registerNewProductScreen(
                        openScreenNewProduct,
                        viewModel = viewModelProduct
                    ){
                        openScreenNewProduct = false;
                    }
                    registerEditProductScreen(
                        openScreenEditProduct,
                        product = editProduct
                    ){
                        openScreenEditProduct = false
                    }
                }
            }
        }
    }
}