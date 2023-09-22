package br.com.confchat.mobile.view.componets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.confchat.mobile.view.Components.ComponentTextField1

@Composable
fun RowScope.ComponentTextFieldSearch(value:String, onChange:(String)->Unit) {
    ComponentTextField1(value = value, onChange = onChange,
        modifier = Modifier.weight(1f))
}

@Preview
@Composable
fun ComponentTextFieldSearchPreview() {
    Row {
        ComponentTextFieldSearch(""){}
    }
}