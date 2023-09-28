package br.com.confchat.mobile.view.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun ComponentText1(text:String,fontSize:TextUnit = TextUnit.Unspecified) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        fontSize = fontSize
    )
}

@Preview
@Composable
private fun ComponentText1Preview() {
    ComponentText1("test")
}