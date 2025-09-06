package com.jorgeromo.androidClassMp1.firstpartial

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FirstPartialView(NavController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Primer Parcial Moviles I",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { NavController.navigate("Login") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Login")
        }
        Button(
            onClick = { NavController.navigate("animations") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Animacion")
    }
}}