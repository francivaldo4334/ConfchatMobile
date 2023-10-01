package br.com.confchat.mobile.view.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.confchat.mobile.R
import br.com.confchat.mobile.view.enums.TextFieldType
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@Composable
fun ComponentTextField1(value:String, type:TextFieldType = TextFieldType.None, modifier: Modifier = Modifier, afterIcon:@Composable ()->Unit = {},beforeIcon:@Composable ()->Unit = {}, background:Color = MaterialTheme.colorScheme.onPrimary, onChange:(String)->Unit) {
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
            None(
                value = value,
                onChange = onChange,
                modifier = modifier,
                afterIcon = afterIcon,
                background = background,
                beforeIcon = beforeIcon
            )
        }
    }
}
@Composable
private fun None(
    value:String,
    onChange:(String)->Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    background: Color = MaterialTheme.colorScheme.background,
    afterIcon:@Composable ()->Unit = {},
    beforeIcon:@Composable ()->Unit = {},
    label:String = ""
) {
    BasicTextField(
        value = value,
        onValueChange = onChange,
        maxLines = 1,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        decorationBox = {
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = background),
                modifier = modifier
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    beforeIcon()
                    Box(modifier = modifier
                        .padding(16.dp)){
                        if(value.isEmpty()){
                            Text(text = label, fontSize = 14.sp, color = Color.Unspecified.copy(0.5f))
                        }
                        it()
                    }
                    afterIcon()
                }
            }
        }
    )
}
@Composable
private fun TextFieldWithLable(
    label:String,value:String,
    onChange:(String)->Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    beforeIcon: @Composable () -> Unit = {}
){
    None(
        value = value,
        onChange = onChange,
        visualTransformation = visualTransformation,
        keyboardType = keyboardType,
        modifier = modifier.fillMaxWidth(),
        beforeIcon = {
            Box(modifier = Modifier.padding(start = 8.dp,top = 8.dp, bottom = 8.dp)){
                beforeIcon()
            }
        },
        label = label
    )
}
@Composable
private fun Email(value:String,onChange:(String)->Unit) {
    TextFieldWithLable(
        label = stringResource(R.string.seu_email_gmail_com),
        value = value,
        onChange = onChange,
        keyboardType = KeyboardType.Email,
        beforeIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null
            )
        }
    )
}
@Composable
private fun Login(value:String,onChange:(String)->Unit) {
    TextFieldWithLable(
        stringResource(id = R.string.login),
        value,
        onChange,
        beforeIcon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )
        }
    )
}
@Composable
private fun UserName(value:String,onChange:(String)->Unit) {
    TextFieldWithLable(
        stringResource(R.string.nome_de_usurious),
        value,
        onChange,
        beforeIcon = {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = null
            )
        }
    )
}
@Composable
private fun Password(value:String,onChange:(String)->Unit) {
    TextFieldWithLable(
        stringResource(R.string.senha),
        value,
        onChange,
        keyboardType = KeyboardType.Password,
        beforeIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null
            )
        }
    )
}
@Composable
private fun ConfirmPassword(value:String,onChange:(String)->Unit) {
    TextFieldWithLable(
        stringResource(R.string.confirmar_senha),
        value,
        onChange,
        keyboardType = KeyboardType.Password,
        beforeIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null
            )
        }
    )
}
@Composable
private fun Date(value:String,onChange:(String)->Unit) {
    val card : @Composable (Int,String,String)->Unit = { init,value,default ->
        val gerarStringComPadrao : (String,String)->String = {prefixo,padrao->
            val caracteresAleatorios = padrao.substring(prefixo.length)
            "$prefixo$caracteresAleatorios"
        }
        Card(
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text =
                if(init >= 0 && init < value.length){
                    val subString = value.substring(init,if(value.length <  (init + default.length)) value.length else default.length + init).toString()
                    gerarStringComPadrao(subString,default)
                }
                else{
                    default
                },
                modifier = Modifier.padding(4.dp),
                fontSize = 24.sp
            )
        }
    }
    BasicTextField(
        value = value,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        decorationBox = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                card(0,value,"DD")
                card(2,value,"MM")
                card(4,value,"AAAA")
            }
        }
    )
}
@Preview
@Composable
private fun ComponentTextField1Preview() {
    ConfchatTheme {
        Date(""){}
    }
}