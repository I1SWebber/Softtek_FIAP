package br.com.fiap.softtek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.menteleve.screens.HomeScreen
import br.com.fiap.softtek.navigation.AppNavigation
import br.com.fiap.softtek.ui.theme.SofttekTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SofttekTheme {


                AppNavigation()

            }
        }
    }
}
