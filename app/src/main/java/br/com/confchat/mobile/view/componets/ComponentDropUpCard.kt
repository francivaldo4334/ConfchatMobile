package br.com.confchat.mobile.view.componets

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComponentDropUpCard(
    expandMenu: Boolean,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    val config = LocalConfiguration.current
    val hScreen = config.screenHeightDp
    AnimatedVisibility(
        visible = expandMenu,
        enter = slideInVertically { it },
        exit = slideOutVertically { it },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.5f))
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((hScreen / 4).dp)
                    .clickable(onClick = onDismiss)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                    .background(MaterialTheme.colorScheme.background)
            ) {
                content()
            }
        }
    }
    BackHandler(expandMenu) {
        onDismiss()
    }
}

@Preview
@Composable
private fun ComponentDropUpCardPreview() {
    ComponentDropUpCard(true, {}) {

    }
}