package br.com.confchat.mobile.view.componets

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComponentImageContact(urlImg:String) {
    ComponentImage(urlImg,Modifier.size(56.dp))
}

@Preview
@Composable
fun ComponentImageContactPreview() {
    ComponentImageContact("")
}