package br.com.confchat.mobile.presenter.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.confchat.mobile.presenter.veiwmodel.ProductViewModel
import br.com.confchat.mobile.presenter.veiwmodel.model.ProductModeltViewModel
import br.com.confchat.mobile.presenter.view.components.ComponentButton1
import br.com.confchat.mobile.presenter.view.components.ComponentTextFieldLabel
import br.com.confchat.mobile.presenter.view.common.visualtransformations.VisualTransformationAmount
import br.com.confchat.mobile.presenter.view.components.ComponentDropUpCard

@Composable
fun registerEditProductScreen(
    expanded: Boolean,
    product: ProductModeltViewModel,
    productViewModel: ProductViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
) {
    var productName by remember { mutableStateOf("") }
    var productValue by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var openDeletedProduct by remember {
        mutableStateOf(false)
    }
    ComponentDropUpCard(expandMenu = expanded, onDismiss = {
        productValue = ""
        productName = ""
        onDismiss()
    }) {
        LaunchedEffect(key1 = Unit, block = {
            productName = product.name
            productValue = product.value.toString()
        })
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
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
                ) {
                    ClickableText(
                        text = AnnotatedString("Excluir produto"),
                        style = TextStyle(color = Color.Red)
                    ) {
                        openDeletedProduct = true
                    }
                }
                Text(text = "Nome", modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.height(4.dp))
                ComponentTextFieldLabel(
                    value = productName,
                    onChange = { productName = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = "Nome do produto",
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Preco", modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.height(4.dp))
                ComponentTextFieldLabel(
                    value = productValue,
                    onChange = { productValue = it },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = VisualTransformationAmount(),
                    keyboardType = KeyboardType.Number,
                    onNext = {
                        focusManager.clearFocus()
                    }
                )
            }
            item {
                ComponentButton1(text = "Atualizar") {
                    product.name = productName
                    product.value = productValue.toInt()
                    productViewModel.update(product)
                    productValue = ""
                    productName = ""
                    onDismiss()
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
    if(openDeletedProduct) {
        AlertDialog(
            containerColor = MaterialTheme.colorScheme.background,
            onDismissRequest = { openDeletedProduct = false },
            title = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    Text(text = "Deletar", fontSize = 14.sp)
                }
            },
            text = {
                Text(text = "Deseja excluir este produto?")
            },
            confirmButton = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { openDeletedProduct = false }) {
                        Text(text = "Cancelar")
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { productViewModel.delete(product);openDeletedProduct = false;onDismiss() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(text = "Excluir")
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun registerEditProductScreenPreview() {
    registerEditProductScreen(true, ProductModeltViewModel("", "", 0)) {

    }
}