package br.com.confchat.mobile.presenter.view.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComponentImageContact(urlImg:String) {
    ComponentImage(urlImg,Modifier.size(56.dp))
}
@Composable
fun ComponentImageMinContact(urlImg:String) {
    ComponentImage(urlImg,modifier = Modifier.size(32.dp).clip(CircleShape))
}

@Preview
@Composable
fun ComponentImageContactPreview() {
    ComponentImageContact("")
}