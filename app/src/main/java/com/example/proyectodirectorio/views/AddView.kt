package com.example.proyectodirectorio.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, viewModel: ContactoViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TituloPrincipal(title = "Agregar Contacto") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF212738), // azul muy claro para top bar
                    titleContentColor = Color(0xFFEDF2EF) // azul oscuro para texto
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { innerPadding ->
        ContentAddContactView(
            it = innerPadding,
            navController = navController,
            contactoViewModel = viewModel,
        )
    }
}

@Composable
fun ContentAddContactView(
    it: PaddingValues,
    navController: NavController,
    contactoViewModel: ContactoViewModel,

) {

    val state = contactoViewModel.state

    Column(
        modifier = Modifier
            .padding(it)
          //  .padding(24.dp)
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Campo de nombre
        CampoEntrada(
            value = state.nombre,
            onValueChange = { contactoViewModel.actualizarNombre(it) },
            label = "Nombre",
            icon = Icons.Default.Person
        )

        // Campo de apellido paterno
        CampoEntrada(
            value = state.apellidoPaterno,
            onValueChange = { contactoViewModel.actualizarApellidoPaterno(it) },
            label = "Apellido Paterno",
            icon = Icons.Default.Person
        )

        // Campo de apellido materno
        CampoEntrada(
            value = state.apellidoMaterno,
            onValueChange = { contactoViewModel.actualizarApellidoMaterno(it) },
            label = "Apellido Materno",
            icon = Icons.Default.Person
        )

        // Campo de número telefónico
        CampoEntrada(
            value = state.numero,
            onValueChange = { contactoViewModel.actualizarNumero(it) },
            label = "Número telefónico",
            icon = Icons.Default.Phone,
            keyboardType = KeyboardType.Number
        )

        // Campo de correo electrónico
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
                contactoViewModel.agregarContacto()
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            enabled = state.formularioValido,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF23CE6B),
                contentColor = Color(0xFFFFFFFF),
                disabledContainerColor = Color(0xFFD4FFD6),
                disabledContentColor = Color(0xFFFFFFFF)
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Actualizar contacto",
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Guardar contacto",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}