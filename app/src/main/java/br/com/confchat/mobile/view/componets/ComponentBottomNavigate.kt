package br.com.confchat.mobile.view.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.confchat.mobile.R
import br.com.confchat.mobile.view.constants.Route

@Composable
fun ComponentBottomNavigate(onClick:(String) ->Unit) {
    val button:@Composable RowScope.(()->Unit, @Composable ()->Unit)->Unit = {onClick,content ->
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable(true, onClick = onClick),
            contentAlignment = Alignment.Center
        ){
            content()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            button({ onClick(Route.Contact) }) {
                Icon(painter = painterResource(id = R.drawable.ic_contact), contentDescription = null)
            }
            button({ onClick(Route.AnonymousChat) }) {
                Icon(painter = painterResource(id = R.drawable.ic_chat), contentDescription = null)
            }
            button({ onClick(Route.Add) }) {
                Box(
                    modifier = Modifier.size(40.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ){
                    Icon(imageVector = Icons.Default.Add, contentDescription = null,tint = MaterialTheme.colorScheme.background)
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
    ComponentBottomNavigate(){}
}