package br.com.confchat.mobile.presenter.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.confchat.mobile.presenter.veiwmodel.model.ContactViewModel

@Composable
fun ComponentItemListContact(it:ContactViewModel,onClinck:()->Unit) {

    ComponentItemList(modifier = Modifier.clickable { onClinck() }){
        ComponentImageContact(it.urlImg)
        Spacer(modifier = Modifier.width(16.dp))
        Column (modifier = Modifier.weight(1f)) {
            Text(text = it.name, fontSize = 14.sp)
            Text(text = it.previewMessage, maxLines = 1, fontSize = 12.sp)
        }
        if(it.isEncripted){
            Icon(imageVector = Icons.Default.Lock, contentDescription = null)
        }
        if(it.coutNewsMessage>0){
            Box(modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.Green),
                contentAlignment = Alignment.Center){
                Text(text = it.coutNewsMessage.toString(), fontSize = 10.sp)
            }
        }
    }
}

@Preview
@Composable
fun ComponentItemListContactPreview() {
    ComponentItemListContact(ContactViewModel("id","","teste","teste",2,true,true)){

    }
}