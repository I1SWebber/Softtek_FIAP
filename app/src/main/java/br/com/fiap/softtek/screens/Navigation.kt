package br.com.fiap.softtek.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.menteleve.screens.DiarioDeHumorScreen
import br.com.fiap.menteleve.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen { route -> navController.navigate(route) }
        }
        composable("avaliacao") {
            Text("Tela de Avaliação Psicossocial")
        }
        composable("diario") {
            DiarioDeHumorScreen(navController)
        }
        composable("canal") {
            // Tela de canal de escuta
        }
        composable("graficos") {
            // Tela de progresso pessoal
        }
    }
}
