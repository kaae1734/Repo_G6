package ni.edu.uam.michigranja

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Configuración de Colores Pastel (Estilo Cozy) ---
val MintGreen = Color(0xFFB2F2BB)
val CreamBackground = Color(0xFFFFF9DB)
val SoftOrange = Color(0xFFFFD8A8)
val PastelBlue = Color(0xFFA5D8FF)
val DeepMint = Color(0xFF63E6BE)

// --- Modelo de Datos ---
data class MichiTask(
    val id: Int,
    val title: String,
    val description: String,
    val progress: Float,
    val progressText: String,
    val isCompleted: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MichiTasksScreen(onBackClick: () -> Unit = {}) {
    // Datos de ejemplo
    val tasks = listOf(
        MichiTask(1, "Cosechar 10 Zanahorias", "Las zanahorias frescas son las favoritas.", 0.4f, "4/10", false),
        MichiTask(2, "Pescar 3 Truchas", "¡Michi tiene hambre de pescado!", 1.0f, "3/3", true),
        MichiTask(3, "Regar los Girasoles", "No olvides hidratar el jardín.", 0.2f, "1/5", false),
        MichiTask(4, "Limpiar el Establo", "Un hogar limpio es un gato feliz.", 0.0f, "0/1", false),
        MichiTask(5, "Saludar a un Amigo", "La amistad es lo más importante.", 1.0f, "1/1", true)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Michi-Tareas", 
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5C5C5C)
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MintGreen
                )
            )
        },
        containerColor = CreamBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Encabezado de la Pantalla
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Text(
                    text = "Tareas de Hoy",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF4A4A4A)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Usamos un emoji o un icono alternativo ya que Pets requiere material-icons-extended
                Text("🐾", fontSize = 32.sp)
            }

            // Lista de Misiones
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(tasks) { task ->
                    MichiTaskCard(task)
                }
            }
        }
    }
}

@Composable
fun MichiTaskCard(task: MichiTask) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(SoftOrange.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Recompensa",
                    tint = SoftOrange,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF4A4A4A)
                )
                Text(
                    text = task.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 18.sp
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LinearProgressIndicator(
                        progress = { task.progress },
                        modifier = Modifier
                            .weight(1f)
                            .height(10.dp)
                            .clip(CircleShape),
                        color = DeepMint,
                        trackColor = MintGreen.copy(alpha = 0.3f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = task.progressText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepMint
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = { /* Acción de reclamar */ },
                enabled = task.isCompleted,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PastelBlue,
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFE0E0E0),
                    disabledContentColor = Color.Gray
                ),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = if (task.isCompleted) "Reclamar" else "En progreso",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MichiTasksScreenPreview() {
    MaterialTheme {
        MichiTasksScreen()
    }
}
