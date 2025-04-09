package es.deiivid.web

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import deiividweb.composeapp.generated.resources.*
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.*

@Composable
fun App() {
    MaterialTheme(colors = darkColors()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeroSection()
                Spacer(modifier = Modifier.height(48.dp))
                TechCarousel()
                Spacer(modifier = Modifier.height(32.dp))
                //SkillsSection()
                Spacer(modifier = Modifier.height(32.dp))
                PortfolioSection()
            }
        }
    }
}
@Composable
fun HeroSection() {
    var showText by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(500)
        showText = true
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.yo),
            contentDescription = "David Navarro",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center)
        )

        TechIcon(Res.drawable.kotlin, 0.dp, -130.dp) // arriba
        TechIcon(Res.drawable.android, 130.dp, 0.dp) // derecha
        TechIcon(Res.drawable.apple, 0.dp, 130.dp) // abajo
        TechIcon(Res.drawable.git, -100.dp, -100.dp) // arriba izquierda
        TechIcon(Res.drawable.gitHub, 100.dp, -100.dp) // arriba derecha
        TechIcon(Res.drawable.java, 100.dp, 100.dp) // abajo derecha

        Column(
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(showText, enter = fadeIn(tween(1000))) {
                Text("Hello 👋 I'm", color = Color.White, style = MaterialTheme.typography.h5)
            }
            AnimatedVisibility(showText, enter = fadeIn(tween(1500))) {
                Text("David Navarro", color = Color.White, style = MaterialTheme.typography.h3)
            }
            AnimatedVisibility(showText, enter = fadeIn(tween(2000))) {
                Text("Mobile Developer (Android | Compose | KMP)", color = Color.Gray)
            }
        }
    }
}

@Composable
fun TechIcon(res: DrawableResource, offsetX: Dp, offsetY: Dp) {
    Image(
        painter = painterResource(res),
        contentDescription = null,
        modifier = Modifier
            .size(48.dp)
            .absoluteOffset(x = offsetX, y = offsetY)
    )
}

@Composable
fun TechCarousel() {
    val techIcons = listOf(
        Res.drawable.kotlin,
        Res.drawable.java,
        Res.drawable.android,
        Res.drawable.apple,
        Res.drawable.git,
        Res.drawable.gitHub,

        )
    val infiniteScroll = rememberInfiniteTransition()
    val offsetX by infiniteScroll.animateFloat(
        initialValue = 0f,
        targetValue = -600f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 15000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clipToBounds()
    ) {
        Row(
            modifier = Modifier.offset(x = offsetX.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            (techIcons + techIcons).forEach { icon ->
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(32.dp)
                )
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
        Text("Mis Skills", color = Color.White, modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            skills.forEach { skill ->
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(skill) {
                    delay(500)
                    visible = true
                }
                AnimatedVisibility(
                    visible = visible,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(durationMillis = 500)
                    )
                ) {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {},
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
        Text("Portfolio", color = Color.White, modifier = Modifier.padding(16.dp))
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
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(project, color = Color.White)
                        Text("Descripción breve del proyecto.", color = Color.Gray)
                    }
                }
            }
        }
    }
}