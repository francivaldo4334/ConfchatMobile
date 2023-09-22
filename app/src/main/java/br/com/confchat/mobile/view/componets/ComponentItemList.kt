package br.com.confchat.mobile.view.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComponentItemList(modifier: Modifier = Modifier,content:@Composable RowScope.() -> Unit) {
    Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),content = content,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically)
}

@Preview
@Composable
fun ComponentItemListPreview() {
    ComponentItemList{

    }
}