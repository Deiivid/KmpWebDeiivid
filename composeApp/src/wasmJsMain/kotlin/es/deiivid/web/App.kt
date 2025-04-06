package es.deiivid.web

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import deiividweb.composeapp.generated.resources.Res
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun AnimatedHeader() {
    var showDescription by remember { mutableStateOf(false) }
    // Lanzamos un efecto para que la descripción aparezca con un delay
    LaunchedEffect(Unit) {
        delay(1000) // 1 segundo de delay
        showDescription = true
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentAlignment = Alignment.Center
    ) {
        // Imagen central (tu foto)
    /*    Image(
            painter = painterResource(Res.drawable.),
            contentDescription = "Mi Foto",
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        )*/
        // Un ejemplo de logo posicionado arriba (Kotlin) - repite para cada logo con posiciones distintas
        Box(
            modifier = Modifier
                .absoluteOffset(x = 0.dp, y = (-150).dp)
        ) {
            /*Image(
                painter = painterResource(Res.drawable),
                contentDescription = "Kotlin Logo",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                // Aquí podrías agregar una animación CSS o usar APIs de animación de Compose
            )*/
        }
        // La descripción que aparece con fadeIn
        AnimatedVisibility(
            visible = showDescription,
            enter = fadeIn(animationSpec = tween(durationMillis = 500))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .absoluteOffset(y = 220.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Soy David, desarrollador móvil apasionado por la tecnología.")
            }
        }
    }
}

@Composable
fun SkillsSection() {
    val skills = listOf("Kotlin", "Android", "Java", "Compose", "KMP", "iOS", "JS")
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mis Skills", modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            skills.forEach { skill ->
                var visible by remember { mutableStateOf(false) }
                // Cada skill aparece con un delay (puedes ajustar o usar el índice para escalonar)
                LaunchedEffect(skill) {
                    delay(500)
                    visible = true
                }
                AnimatedVisibility(
                    visible = visible,
                    enter = slideInVertically(
                        initialOffsetY = { it }, // Aparece desde abajo
                        animationSpec = tween(durationMillis = 500)
                    )
                ) {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = TODO(),
                        enabled = TODO(),
                        interactionSource = TODO(),
                        elevation = TODO(),
                        shape = TODO(),
                        border = TODO(),
                        colors = TODO(),
                        contentPadding = TODO()
                    ) {
                        Text(skill)
                    }
                }
            }
        }
    }
}

@Composable
fun PortfolioSection() {
    val projects = listOf("Proyecto 1", "Proyecto 2", "Proyecto 3")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Portfolio", modifier = Modifier.padding(16.dp))
        // Simulamos una grid simple con Column y Row; en un caso real, podrías crear un layout más avanzado
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            projects.forEach { project ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(0.8f)
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Cada proyecto con un fondo y descripción breve
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(project)
                        Text("Descripción breve del proyecto.")
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedHeader()
            SkillsSection()
            PortfolioSection()
        }
    }
}