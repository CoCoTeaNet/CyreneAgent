package net.cocotea.cyreneagent.view.component.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ChatView() {
    Column {
        TextButton(onClick = { /* Do something! */ }) { Text("这里是ChatView") }
        TextButton(onClick = { /* Do something! */ }) { Text("这里是ChatView") }
        TextButton(onClick = { /* Do something! */ }) { Text("这里是ChatView") }
    }
}