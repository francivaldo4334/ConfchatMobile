package br.com.confchat.mobile.view.Components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.confchat.mobile.R
import br.com.confchat.mobile.view.enums.IconsLayout

@Composable
fun ComponentIcon1(icon: IconsLayout = IconsLayout.Logo) {
    Icon(
        painter = painterResource(id = R.drawable.ic_confchat),
        contentDescription = null,
        modifier = Modifier.size(40.dp)
    )
}

@Preview
@Composable
private fun ComponentIcon1Preview() {
    ComponentIcon1()
}