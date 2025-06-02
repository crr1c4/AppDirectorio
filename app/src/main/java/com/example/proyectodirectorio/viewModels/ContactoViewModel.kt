package com.example.proyectodirectorio.viewModels

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.proyectodirectorio.models.Contacto
import com.example.proyectodirectorio.repository.ContactoRepository
import com.example.proyectodirectorio.state.ContactoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactoViewModel @Inject constructor(
    private val repository: ContactoRepository
) : ViewModel() {

    /* Barra de busqueda */
    var textoBusqueda by mutableStateOf("")

    val contactosFiltrados get(): List<Contacto> {
        return if (textoBusqueda.isBlank()) {
            contactosList.value
        } else {
            contactosList.value.filter {
                it.nombre.contains(textoBusqueda, ignoreCase = true) ||
                        it.apellidoPaterno.contains(textoBusqueda, ignoreCase = true) ||
                        it.apellidoMaterno.contains(textoBusqueda, ignoreCase = true) ||
                        it.numero.contains(textoBusqueda, ignoreCase = true) ||
                        it.correo.contains(textoBusqueda, ignoreCase = true)
            }
        }
    }

    fun actualizarTextoBusqueda(texto: String) {
        textoBusqueda = texto
    }


    /*ABC*/

    var state by mutableStateOf(ContactoState())
        private set

    private val _contactosList = MutableStateFlow<List<Contacto>>(emptyList())
    val contactosList = _contactosList.asStateFlow()

    var contactoJob by mutableStateOf<Job?>(null)
        private set

    init {
        cargarContactos()
    }

    private fun cargarContactos() {
        contactoJob?.cancel()
        contactoJob = viewModelScope.launch(Dispatchers.IO) {
            repository.getAllContactos().collect { contactos ->
                _contactosList.value = contactos ?: emptyList()
            }
        }
    }

    // Actualización de campos
    fun actualizarNombre(nombre: String) {
        state = state.copy(nombre = nombre)
        validarFormulario()
    }

    fun actualizarApellidoPaterno(apellidoPaterno: String) {
        state = state.copy(apellidoPaterno = apellidoPaterno)
        validarFormulario()
    }

    fun actualizarApellidoMaterno(apellidoMaterno: String) {
        state = state.copy(apellidoMaterno = apellidoMaterno)
        validarFormulario()
    }


    fun actualizarNumero(numero: String) {
        state = state.copy(numero = numero)
        validarFormulario()
    }

    fun actualizarCorreo(correo: String) {
        state = state.copy(correo = correo)
        validarFormulario()
    }

    private fun validarFormulario() {
        state = state.copy(
            formularioValido =
                    state.nombre.isNotBlank() &&
                    state.numero.isNotBlank() &&
                    state.apellidoPaterno.isNotBlank() &&
                   // state.apellidoMaterno.isNotBlank() &&
                    state.correo.isNotBlank()
        )
    }

    // Operaciones CRUD
    fun agregarContacto() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addContacto(
                Contacto(
                    nombre = state.nombre,
                    apellidoPaterno = state.apellidoPaterno,
                    apellidoMaterno = state.apellidoMaterno,
                    numero = state.numero,
                    correo = state.correo
                )
            )
            resetearFormulario()
        }
    }

    fun cargarContactoParaEdicion(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getContactoById(id).collect { contacto ->
                contacto.let {
                    state = state.copy(
                        nombre = it.nombre,
                        apellidoPaterno = it.apellidoPaterno,
                        apellidoMaterno = it.apellidoMaterno,
                        numero = it.numero,
                        correo = it.correo,
                        edicionActiva = true,
                        contactoSeleccionado = it.id
                    )
                }
            }
        }
    }

    fun actualizarContacto() {
        state.contactoSeleccionado?.let { id ->
            viewModelScope.launch(Dispatchers.IO) {
                repository.updateContacto(
                    Contacto(
                        id = id,
                        nombre = state.nombre,
                        apellidoPaterno = state.apellidoPaterno,
                        apellidoMaterno = state.apellidoMaterno,
                        numero = state.numero,
                        correo = state.correo
                    )
                )
                resetearFormulario()
            }
        }
    }

    fun eliminarContacto(contacto: Contacto) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContacto(contacto)
        }
    }

    private fun resetearFormulario() {
        state = ContactoState()
    }

    fun cancelarEdicion() {
        resetearFormulario()
    }
}