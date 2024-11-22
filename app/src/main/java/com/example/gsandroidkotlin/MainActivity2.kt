package com.example.gsandroidkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gsandroidkotlin.ui.theme.GSAndroidKotlinTheme
import androidx.compose.ui.unit.dp

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GSAndroidKotlinTheme {
                Scaffold { paddingValues ->
                    Content(Modifier.padding(paddingValues))
                }
            }
        }
    }

    @Composable
    fun Content(modifier: Modifier = Modifier) {
        androidx.compose.foundation.layout.Column(
            modifier = modifier.fillMaxSize()
        ) {
            Text(text = "Integrantes: ", modifier = Modifier.padding(16.dp))
            Text(text = "Luauberth Sampaio - RM96260", modifier = Modifier.padding(8.dp))
            Text(text = "Nicolas Ormart - RM95016", modifier = Modifier.padding(8.dp))
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewContent() {
        GSAndroidKotlinTheme {
            Content()
        }
    }
}
