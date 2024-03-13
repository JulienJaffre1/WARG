package com.example.warg.domain.views

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.warg.R
import com.example.warg.domain.components.Screen
import com.example.warg.domain.components.WargGameList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WargLibraryScreen (navHostController: NavHostController) {
    Row {
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            WargGameList()
        }
        TextButton(onClick = {  navHostController.navigate(route = "${Screen.WargSettings.route}") }) {
            Image(painter = painterResource(id = R.drawable.settings), contentDescription = stringResource(id = R.string.setting),
                modifier = Modifier.size(height = 15.dp, width = 15.dp))
            // Text(text = "Créer un compte")
        }
       // Image(painter = painterResource(id = R.drawable.settings), contentDescription = stringResource(id = R.string.setting),)
    }



}