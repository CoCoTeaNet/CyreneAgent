package net.cocotea.cyreneagent.view.component.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun SettingView() {
    Column {
        TextButton(onClick = { /* Do something! */ }) { Text("这里是SettingView") }
        TextButton(onClick = { /* Do something! */ }) { Text("这里是SettingView") }
        TextButton(onClick = { /* Do something! */ }) { Text("这里是SettingView") }
    }
}