package br.com.fiap.softtek

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.softtek.screens.HomeScreen
import br.com.fiap.softtek.navigation.AppNavigation
import br.com.fiap.softtek.ui.theme.SofttekTheme
import br.com.fiap.softtek.util.agendarNotificacaoDiaria
import br.com.fiap.softtek.util.criarCanalDeNotificacao

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.fiap.softtek.screens.DashboardScreen
import br.com.fiap.softtek.util.dispararNotificacaoAgora

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        criarCanalDeNotificacao(this)

        // Solicita permissão de notificação (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            } else {
                agendarNotificacaoDiaria(this)
            }
        } else {
            agendarNotificacaoDiaria(this)
        }

        dispararNotificacaoAgora(this)

        setContent {
            AppNavigation()
        }
    }
}
