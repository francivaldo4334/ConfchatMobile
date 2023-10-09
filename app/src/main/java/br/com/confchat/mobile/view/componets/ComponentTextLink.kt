package br.com.confchat.mobile.view.Components

import android.text.style.ClickableSpan
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.confchat.mobile.view.ui.theme.ConfchatTheme

@Composable
fun ComponentTextLink1(textLeft:String = "",textRight:String = "",onClick:()->Unit) {
    var text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 14.sp),){
            append(textLeft)
        }
        withStyle(style = SpanStyle(fontSize = 16.sp,color = MaterialTheme.colorScheme.onSurfaceVariant)){
            pushStringAnnotation(textRight,textRight)
            append(textRight)
        }
    }
    ClickableText(text = text, onClick = {
        text.getStringAnnotations(textRight,it,it).firstOrNull()?.let {
            onClick()
        }
    })
}

@Preview
@Composable
private fun ComponentTextLink1Preview() {
    ConfchatTheme {
        ComponentTextLink1(){}
    }
}