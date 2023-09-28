package br.com.confchat.mobile.view.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.confchat.mobile.view.enums.ChatDirection
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@Composable
fun ComponentChat1(text:String,dir:ChatDirection = ChatDirection.Rigth) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        contentAlignment =
            when(dir){
                ChatDirection.Left -> {
                    Alignment.CenterStart
                }
                else ->
                    Alignment.CenterEnd
            }
    ){
        Text(
            text = text,
            modifier = Modifier
                .padding(
                    start =
                    when (dir) {
                        ChatDirection.Left -> {
                            16.dp
                        }

                        else ->
                            72.dp
                    },
                    end =
                    when (dir) {
                        ChatDirection.Left -> {
                            72.dp
                        }

                        else ->
                            16.dp
                    }
                )
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if(dir == ChatDirection.Left)
                        MaterialTheme.colorScheme.primary.copy(0.25f)
                    else
                        MaterialTheme.colorScheme.primary
                )
                .padding(16.dp),
            color =
            if(dir == ChatDirection.Left)
                MaterialTheme.colorScheme.onBackground
            else
                MaterialTheme.colorScheme.onPrimary

        )
    }
}

@Preview
@Composable
private fun ComponentChat1Preview() {
    ConfchatTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            ComponentChat1("texttexttexttexttexttexttexttexttexttext")
            ComponentChat1("texttexttexttexttexttexttexttexttexttext", dir = ChatDirection.Left)
        }
    }
}