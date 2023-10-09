package br.com.confchat.mobile.view.componets

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.confchat.mobile.R
import br.com.confchat.mobile.view.PaymentActivity
import br.com.confchat.mobile.view.componets.customshapes.CustomShapeCardAddMenu
import br.com.confchat.mobile.view.constants.Route

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ComponentBottomNavigate(onNewProduct:()->Unit,onClick: (String) -> Unit) {
    var openAddMenu: Boolean by remember { mutableStateOf(false) }
    var stateMenu: String by remember { mutableStateOf(Route.Contact) }
    val context = LocalContext.current
    val button: @Composable RowScope.(String, @Composable (Color) -> Unit) -> Unit =
        { route, content ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable(true, onClick = {
                        when (route) {
                            Route.Add -> {
                                openAddMenu = !openAddMenu
                            }

                            else -> {
                                if (openAddMenu) {
                                    openAddMenu = false
                                } else {
                                    onClick(route)
                                    stateMenu = route
                                }
                            }
                        }
                    }),
                contentAlignment = Alignment.Center
            ) {
                content(
                    if (stateMenu == route)
                        MaterialTheme.colorScheme.onBackground
                    else
                        MaterialTheme.colorScheme.onBackground.copy(0.5f)
                )
            }
        }
    val animatedColor by animateColorAsState(
        if (openAddMenu) MaterialTheme.colorScheme.onBackground.copy(
            0.5f
        ) else Color.Transparent, label = ""
    )
    var showOpetionsMerchant by remember {
        mutableStateOf(true)
    }
    val listAddMenuOptions: List<Triple<String, () -> Unit, @Composable () -> Unit>> = buildList {
        add(Triple(
            "Contato", {

            }, {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            }
        ))

    }
    val listAddMenuOptionsMerchants: List<Triple<String, () -> Unit, @Composable () -> Unit>> = buildList {
        add(Triple(
            "Vender", {
                context.startActivity(Intent(context, PaymentActivity::class.java))
            }, {
                Icon(
                    painter = painterResource(id = R.drawable.ic_card_payment),
                    contentDescription = null
                )
            }
        ))
        add(Triple(
            "Produto", {
                onNewProduct()
            }, {
                Icon(
                    painter = painterResource(id = R.drawable.ic_product),
                    contentDescription = null
                )
            }
        ))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedColor),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = openAddMenu,
            enter = scaleIn() + slideInVertically { it },
            exit = scaleOut() + slideOutVertically { it },
            modifier = Modifier
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { openAddMenu = false })
                Card(
                    shape = CustomShapeCardAddMenu(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
                ) {
                    val bntItem: @Composable (
                        String,
                        () -> Unit,
                        @Composable () -> Unit
                    ) -> Unit = { label, onClickItem, content ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    openAddMenu = false
                                    onClickItem()
                                },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            content()
                            Text(text = label, fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(12.dp)
                            .width(
                                if(!showOpetionsMerchant){
                                    if (listAddMenuOptions.size <= 1)
                                        56.dp
                                    else if (listAddMenuOptions.size <= 2)
                                        (2 * 56).dp
                                    else
                                        (3 * 56).dp
                                }
                                else{
                                    if (listAddMenuOptions.size <= 1 && listAddMenuOptionsMerchants.size <= 1)
                                        56.dp
                                    else if (listAddMenuOptions.size <= 2 && listAddMenuOptionsMerchants.size <= 2)
                                        (2 * 56).dp
                                    else
                                        (3 * 56).dp
                                }
                            ),
                        columns = GridCells.Adaptive(56.dp),
                        horizontalArrangement = Arrangement.Center,
                        content = {
                            items(listAddMenuOptions) {
                                bntItem(it.first, it.second, it.third)
                            }
                            if(showOpetionsMerchant){
                                item(
                                    span = {GridItemSpan(maxLineSpan)}
                                ) {
                                    Divider()
                                }
                                items(listAddMenuOptionsMerchants) {
                                    bntItem(it.first, it.second, it.third)
                                }
                            }
                        })
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            button(Route.Contact) {
                Icon(
                    tint = it,
                    painter = painterResource(id = R.drawable.ic_contact),
                    contentDescription = null
                )
            }
//            button(Route.AnonymousChat) {
//                Icon(
//                    tint = it,
//                    painter = painterResource(id = R.drawable.ic_chat),
//                    contentDescription = null
//                )
//            }
            button(Route.Add) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            }
//            button(Route.Merchant) {
//                Icon(
//                    tint = it,
//                    painter = painterResource(id = R.drawable.ic_store),
//                    contentDescription = null
//                )
//            }
            button(Route.Profile) {
                Icon(
                    tint = it,
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun ComponentBottomNavigatePreview() {
    ComponentBottomNavigate({}) {}
}