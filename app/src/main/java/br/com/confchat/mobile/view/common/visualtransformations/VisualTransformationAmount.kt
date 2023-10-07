package br.com.confchat.mobile.view.common.visualtransformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class VisualTransformationAmount : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var value = text.text
        if (value.length < 3) {
            value = when {
                value.length == 2 -> "0$value"
                value.length == 1 -> "00$value"
                else -> "000"
            }
        }
        val regex = Regex("(\\d+)(\\d{2})")
        var result = value.replace(regex, "$1,$2")
        result = "R$ $result"
        return TransformedText(
            text = AnnotatedString(result),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return result.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return text.length
                }

            }
        )
    }
}