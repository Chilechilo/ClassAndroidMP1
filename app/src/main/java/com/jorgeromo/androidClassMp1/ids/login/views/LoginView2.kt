package com.jorgeromo.androidClassMp1.ids.login.views

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.jorgeromo.androidClassMp1.R
import com.jorgeromo.androidClassMp1.ui.theme.AndroidClassMP1Theme

@Composable
fun LoginView2() {
    // Estado de inputs
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Estado de "tocado" para no mostrar errores desde el inicio
    var emailTouched by remember { mutableStateOf(false) }
    var passTouched by remember { mutableStateOf(false) }

    // Validaciones
    val isEmailValid = remember(email) { Patterns.EMAIL_ADDRESS.matcher(email).matches() }
    val isPassValid = remember(password) { password.isValidPassword() }
    val canSubmit = isEmailValid && isPassValid

    // Lienzo principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Ícono/imagen superior (estilo del mock)
                Image(
                    painter = painterResource(id = R.drawable.onb_1),
                    contentDescription = "Imagen de cabecera",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Spacer(Modifier.height(20.dp))

                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        if (!emailTouched) emailTouched = true
                    },
                    label = { Text("Correo electrónico") },
                    singleLine = true,
                    isError = emailTouched && !isEmailValid && email.isNotBlank(),
                    supportingText = {
                        if (emailTouched && !isEmailValid && email.isNotBlank()) {
                            Text("Ingresa un correo válido (ej. usuario@dominio.com)")
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                // Password
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        if (!passTouched) passTouched = true
                    },
                    label = { Text("Contraseña") },
                    singleLine = true,
                    isError = passTouched && !isPassValid && password.isNotBlank(),
                    supportingText = {
                        if (passTouched && !isPassValid && password.isNotBlank()) {
                            Text(
                                "Debe tener ≥6 caracteres, 1 mayúscula, 1 número y 1 símbolo"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff,
                                contentDescription = if (passwordVisible) "Ocultar" else "Mostrar"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                // "¿No tienes cuenta? Regístrate"
                Text(
                    text = "¿No tienes cuenta? Regístrate",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { /* Navegar a registro */ }
                        .padding(vertical = 4.dp)
                )

                Spacer(Modifier.height(8.dp))

                // Ícono tipo "face id" central (opcional del mock)
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = "Face ID",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(Modifier.height(22.dp))

                // Botón Iniciar sesión (habilitado solo si válido)
                Button(
                    onClick = { /* TODO: autenticación */ },
                    enabled = canSubmit,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Iniciar sesión")
                }
            }
        }
    }
}

/**
 * Reglas:
 * - Al menos 6 caracteres
 * - Al menos 1 mayúscula
 * - Al menos 1 número
 * - Al menos 1 símbolo especial
 */
private fun String.isValidPassword(): Boolean {
    if (length < 6) return false
    val hasUpper = any { it.isUpperCase() }
    val hasDigit = any { it.isDigit() }
    val hasSymbol = any { !it.isLetterOrDigit() }
    return hasUpper && hasDigit && hasSymbol
}

@Preview(showBackground = true)
@Composable
fun LoginView2Preview() {
    AndroidClassMP1Theme {
        LoginView2()
    }
}
