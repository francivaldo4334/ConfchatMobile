package br.com.confchat.mobile.view.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.confchat.mobile.view.Components.ComponentTextFieldLabel
import br.com.confchat.mobile.view.common.visualtransformations.VisualTransformationAmount
import br.com.confchat.mobile.view.componets.ComponentDropUpCard

@Composable
fun registerEditProductScreen(expanded: Boolean, onDismiss: () -> Unit) {
    var productName by remember{ mutableStateOf("") }
    var productValue by remember{ mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    ComponentDropUpCard(expandMenu = expanded, onDismiss = {
        productValue = ""
        productName = ""
        onDismiss()
    }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            item {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = "Editar produto",
                        modifier = Modifier.align(Alignment.Center)
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, top = 16.dp),
                    contentAlignment = Alignment.CenterEnd
                ){
                    ClickableText(
                        text = AnnotatedString("Excluir produto"),
                        style = TextStyle(color = Color.Red)
                    ) {
                        /*TODO*/
                        var toast = Toast(context)
                        toast.setText("hellow")
                        toast.duration = Toast.LENGTH_LONG
                        toast.show()
                    }
                }
                Text(text = "Nome",modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.height(4.dp))
                ComponentTextFieldLabel(
                    value = productName,
                    onChange = {productName = it},
                    modifier = Modifier.fillMaxWidth(),
                    label = "Nome do produto",
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Preco",modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.height(4.dp))
                ComponentTextFieldLabel(
                    value = productValue,
                    onChange = {productValue = it},
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = VisualTransformationAmount(),
                    keyboardType = KeyboardType.Number,
                    onNext = {
                        focusManager.clearFocus()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun registerEditProductScreenPreview() {
    registerEditProductScreen(true){

    }
}