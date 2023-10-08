package br.com.confchat.mobile.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.confchat.mobile.R
import br.com.confchat.mobile.veiwmodel.ProductViewModel
import br.com.confchat.mobile.veiwmodel.model.ProductModeltViewModel
import br.com.confchat.mobile.view.common.CreditCardDoc
import br.com.confchat.mobile.view.common.ProfileInformations
import br.com.confchat.mobile.view.componets.ComponentImageProfile
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@Composable
fun ProfileScreen(
    viewModelProduct:ProductViewModel = hiltViewModel(),
    navController: NavController,
    profileInformations: ProfileInformations,
    onNewProduct:()->Unit
) {
    val listProduct: List<ProductModeltViewModel> by viewModelProduct.listProduct.collectAsState()
    var openMoreOptions by remember{
        mutableStateOf(false)
    }
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
            Text(
                text = stringResource(R.string.meu_perfil),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
            Row(
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.ic_store), contentDescription = null)
                }
                IconButton(onClick = { openMoreOptions = true }) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                }
                DropdownMenu(expanded = openMoreOptions, onDismissRequest = { openMoreOptions = false }) {
                    DropdownMenuItem(text = {
                        Text(text = "Novo Produto")
                    }, onClick = { onNewProduct();openMoreOptions = false })
                }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ){
            item(span = { GridItemSpan(maxLineSpan) }){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    ComponentImageProfile(56.dp)
                    Text(text = profileInformations.userName, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text(text = profileInformations.userDescription, fontSize = 12.sp)
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        val buttonFlower: @Composable RowScope.(Int,String,()->Unit)->Unit = {n,text,onClick->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { onClick() }
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ){
                                val bs = buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)){
                                        append("${n.toString()}\n")
                                    }
                                    withStyle(style = SpanStyle(fontSize = 12.sp)){
                                        append(text)
                                    }
                                }
                                Text(text = bs, textAlign = TextAlign.Center)
                            }
                        }
                        buttonFlower(profileInformations.fllowers,"Seguidores"){
                            //TODO
                        }
                        buttonFlower(profileInformations.fllowing,"Seguindo"){
                            //TODO
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Loja", fontWeight = FontWeight.Bold)
                    }
                }
            }
            if(listProduct.isEmpty()){
                item(span = { GridItemSpan(maxLineSpan) }){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_product),
                            contentDescription = null,
                            modifier = Modifier.size(72.dp),
                            tint = MaterialTheme.colorScheme.onBackground.copy(0.5f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = stringResource(R.string.adicione_seus_produtos).replace("\\n","\n"), fontWeight = FontWeight.Light)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = onNewProduct) {
                            Text(text = stringResource(R.string.adicionar_produtos))
                        }
                    }
                }
            }
            else{
                items(
                    items =listProduct
                ){
                    Column(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .aspectRatio(1f),
                            contentAlignment = Alignment.Center
                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_product),
                                contentDescription = null,
                                modifier = Modifier.size(72.dp),
                                tint = MaterialTheme.colorScheme.onBackground.copy(0.5f)
                            )
                        }
                        Text(text = it.name)
                        val mask = CreditCardDoc().mask
                        Text(text = mask(it.value.toString()), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ConfchatTheme {
        ProfileScreen(navController = rememberNavController(), profileInformations =  ProfileInformations()){}
    }
}