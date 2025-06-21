package com.dzcompany.s4_tarea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dzcompany.s4_tarea.ui.theme.S4_TareaTheme
import kotlinx.coroutines.launch
import java.math.BigInteger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S4_TareaTheme {
                MenuApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Funciones", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = false,
                    onClick = {
                        navController.navigate("home")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Tabla de Multiplicar") },
                    selected = false,
                    onClick = {
                        navController.navigate("multiplication_table")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Cálculo de Factorial") },
                    selected = false,
                    onClick = {
                        navController.navigate("factorial_calculator")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Juego de Adivinanza") },
                    selected = false,
                    onClick = {
                        navController.navigate("guessing_game")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Conversión de Texto") },
                    selected = false,
                    onClick = {
                        navController.navigate("text_conversion")
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("S4-Tarea-Menú de Funciones") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("home") { HomeScreen() }
                composable("multiplication_table") { MultiplicationTableScreen() }
                composable("factorial_calculator") { FactorialCalculatorScreen() }
                composable("guessing_game") { GuessingGameScreen() }
                composable("text_conversion") { TextConversionScreen() }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("¡Bienvenido a mi aplicación!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Selecciona una función del menú lateral.")
    }
}

@Composable
fun MultiplicationTableScreen() {
    var numberInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tabla de Multiplicar", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = numberInput,
            onValueChange = { numberInput = it },
            label = { Text("Ingresa un número entero") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val num = numberInput.toIntOrNull()
            if (num != null) {
                val table = StringBuilder()
                for (i in 1..12) {
                    table.append("$num x $i = ${num * i}\n")
                }
                result = table.toString()
            } else {
                result = "Por favor, ingresa un número válido."
            }
        }) {
            Text("Generar Tabla")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(result)
    }
}

@Composable
fun FactorialCalculatorScreen() {
    var numberInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cálculo de Factorial", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = numberInput,
            onValueChange = { numberInput = it },
            label = { Text("Ingresa un número entero positivo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val num = numberInput.toIntOrNull()
            if (num != null && num >= 0) {
                var factorial = BigInteger.ONE
                for (i in 1..num) {
                    factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
                }
                result = "El factorial de $num es $factorial"
            } else {
                result = "Por favor, ingresa un número entero positivo válido."
            }
        }) {
            Text("Calcular Factorial")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(result)
    }
}

@Composable
fun GuessingGameScreen() {
    var guessInput by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Adivina un número entre 1 y 10") }
    var randomNumber by remember { mutableIntStateOf((1..10).random()) }
    var attempts by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Juego de Adivinanza", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = guessInput,
            onValueChange = { guessInput = it },
            label = { Text("Tu adivinanza") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val guess = guessInput.toIntOrNull()
            if (guess != null) {
                attempts++
                message = when {
                    guess < randomNumber -> "El número es mayor."
                    guess > randomNumber -> "El número es menor."
                    else -> "¡Correcto! Adivinaste en $attempts intentos. El número era $randomNumber."
                }
                if (guess == randomNumber) {
                    randomNumber = (1..10).random()
                    attempts = 0
                    guessInput = ""
                }
            } else {
                message = "Por favor, ingresa un número válido."
            }
        }) {
            Text("Adivinar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(message)
    }
}

@Composable
fun TextConversionScreen() {
    var phraseInput by remember { mutableStateOf("") }
    var upperCaseText by remember { mutableStateOf("") }
    var lowerCaseText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Conversión de Texto", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = phraseInput,
            onValueChange = { phraseInput = it },
            label = { Text("Ingresa una frase") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            upperCaseText = phraseInput.uppercase()
            lowerCaseText = phraseInput.lowercase()
        }) {
            Text("Convertir Texto")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Mayúsculas: $upperCaseText")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Minúsculas: $lowerCaseText")
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    S4_TareaTheme {
        MenuApp()
    }
}
