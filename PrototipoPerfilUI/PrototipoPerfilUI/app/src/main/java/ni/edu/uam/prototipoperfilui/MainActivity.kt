package ni.edu.uam.prototipoperfilui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PlayerProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerProfileScreen() {
    // Variables editables por el jugador
    var estado by remember { mutableStateOf("¡Listo para la ranked!") }
    var clan by remember { mutableStateOf("Los Dragones") }

    // Colores personalizados para simular la temática de un juego
    val backgroundColor = Color(0xFF121212)
    val surfaceColor = Color(0xFF1E1E1E)
    val accentColor = Color(0xFFBB86FC) // Un morado tipo "gaming"
    val textColor = Color.White

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil de Jugador", color = textColor) },
                navigationIcon = {
                    IconButton(onClick = { /* Acción de volver */ }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver", tint = textColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 1. Avatar del Jugador
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(surfaceColor)
                    .border(3.dp, accentColor, CircleShape), // Borde llamativo
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Avatar",
                    modifier = Modifier.size(80.dp),
                    tint = accentColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Gamertag y Nombre Real
            Text(
                text = "ShadowNinja99",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = textColor
            )
            Text(
                text = "juan.perez@correo.com",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Estadísticas del juego (Nivel, Rango, Puntos) usando Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatBox(title = "Nivel", value = "42", surfaceColor, textColor, accentColor)
                StatBox(title = "Rango", value = "Oro II", surfaceColor, textColor, accentColor)
                StatBox(title = "K/D", value = "1.5", surfaceColor, textColor, accentColor)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 4. Campos editables (Estado y Clan)
            OutlinedTextField(
                value = clan,
                onValueChange = { clan = it },
                label = { Text("Clan / Equipo", color = Color.Gray) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    focusedBorderColor = accentColor,
                    unfocusedBorderColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = estado,
                onValueChange = { estado = it },
                label = { Text("Mensaje de estado", color = Color.Gray) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    focusedBorderColor = accentColor,
                    unfocusedBorderColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 5. Botones de acción
            Button(
                onClick = { /* Guardar */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = accentColor)
            ) {
                Text("Guardar Perfil", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// Componente reutilizable para las estadísticas
@Composable
fun StatBox(title: String, value: String, surfaceColor: Color, textColor: Color, accentColor: Color) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(surfaceColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontSize = 12.sp, color = Color.Gray)
        Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = accentColor)
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerProfilePreview() {
    MaterialTheme {
        PlayerProfileScreen()
    }
}