package br.com.confchat.mobile.view.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComponentLog1() {
    Box(
        modifier = Modifier
            .width(70.dp)
            .height(42.dp)
            .background(MaterialTheme.colorScheme.onBackground),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "LOGO", fontSize = 14.sp, color = MaterialTheme.colorScheme.background)
    }
}

@Preview
@Composable
private fun ComponentLog1Preview() {
    ComponentLog1()
}