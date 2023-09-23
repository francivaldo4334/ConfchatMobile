package br.com.confchat.mobile.view.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.confchat.mobile.R
import br.com.confchat.mobile.view.componets.transformations.DateTransformation
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
private fun None(
    value:String,
    onChange:(String)->Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    BasicTextField(
        value = value,
        onValueChange = onChange,
        maxLines = 1,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType)
    )
}
@Composable
private fun TextFieldWithLable(
    label:String,value:String,
    onChange:(String)->Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
){
    Column() {
        Text(
            text = label,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        None(
            value = value,
            onChange = onChange,
            visualTransformation = visualTransformation,
            keyboardType = keyboardType,
            modifier = modifier.fillMaxWidth()
        )
    }
}
@Composable
private fun Email(value:String,onChange:(String)->Unit) {
    TextFieldWithLable(
        stringResource(R.string.email),
        value,
        onChange,
        keyboardType = KeyboardType.Email
    )
}
@Composable
private fun Login(value:String,onChange:(String)->Unit) {
    TextFieldWithLable(
        stringResource(id = R.string.login),
        value,
        onChange
    )
}
@Composable
private fun UserName(value:String,onChange:(String)->Unit) {
    TextFieldWithLable(
        stringResource(R.string.nome_de_usurious),
        value,
        onChange
    )
}
@Composable
private fun Password(value:String,onChange:(String)->Unit) {
    TextFieldWithLable(
        stringResource(R.string.senha),
        value,
        onChange,
        keyboardType = KeyboardType.Password
    )
}
@Composable
private fun ConfirmPassword(value:String,onChange:(String)->Unit) {
    TextFieldWithLable(
        stringResource(R.string.confirmar_senha),
        value,
        onChange,
        keyboardType = KeyboardType.Password
    )
}
@Composable
private fun Date(value:String,onChange:(String)->Unit) {
    None(
        value = value,
        onChange = {
            if(it.length<=8)
                onChange(it)
        },
        keyboardType = KeyboardType.Number,
        visualTransformation = DateTransformation()
    )
}
@Preview
@Composable
private fun ComponentTextField1Preview() {
    ComponentTextField1("test", type = TextFieldType.Password){}
}