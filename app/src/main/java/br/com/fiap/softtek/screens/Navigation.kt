package br.com.fiap.softtek.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.softtek.screens.AvaliacaoScreen
import br.com.fiap.softtek.screens.DashboardScreen
import br.com.fiap.softtek.screens.HomeScreen
import br.com.fiap.softtek.screens.DiarioDeHumorScreen
import br.com.fiap.softtek.screens.HistoricoScreen
import br.com.fiap.softtek.screens.RecursosScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen { route -> navController.navigate(route) }
        }
        composable("avaliacao") {
            AvaliacaoScreen(navController)
        }
        composable("diario") {
            DiarioDeHumorScreen(navController)
        }
        composable("graficos") {
            DashboardScreen()
        }
        composable("historico") {
            HistoricoScreen()
        }
        composable("canal") {
            RecursosScreen()
        }
    }
}
