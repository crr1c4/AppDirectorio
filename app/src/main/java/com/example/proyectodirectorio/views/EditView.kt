package com.example.proyectodirectorio.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectodirectorio.components.CampoEntrada
import com.example.proyectodirectorio.components.MainIconButton
import com.example.proyectodirectorio.components.TituloPrincipal
import com.example.proyectodirectorio.viewModels.ContactoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(navController: NavController, viewModel: ContactoViewModel, id: Long) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TituloPrincipal(title = "Editar Contacto") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF212738),
                    titleContentColor = Color(0xFFEDF2EF)
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { innerPadding ->
        ContentEditContactView(
            it = innerPadding,
            navController = navController,
            contactoViewModel = viewModel,
            id = id
        )
    }
}

@Composable
fun ContentEditContactView(
    it: PaddingValues,
    navController: NavController,
    contactoViewModel: ContactoViewModel,
    id: Long
) {
    val state = contactoViewModel.state

    LaunchedEffect(key1 = id) {
        contactoViewModel.cargarContactoParaEdicion(id);
    }

    Column(
        modifier = Modifier
            .padding(it)
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CampoEntrada(
            value = state.nombre,
            onValueChange = { contactoViewModel.actualizarNombre(it) },
            label = "Nombre",
            icon = Icons.Default.Person
        )

        CampoEntrada(
            value = state.apellidoPaterno,
            onValueChange = { contactoViewModel.actualizarApellidoPaterno(it) },
            label = "Apellido Paterno",
            icon = Icons.Default.Person
        )

        CampoEntrada(
            value = state.apellidoMaterno,
            onValueChange = { contactoViewModel.actualizarApellidoMaterno(it) },
            label = "Apellido Materno",
            icon = Icons.Default.Person
        )

        CampoEntrada(
            value = state.numero,
            onValueChange = { contactoViewModel.actualizarNumero(it) },
            label = "Número telefónico",
            icon = Icons.Default.Phone,
            keyboardType = KeyboardType.Number
        )

        CampoEntrada(
            value = state.correo,
            onValueChange = { contactoViewModel.actualizarCorreo(it) },
            label = "Correo electrónico",
            icon = Icons.Default.Email,
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                contactoViewModel.actualizarContacto()
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            enabled = state.formularioValido,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF23CE6B),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFFD4FFD6),
                disabledContentColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Actualizar contacto",
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Actualizar contacto",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
