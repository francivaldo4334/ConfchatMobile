package br.com.confchat.mobile.view.componets

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComponentImageProfile() {
    ComponentImage("Url profile",
        modifier = Modifier.size(32.dp).clip(CircleShape))
}

@Preview
@Composable
fun ComponentImageProfilePreview() {
    ComponentImageProfile()
}