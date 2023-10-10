package br.com.confchat.mobile.presenter.view.common.visualtransformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class VisualTransformationCpf : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var result = ""
        text.forEachIndexed { index, c ->
            when(index){
                3,6 ->{
                    result += '.'
                }
                9 ->{
                    result += '-'
                }
            }
            result += c
        }
        result
        return TransformedText(
            text = AnnotatedString(result),
            offsetMapping = object :OffsetMapping{
                override fun originalToTransformed(offset: Int): Int {
                    return result.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return offset
                }

            }
        )
    }
}