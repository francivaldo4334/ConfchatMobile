package br.com.confchat.mobile.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.confchat.mobile.view.Components.ComponentButton1
import br.com.confchat.mobile.view.Components.ComponentTextField1
import br.com.confchat.mobile.view.Components.ComponentTextFieldLabel
import br.com.confchat.mobile.view.common.visualtransformations.VisualTransformationAmount
import br.com.confchat.mobile.view.componets.ComponentDropUpCard

@Composable
fun registerNewProductScreen(expanded: Boolean, onDismiss: () -> Unit) {
    var productName by remember{ mutableStateOf("") }
    var productValue by remember{ mutableStateOf("") }
    val focusManager = LocalFocusManager.current
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
                        text = "Adicionar produto",
                        modifier = Modifier.align(Alignment.Center)
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
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
            item {
                ComponentButton1(text = "+ Adicionar produto") {
                    
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun registerNewProductScreenPreview() {
    registerNewProductScreen(true) {}
}