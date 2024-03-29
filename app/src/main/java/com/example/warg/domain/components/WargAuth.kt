package com.example.warg.domain.components

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.warg.R
import com.example.warg.domain.model.WargViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

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

@OptIn(InternalCoroutinesApi::class)
@Composable
fun WargTestAuth(username : String, password : String, navHostController: NavHostController) {
    val wargViewModel = getViewModel<WargViewModel>()
    val state by wargViewModel.viewState.collectAsState()
    val openAlertDialog = remember { mutableStateOf(false) }
    val debug = false

    Button(
        onClick = {
            wargViewModel.viewModelScope.launch {
                wargViewModel.accountConnectionSus(username, password)
            }

            while (state.isLoading) {

            }

            Log.d("WARG", "Auth " + state.token!!.token)

                //state.token?.let { Log.d("WARG", "Auth " + it.token) }

                if(state.token?.token?.isNotEmpty() == true) {
                    navHostController.navigate(route = "WargLibraryScreen/" + state.token!!.token)
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