package com.example.proyectodirectorio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectodirectorio.models.Contacto
import com.example.proyectodirectorio.viewModels.ContactoViewModel

@Composable
fun TituloPrincipal(title: String, color: Color = Color(0xFFD4FFD6)) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.W300,
        color = color, // Aquí puedes cambiar el color
        modifier = Modifier.padding(vertical = 14.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoEntrada(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        maxLines = 1,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = icon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = Color(0xFF23CE6B)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 6.dp),

        shape = RoundedCornerShape(12.dp),

        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = Color(0xFF212738),
            focusedTextColor = Color(0xFF212738),
            focusedBorderColor = Color(0xFF23CE6B),
            unfocusedBorderColor = Color(0xFF6A8D92),
            cursorColor = Color(0xFF212738),
            focusedLabelColor = Color(0xFF23CE6B),
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}


@Composable
fun TarjetaContacto(
    contacto: Contacto,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = Color(0xFF23CE6B),
            modifier = Modifier
                .size(48.dp)
                .padding(end = 12.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${contacto.nombre} ${contacto.apellidoPaterno} ${contacto.apellidoMaterno}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF212738)
            )
            Text(
                text = contacto.numero,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF212738)
            )
            Text(
                text = contacto.correo,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF212738)
            )
        }
    }
}

@Composable
fun MensajeListaVacia(
    modifier: Modifier = Modifier,
    mensaje: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(
                    color = Color(0xFFD4FFD6),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Sin contactos",
                tint = Color(0xFF23CE6B),
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = mensaje,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W200,
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color(0xFF212738),
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = "Presiona el botón para agregar uno nuevo",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFF6A8D92)
            ),
            modifier = Modifier.padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}