package br.com.confchat.mobile.view.componets.customshapes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class CustomShapeCardAddMenu():Shape{
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val w = (size.width)
        val h = (size.height)
        val border = with(density){
            16.dp.toPx()
        }
        val path = Path().apply {
            moveTo(0f, 0f)
            arcTo(
                Rect(
                    offset = Offset(0f,0f),
                    size = Size(border,border)
                ),
                -180f,
                90f,
                false
            )
            lineTo(w, 0f)
            arcTo(
                Rect(
                    offset = Offset(w-(border),0f),
                    size = Size(border,border)
                ),
                -90f,
                90f,
                false
            )
            val dis = border/2
            lineTo(w, h - (dis*2))
            arcTo(
                Rect(
                    offset = Offset(w -border,h-border-dis),
                    size = Size(border,border)
                ),
                0f,
                90f,
                false
            )
            lineTo((w+border)/2, h - dis)
            lineTo(w/2,h)
            lineTo((w-border)/2, h - dis)
            lineTo(0f, h - dis)
            arcTo(
                Rect(
                    offset = Offset(0f,h-border-dis),
                    size = Size(border,border)
                ),
                90f,
                90f,
                false
            )
            lineTo(0f, 0f)
            close()
        }
        return Outline.Generic(path)
    }

}