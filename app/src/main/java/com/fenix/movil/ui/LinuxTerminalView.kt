package com.fenix.movil.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LinuxTerminalView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0B10))
            .padding(16.dp)
    ) {
        // Top status bar stub
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Debian 12 Bookworm | glibc 2.36",
                color = Color.Green,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = "PTY Activo",
                color = Color.Green,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Terminal Content Stub
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "fenix@debian-12:~\$ ",
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        // Floating Action Keys Stub
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val keys = listOf("ESC", "TAB", "CTRL", "ALT", "|", "/", "-", "^C")
            keys.forEach { key ->
                Box(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = key, color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LinuxTerminalPreview() {
    LinuxTerminalView()
}
