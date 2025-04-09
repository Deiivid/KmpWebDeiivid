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
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun App() {
    MaterialTheme(colors = darkColors()) {
        val DarkGrayBackground = Color(0xFF0F111A) // gris azulado oscuro elegante
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkGrayBackground)
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
fun RotatingTechIcons(centerX: Dp, centerY: Dp, radius: Dp, icons: List<DrawableResource>) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    icons.forEachIndexed { index, icon ->
        val angleOffset = angle + (360f / icons.size) * index
        val rad = angleOffset * (PI / 180)
        val x = (centerX.value + radius.value * cos(rad)).dp
        val y = (centerY.value + radius.value * sin(rad)).dp

        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .absoluteOffset(x = x, y = y)
        )
    }
}
@Composable
fun HeroSection() {
    val radius = 100.dp

    val techIcons = listOf(
        Res.drawable.kotlin,
        Res.drawable.git,
        Res.drawable.github,
        Res.drawable.android,
        Res.drawable.java,
        Res.drawable.apple
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.padding(end = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Hello 👋 I'm",
                color = Color.White,
                style = MaterialTheme.typography.h5
            )
            Text(
                "David Navarro",
                color = Color.White,
                style = MaterialTheme.typography.h3
            )
            Text(
                "Mobile Developer (Android | Compose | KMP)",
                color = Color.Gray
            )
        }

        Box(
            modifier = Modifier
                .size(620.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.yo),
                contentDescription = "David Navarro",
                modifier = Modifier.size(560.dp)
            )
            RotatingTechIcons(
                centerX = 180.dp,
                centerY = 180.dp,
                radius = 240.dp,
                icons = techIcons
            )
        }
    }
}
@Composable
fun TechIcon(res: DrawableResource, offsetX: Dp, offsetY: Dp) {
    val infiniteTransition = rememberInfiniteTransition()
    val floatY by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(res),
        contentDescription = null,
        modifier = Modifier
            .size(48.dp)
            .absoluteOffset(x = offsetX, y = offsetY + floatY.dp)
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
        Res.drawable.github,
        Res.drawable.compose,
        Res.drawable.kmp,
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