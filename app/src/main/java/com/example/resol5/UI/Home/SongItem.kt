package com.example.resol5.UI.Home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage // Thư viện Coil load ảnh
import com.example.resol5.Model.Song

@Composable
fun SongItem(song: Song) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ảnh bìa
            AsyncImage(
                model = song.Anh,
                contentDescription = "Ảnh bìa",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Tên và Ca sĩ
            Column {
                Text(text = song.TieuDe, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = song.CaSi, color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}