package com.example.resol5

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.database.database



@Composable
fun HomeScreen() {
    // 1. Khởi tạo Firebase Database và tham chiếu (Reference)
    val database = Firebase.database
    val myRef = database.getReference("message")

    // 2. State để lưu trữ nội dung TextField
    var text by remember { mutableStateOf("") }

    // 3. UI
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = { Text("Enter text") }
        )
        Button(
            onClick = {
                // Ghi giá trị (string) vào đường dẫn "message" trong Realtime Database
                myRef.setValue(text)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Send to Firebase")
        }
    }
}