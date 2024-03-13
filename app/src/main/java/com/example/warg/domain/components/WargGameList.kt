package com.example.warg.domain.components

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat
import coil.compose.AsyncImage
import com.example.warg.data.core.Jeux
import com.squareup.picasso.Picasso

@Composable
fun WargGameList() {
    val jeux1: Jeux = Jeux("The Binding of Isaac: Rebirth", "https://media.steampowered.com/steamcommunity/public/images/apps/250900/16d46c8630499bfc54d20745ac90786a302cd643.jpg")
    val jeux2: Jeux = Jeux("Last Epoch", "https://media.steampowered.com/steamcommunity/public/images/apps/899770/af127fa6337a3c44e52690b62f26530d520127f4.jpg")
    val jeux3: Jeux = Jeux("Digimon Story Cyber Sleuth: Complete Edition", "https://media.steampowered.com/steamcommunity/public/images/apps/1042550/a2387ad464e47941d8b0c1293b68e9fd84de8031.jpg")
    val jeux4: Jeux = Jeux("DATE A LIVE: Rio Reincarnation", "https://media.steampowered.com/steamcommunity/public/images/apps/1047440/c460f5d176fae181e273fc82d5fec85841b9af7e.jpg")
    val jeux5: Jeux = Jeux("BLEACH Brave Souls - 3D Action", "https://media.steampowered.com/steamcommunity/public/images/apps/1201240/5da6d5a0e063642e67001c53522f21773b6029ed.jpg")

// Créer un tableau de jeux
    val tableauJeux = arrayOf(jeux1, jeux2, jeux3, jeux4, jeux5)

    LazyColumn {
        items(tableauJeux) { jeu ->
            JeuxItem(jeu)
        }
    }
}

@Composable
fun JeuxItem(jeu: Jeux) {
    Row(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = jeu.image,
            contentDescription = null,
            modifier = Modifier.size(height = 15.dp, width = 15.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = jeu.nom)
    }
}