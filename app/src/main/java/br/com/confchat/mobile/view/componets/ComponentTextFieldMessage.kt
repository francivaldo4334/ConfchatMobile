package br.com.confchat.mobile.view.componets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.confchat.mobile.view.Components.ComponentTextField1
import br.com.confchat.mobile.view.enums.TextFieldType
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@Composable
fun ComponentTextFieldMessage(
    value:String,
    onChange:(String)->Unit,
    onSend:()->Unit
) {
    Row(Modifier.fillMaxWidth()) {
        ComponentTextField1(
            value = value,
            onChange = onChange,
            type = TextFieldType.None,
            modifier = Modifier.weight(1f),
            afterIcon = {
                IconButton(onClick = onSend) {
                    Icon(imageVector = Icons.Default.Send,contentDescription = null)
                }
            },
            background = MaterialTheme.colorScheme.surface
        )
    }
}

@Preview
@Composable
fun ComponentTextFieldMessagePreview() {
    ConfchatTheme {
        ComponentTextFieldMessage("",{}){}
    }
}