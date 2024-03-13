package com.example.warg.domain.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.warg.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WargAuth(modifier: Modifier, user : String, pass : String, userChange: (String) -> Unit, passChange: (String) -> Unit) {
    Column(
        modifier = modifier
    ) {
        TextField(
            value = user,
            onValueChange = userChange,
            label = { Text("Adresse mail") },
            singleLine = true
        )
        TextField(
            value = pass,
            onValueChange = passChange,
            label = { Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

@Composable
fun WargTestAuth(username : String, password : String, navHostController: NavHostController) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val debug = true

    Button(
        onClick = {
            if((username.equals("Jupiter") and password.equals("mars")) || debug) {
                navHostController.navigate(route = "${Screen.WargLibrary.route}")
            }
            else {
                openAlertDialog.value = true
            }
        }
    ) {
        Text(text = "Se connecter")
    }

    if(openAlertDialog.value) {
        AlertDialog(
            title = { Text(text = "Connexion impossible") },
            text = { Text(text = "Adresse mail ou mot de passe erroné") },
            confirmButton = {
                TextButton(
                    onClick = {
                        openAlertDialog.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            onDismissRequest = { },
        )
    }
}