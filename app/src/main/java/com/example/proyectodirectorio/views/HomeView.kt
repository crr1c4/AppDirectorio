package com.example.proyectodirectorio.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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
import com.example.proyectodirectorio.components.TarjetaContacto
import com.example.proyectodirectorio.components.TituloPrincipal
import com.example.proyectodirectorio.components.MensajeListaVacia
import com.example.proyectodirectorio.viewModels.ContactoViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.example.proyectodirectorio.components.CampoEntrada
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, contactoVM: ContactoViewModel) {
    val textoBusqueda = contactoVM.textoBusqueda
    val contactosFiltrados = contactoVM.contactosFiltrados
    val contactos by contactoVM.contactosList.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TituloPrincipal(title = "Agendástico") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF212738),
                    titleContentColor = Color(0xFFEDF2EF)
                )
            )
        },
        floatingActionButton = {
            AddContactFab {
                navController.navigate("add")
            }
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {

            CampoEntrada(
                value = textoBusqueda,
                onValueChange = { contactoVM.actualizarTextoBusqueda(it) },
                label = "Busqueda",
                icon = Icons.Default.Search
            )

            if (contactos.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MensajeListaVacia(mensaje = "¡No tienes contactos aun!")
                }
            } else if (contactosFiltrados.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MensajeListaVacia(mensaje = "No hay resultados que coincidan con tu busqueda.")
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(contactosFiltrados, key = { it.id }) { contacto ->
                        val eliminar = SwipeAction(
                            icon = rememberVectorPainter(Icons.Default.Delete),
                            background = Color(0xFFFF6868),
                            onSwipe = {
                                contactoVM.eliminarContacto(contacto)
                            }
                        )

                        SwipeableActionsBox(
                            startActions = listOf(eliminar),
                            swipeThreshold = 160.dp
                        ) {
                            TarjetaContacto(
                                contacto = contacto,
                                onClick = {
                                    navController.navigate("edit/${contacto.id}")
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                }
            }
        }
    }
}
