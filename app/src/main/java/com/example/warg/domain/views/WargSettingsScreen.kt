package com.example.warg.domain.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.warg.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WargSettingsScreen (navHostController: NavHostController) {
    var utilisateur by rememberSaveable { mutableStateOf("Jupiter") }
    var mail by rememberSaveable { mutableStateOf("jupiter@gmail.com") }
    var password by rememberSaveable { mutableStateOf("") }
    var password2 by rememberSaveable { mutableStateOf("") }
    var aff by rememberSaveable { mutableStateOf(false) }


    Row {
        Column(
            modifier = Modifier.padding(vertical = 35.dp, horizontal = 25.dp),
            //horizontalAlignment = Alignment.CenterHorizontally
            //verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Mon compte :", fontSize = 20.sp)
            Column(
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row (
                    modifier = Modifier.padding(horizontal = 50.dp)
                ){
                    TextField(
                        value = utilisateur,
                        onValueChange = {},
                        label = { Text("Utilisateur") },
                        singleLine = true,
                        enabled = false
                    )
                }
                Row(
                    modifier = Modifier.padding(horizontal = 50.dp)
                ) {
                    TextField(
                        value = mail,
                        onValueChange = {},
                        label = { Text("Adresse mail") },
                        singleLine = true,
                        enabled = false
                    )
                }
            }
            Column(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.separator),
                    contentDescription = stringResource(id = R.string.separator),
                    Modifier.fillMaxWidth()
                )
            }
            Column {
                Text(text = "Steam :", fontSize = 20.sp)
                Column(
                    modifier = Modifier.padding(vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    var steamID by rememberSaveable { mutableStateOf("") }


                    Column {
                        TextField(
                            value = steamID,
                            onValueChange = { steamID = it },
                            label = { Text("Utilisateur") },
                            singleLine = true,
                            enabled = true
                        )
                        
                        Button(onClick = { aff = true}) {
                            Text(text = "Connect")
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.separator),
                    contentDescription = stringResource(id = R.string.separator),
                    Modifier.fillMaxWidth()
                )
            }
            Column {
                Text(text = "GOG :", fontSize = 20.sp)
                Column(
                    modifier = Modifier.padding(vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    var GOGID by rememberSaveable { mutableStateOf("") }


                    Column {
                        TextField(
                            value = GOGID,
                            onValueChange = { GOGID = it },
                            label = { Text("Utilisateur") },
                            singleLine = true,
                            enabled = false
                        )
                    }
                }
            }
        }
    }
}