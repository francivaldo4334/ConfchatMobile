package br.com.confchat.mobile.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.confchat.mobile.R
import br.com.confchat.mobile.view.Components.ComponentIcon1
import br.com.confchat.mobile.view.enums.IconsLayout

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_confchat),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 24.sp
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}