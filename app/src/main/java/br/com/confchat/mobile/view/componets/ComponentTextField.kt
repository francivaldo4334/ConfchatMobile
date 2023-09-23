package br.com.confchat.mobile.view.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.confchat.mobile.R
import br.com.confchat.mobile.view.enums.TextFieldType

@Composable
fun ComponentTextField1(value:String,type:TextFieldType = TextFieldType.None,modifier: Modifier = Modifier,onChange:(String)->Unit) {
    when(type){
        TextFieldType.Email ->{
            Email(value = value,onChange = onChange)
        }
        TextFieldType.Login ->{
            Login(value = value,onChange = onChange)
        }
        TextFieldType.UserName ->{
            UserName(value = value,onChange = onChange)
        }
        TextFieldType.Password ->{
            Password(value = value,onChange = onChange)
        }
        TextFieldType.ConfirmPassword ->{
            ConfirmPassword(value = value,onChange = onChange)
        }
        TextFieldType.Date ->{
            Date(value = value, onChange = onChange)
        }
        else -> {
            None(value = value,onChange = onChange, modifier = modifier)
        }
    }
}
@Composable
private fun None(value:String,onChange:(String)->Unit,modifier: Modifier = Modifier) {
    BasicTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp)
            .height(50.dp)
    )
}
@Composable
private fun Email(value:String,onChange:(String)->Unit) {
    Column {
        Text(
            text = stringResource(R.string.email),
            modifier = Modifier.padding(start = 16.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp)
                .height(50.dp)
        )
    }
}
@Composable
private fun Login(value:String,onChange:(String)->Unit) {
    Column {
        Text(
            text = stringResource(id = R.string.login),
            modifier = Modifier.padding(start = 16.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp)
                .height(50.dp)
        )
    }
}
@Composable
private fun UserName(value:String,onChange:(String)->Unit) {
    Column {
        Text(
            text = stringResource(R.string.nome_de_usurious),
            modifier = Modifier.padding(start = 16.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp)
                .height(50.dp)
        )
    }
}
@Composable
private fun Password(value:String,onChange:(String)->Unit) {
    Column {
        Text(
            text = stringResource(R.string.senha),
            modifier = Modifier.padding(start = 16.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp)
                .height(50.dp)
        )
    }
}
@Composable
private fun ConfirmPassword(value:String,onChange:(String)->Unit) {
    Column {
        Text(
            text = stringResource(R.string.confirmar_senha),
            modifier = Modifier.padding(start = 16.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp)
                .height(50.dp)
        )
    }
}
@Composable
private fun Date(value:String,onChange:(String)->Unit) {
    BasicTextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp)
            .height(50.dp),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
    )
}
@Preview
@Composable
private fun ComponentTextField1Preview() {
    ComponentTextField1("test", type = TextFieldType.Password){}
}