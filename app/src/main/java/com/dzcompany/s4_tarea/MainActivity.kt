package com.dzcompany.s4_tarea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
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
            ModalDrawerSheet(
                drawerContainerColor = Color(0xD0043762) // Fondo del drawer
            ) {
                Text(
                    "Menu",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
                HorizontalDivider(color = Color.White)
                NavigationDrawerItem(
                    label = { Text("Inicio", color = Color.White) },
                    selected = false,
                    onClick = {
                        navController.navigate("home")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Tabla de Multiplicar", color = Color.White) },
                    selected = false,
                    onClick = {
                        navController.navigate("multiplication_table")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Cálculo de Factorial", color = Color.White) },
                    selected = false,
                    onClick = {
                        navController.navigate("factorial_calculator")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Juego de Adivinanza", color = Color.White) },
                    selected = false,
                    onClick = {
                        navController.navigate("guessing_game")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Conversión de Texto", color = Color.White) },
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
                    title = { Text("MultiFunciones - Ulises Zuniga", color = Color.White) }, // 👈 Título blanco
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menú",
                                tint = Color.White // 👈 Ícono blanco
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xD0043762) // 👈 Color de fondo del TopAppBar
                    )
                )
            },
            containerColor = Color(0xE1041241)
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF8BBD0)) // Fondo general del contenido
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "home",
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
}

@Composable
fun HomeScreen() {
    CompositionLocalProvider(LocalContentColor provides Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xE9009688))
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnim(modifier = Modifier.size(250.dp))
            Text("¡Bienvenido a mi aplicación!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Selecciona una función del menú lateral.")
        }
    }
}

@Composable
fun MultiplicationTableScreen() {
    var numberInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xE9009688))
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RobotAnim(modifier = Modifier.size(180.dp))
        Text("Tabla de Multiplicar", style = MaterialTheme.typography.headlineSmall, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(2.dp),
            value = numberInput,
            onValueChange = { input ->
                // Solo números y máximo 9 dígitos
                if (input.all { it.isDigit() } && input.length <= 9) {
                    numberInput = input
                }
            },
            label = { Text("Ingresa un número entero", color = Color.Gray) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    generarTabla(numberInput)?.let {
                        result = it
                    } ?: run {
                        result = "Por favor, ingresa un número válido de hasta 9 dígitos."
                    }
                    keyboardController?.hide()
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            generarTabla(numberInput)?.let {
                result = it
            } ?: run()
             {
                result = "Por favor, ingresa un número válido de hasta 9 dígitos."
            }
            numberInput = ""
            keyboardController?.hide()
        }) {
            Text("Generar Tabla")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(result, color = Color.White)
    }
}

// Función auxiliar para generar la tabla
private fun generarTabla(input: String): String? {
    val num = input.toLongOrNull() ?: return null
    if (num > Int.MAX_VALUE) return null // Evita desbordamiento
    val table = StringBuilder()
    for (i in 1..12) {
        table.append("$num x $i = ${num * i}\n")
    }
    return table.toString()
}


@Composable
fun FactorialCalculatorScreen() {
    var numberInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xE9009688))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RobotAnim2(modifier = Modifier.size(150.dp))
        Text("Cálculo de Factorial", style = MaterialTheme.typography.headlineSmall, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(2.dp),
            value = numberInput,
            onValueChange = { input ->
                // Solo permitir dígitos y máximo 9 caracteres
                if (input.all { it.isDigit() } && input.length <= 9) {
                    numberInput = input
                }
            },
            label = { Text("Ingresa un número entero positivo", color = Color.Gray) },
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
                result = "Por favor, ingresa un número entero positivo válido de hasta 9 dígitos."
            }
            numberInput = "" // 👈 Limpia el campo después del cálculo
            keyboardController?.hide()
        }) {
            Text("Calcular Factorial")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(result, color = Color.White)
    }
}


enum class RobotAnimType {
    SUCCESS, FAIL
}

@Composable
fun GuessingGameScreen() {
    var guessInput by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Adivina un número entre 1 y 10") }
    var randomNumber by remember { mutableIntStateOf((1..10).random()) }
    var attempts by remember { mutableIntStateOf(0) }
    var animationState by remember { mutableStateOf<RobotAnimType?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xE9009688))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 👇 Animación basada en estado
        when (animationState) {
            RobotAnimType.SUCCESS -> RobotAnim5(modifier = Modifier.size(150.dp))
            RobotAnimType.FAIL -> RobotAnim6(modifier = Modifier.size(150.dp))
            else -> RobotAnim3(modifier = Modifier.size(150.dp)) // Animación neutral por defecto
        }

        Text("Juego de Adivinanza", style = MaterialTheme.typography.headlineSmall, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(2.dp),
            value = guessInput,
            onValueChange = { input ->
                if (input.all { it.isDigit() } && input.length <= 9) {
                    guessInput = input
                }
            },
            label = { Text("Tu adivinanza", color = Color.Gray) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val guess = guessInput.toIntOrNull()
            if (guess != null && guess in 1..10) {
                attempts++
                if (guess == randomNumber) {
                    message = "¡Correcto! Adivinaste en $attempts intentos. El número era $randomNumber."
                    animationState = RobotAnimType.SUCCESS
                    randomNumber = (1..10).random()
                    attempts = 0
                } else {
                    message = if (guess < randomNumber) "El número es mayor." else "El número es menor."
                    animationState = RobotAnimType.FAIL
                }
                guessInput = ""
            } else {
                message = "Por favor, ingresa un número del 1 al 10."
                animationState = RobotAnimType.FAIL
                guessInput = ""
            }
        }) {
            Text("Adivinar")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(message, color = Color.White)
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
            .background(Color(0xE9009688))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RobotAnim4(modifier = Modifier.size(150.dp))
        Text("Conversión de Texto", style = MaterialTheme.typography.headlineSmall, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = phraseInput,
            onValueChange = { phraseInput = it },
            label = { Text("Ingresa una frase", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(2.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            upperCaseText = phraseInput.uppercase()
            lowerCaseText = phraseInput.lowercase()
        }) {
            Text("Convertir Texto")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Mayúsculas: $upperCaseText", color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Minúsculas: $lowerCaseText", color = Color.White)
    }
}

@Composable
fun LottieAnim(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_maths))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Composable
fun RobotAnim(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.robot_math))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Composable
fun RobotAnim2(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.robot_math2))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Composable
fun RobotAnim3(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.robot_think))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Composable
fun RobotAnim4(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.robot_written))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Composable
fun RobotAnim5(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.robot_ok))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Composable
fun RobotAnim6(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.robot_wrong))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    S4_TareaTheme {
        MenuApp()
    }
}
