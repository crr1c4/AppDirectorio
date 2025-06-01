package com.example.proyectodirectorio.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectodirectorio.components.AddContactFab
import com.example.proyectodirectorio.components.ContactoCard
import com.example.proyectodirectorio.components.TituloPrincipal
import com.example.proyectodirectorio.components.MensajeListaVacia
import com.example.proyectodirectorio.viewModels.ContactoViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, contactoVM: ContactoViewModel) {
    val contactos by contactoVM.contactosList.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TituloPrincipal(title = "Agendástico") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF212738), // azul muy claro para top bar
                    titleContentColor = Color(0xFFEDF2EF) // azul oscuro para texto
                )
            )
        },
        floatingActionButton = {
            AddContactFab(
                onClick =  {
                    navController.navigate("add")
                }
            )
        },
        containerColor = Color.White // fondo blanco general
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                // .padding(horizontal = 12.dp, vertical = 8.dp)
                .background(Color(0xFFFFFFFF))
        ) {
            if (contactos.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MensajeListaVacia()
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(contactos, key = { it.id }) { contacto ->
                        ContactoCard(
                            contacto = contacto,
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = {
                                navController.navigate("EditContact/${contacto.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}