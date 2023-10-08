package br.com.confchat.mobile.view.componets

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import br.com.confchat.mobile.R
import br.com.confchat.mobile.view.componets.customshapes.CustomShapeCardAddMenu
import br.com.confchat.mobile.view.constants.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ComponentBottomNavigate(onClick: (String) -> Unit) {
    val button: @Composable RowScope.(() -> Unit, @Composable () -> Unit) -> Unit =
        { onClick, content ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable(true, onClick = onClick),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    var openAddMenu: Boolean by remember { mutableStateOf(false) }
    val animatedColor by animateColorAsState(if(openAddMenu) MaterialTheme.colorScheme.onBackground.copy(0.5f) else Color.Transparent, label = "")
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
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter){
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
                    Box(modifier = Modifier.padding(16.dp)){
                        Text(text = "Test")
                    }
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
            button({ onClick(Route.Contact) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_contact),
                    contentDescription = null
                )
            }
            button({ onClick(Route.AnonymousChat) }) {
                Icon(painter = painterResource(id = R.drawable.ic_chat), contentDescription = null)
            }
            button({
                openAddMenu = !openAddMenu
            }) {
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
            button({ onClick(Route.Merchant) }) {
                Icon(painter = painterResource(id = R.drawable.ic_store), contentDescription = null)
            }
            button({ onClick(Route.Profile) }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
private fun ComponentBottomNavigatePreview() {
    ComponentBottomNavigate() {}
}