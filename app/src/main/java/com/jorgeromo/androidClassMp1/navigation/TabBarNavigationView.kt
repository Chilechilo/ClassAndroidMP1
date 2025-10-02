package com.jorgeromo.androidClassMp1.navigation

import SecondPartialView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.jorgeromo.androidClassMp1.firstpartial.FirstPartialView
import com.jorgeromo.androidClassMp1.ids.IdsView
import com.jorgeromo.androidClassMp1.firstpartial.login.views.LoginView
import com.jorgeromo.androidClassMp1.thirdpartial.ThirdPartialView
import androidx.compose.ui.graphics.Color
import com.jorgeromo.androidClassMp1.R
import com.jorgeromo.androidClassMp1.firstpartial.login.views.LottieAnimationView
import com.jorgeromo.androidClassMp1.secondpartial.qrcode.views.QrCodeView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.jorgeromo.androidClassMp1.secondpartial.home.model.network.HomeApi
import com.jorgeromo.androidClassMp1.secondpartial.home.model.repository.HomeRepository
import com.jorgeromo.androidClassMp1.secondpartial.home.viewmodel.HomeViewModel
import com.jorgeromo.androidClassMp1.secondpartial.home.viewmodel.HomeViewModelFactory
import com.jorgeromo.androidClassMp1.secondpartial.home.views.HomeViewProducts
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarNavigationView(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        ScreenNavigation.Ids,
        ScreenNavigation.FirstPartial,
        ScreenNavigation.SecondPartial,
        ScreenNavigation.ThirdPartial
    )

    // Mapa de títulos por ruta (incluye tabs y pantallas internas)
    val routeTitles = remember {
        mapOf(
            ScreenNavigation.Ids.route to ScreenNavigation.Ids.label,
            ScreenNavigation.FirstPartial.route to ScreenNavigation.FirstPartial.label,
            ScreenNavigation.SecondPartial.route to ScreenNavigation.SecondPartial.label,
            ScreenNavigation.ThirdPartial.route to ScreenNavigation.ThirdPartial.label,

            // Rutas internas (ajusta a tus strings preferidos)
            ScreenNavigation.IMC.route to "IMC",
            ScreenNavigation.Login.route to "Login",
            ScreenNavigation.Sum.route to "Suma",
            ScreenNavigation.Temperature.route to "Temperatura",
            ScreenNavigation.StudentList.route to "Estudiantes",
            ScreenNavigation.Locations.route to "Ubicaciones",
            ScreenNavigation.Animation.route to "Animation"
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Si usas nested graphs, puedes leer la jerarquía; aquí basta con la route actual:
    val currentRoute = navBackStackEntry?.destination?.route
    val currentTitle = routeTitles[currentRoute] ?: ""

    Scaffold(
        topBar = {
            // Puedes usar SmallTopAppBar o CenterAlignedTopAppBar
            CenterAlignedTopAppBar(
                title = { Text(text = "Luis Angel H C 13480") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFF2E7D32), // Azul
                titleContentColor = Color.White
            )
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEach { screen ->
                    val selected = currentRoute == screen.route
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Ids.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenNavigation.Ids.route) { IdsView(navController) }

            // Primer Parcial -> pasa navController
            composable(ScreenNavigation.FirstPartial.route) {
                FirstPartialView(navController)
            }

            composable(ScreenNavigation.SecondPartial.route) { SecondPartialView(navController) }
            composable(ScreenNavigation.ThirdPartial.route) { ThirdPartialView(navController) }

            // Rutas internas
            composable(ScreenNavigation.Login.route) { LoginView() }
            composable(ScreenNavigation.Animation.route) {
                LottieAnimationView(
                    modifier = Modifier.fillMaxSize(),
                    resId = R.raw.animation,
                    autoplay = true
                )
            }
            // (IMC, Sum, etc. si las usan)

            // Segundo Parcial
            composable(ScreenNavigation.QrCode.route) { QrCodeView() }
            composable(ScreenNavigation.HomeProducts.route) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://gist.githubusercontent.com/Manuel2210337/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val api = retrofit.create(HomeApi::class.java)
                val repo = HomeRepository(api)
                val viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(repo))
                val uiState by viewModel.ui.collectAsState()
                LaunchedEffect(Unit) {
                    viewModel.fetchHome()
                }
                HomeViewProducts(uiState = uiState)
            }

        }
    }
}
