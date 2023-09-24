package br.com.confchat.mobile.view.componets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.confchat.mobile.view.Components.ComponentTextField1
import br.com.confchat.mobile.view.enums.TextFieldType

@Composable
fun ComponentTextFieldMessage(
    value:String,
    onChange:(String)->Unit
) {
    Row(Modifier.fillMaxWidth()) {
        ComponentTextField1(
            value = value,
            onChange = onChange,
            type = TextFieldType.None,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Create,contentDescription = null)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Send,contentDescription = null)
        }
    }
}

@Preview
@Composable
fun ComponentTextFieldMessagePreview() {
    ComponentTextFieldMessage(""){}
}