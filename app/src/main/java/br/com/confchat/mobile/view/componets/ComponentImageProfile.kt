package br.com.confchat.mobile.view.componets

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComponentImageProfile() {
    ComponentImage("Url profile",
        modifier = Modifier.size(24.dp))
}

@Preview
@Composable
fun ComponentImageProfilePreview() {
    ComponentImageProfile()
}