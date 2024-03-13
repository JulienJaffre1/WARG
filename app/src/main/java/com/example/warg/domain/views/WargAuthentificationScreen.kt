package com.example.warg.domain.views

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.warg.R
import com.example.warg.domain.components.Screen
import com.example.warg.domain.components.WargAuth
import com.example.warg.domain.components.WargTestAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WargAuthentificationScreen (navHostController: NavHostController) {
    var utilisateur by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(vertical = 35.dp, horizontal = 25.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = stringResource(id = R.string.logo))
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.padding(vertical = 50.dp))
        WargAuth(
            modifier = Modifier,
            user = utilisateur,
            pass = password,
            userChange = { utilisateur = it },
            passChange = { password = it}
        )
        Row (
            modifier = Modifier.padding(vertical = 10.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
               TextButton(onClick = {  navHostController.navigate(route = "${Screen.WargInscription.route}") }) {
                   Text(text = "Créer un compte")
               }
            }
            Spacer(modifier = Modifier.padding(horizontal = 20.dp))
            WargTestAuth(username = utilisateur, password = password, navHostController = navHostController)
        }
    }
}