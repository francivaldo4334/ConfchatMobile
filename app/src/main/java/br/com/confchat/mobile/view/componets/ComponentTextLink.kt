package br.com.confchat.mobile.view.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@Composable
fun ComponentTextLink1(textLeft:String = "",textRight:String = "",onClick:()->Unit) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = textLeft, fontSize = 14.sp)
        TextButton(onClick = onClick) {
            Text(text = textRight, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Preview
@Composable
private fun ComponentTextLink1Preview() {
    ConfchatTheme {
        ComponentTextLink1(){}
    }
}