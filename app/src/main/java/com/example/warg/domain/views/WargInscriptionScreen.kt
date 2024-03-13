package com.example.warg.domain.views

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.warg.domain.components.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WargInscriptionScreen(navHostController: NavHostController) {
    var utilisateur by rememberSaveable { mutableStateOf("") }
    var mail by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var password2 by rememberSaveable { mutableStateOf("") }
    val openAlertCreer = remember { mutableStateOf(false) }
    val openAlertErreur = remember { mutableStateOf(false) }
    var textErreur = remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Création de compte :", fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = utilisateur,
                    onValueChange = { utilisateur = it },
                    label = { Text("Identifiant") },
                    singleLine = true
                )
                TextField(
                    value = mail,
                    onValueChange = { mail = it },
                    label = { Text("Adresse mail") },
                    singleLine = true
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Mot de passe") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                TextField(
                    value = password2,
                    onValueChange = { password2 = it },
                    label = { Text("Confirmer mot de passe") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Row(
                    modifier = Modifier.padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    TextButton(onClick = { navHostController.navigate(route = "${Screen.WargAuthentification.route}") }) {
                        Text(text = "Retour")

                    }
                    Spacer(modifier = Modifier.padding(horizontal = 40.dp))
                    Button(onClick = {
                        if(utilisateur != "" && utilisateur.length <= 22 && utilisateur.length > 1) {
                            if(mail.contains("@") && mail != "") {
                                if(password == password2 && password != "" && password2 != "") {
                                    openAlertCreer.value = true
                                }
                                else {
                                    textErreur.value = "Les mots de passes ne correspondent pas"
                                    openAlertErreur.value = true
                                }
                            }
                            else {
                                textErreur.value = "L'adresse mail n'est pas valide"
                                openAlertErreur.value = true
                            }
                        }
                        else {
                            textErreur.value = "L'Identifiant n'est pas valide"
                            openAlertErreur.value = true
                        }
                    }) {
                        Text(text = "Confirmer")
                    }
                }
            }
        }
    }

    if(openAlertCreer.value) {
        AlertDialog(
            title = { Text(text = "Compte créé") },
            text = { Text(text = "Votre compte a bien été créé") },
            confirmButton = {
                TextButton(
                    onClick = {
                        openAlertCreer.value = false
                        navHostController.navigate(route = "${Screen.WargAuthentification.route}")
                    }
                ) {
                    Text("OK")
                }
            },
            onDismissRequest = { },
        )
    }

    if(openAlertErreur.value) {
        AlertDialog(
            title = { Text(text = "Impossible de créer votre compte") },
            text = { Text(text = textErreur.value) },
            confirmButton = {
                TextButton(
                    onClick = {
                        openAlertErreur.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            onDismissRequest = { },
        )
    }
}