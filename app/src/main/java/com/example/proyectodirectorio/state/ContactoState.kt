package com.example.proyectodirectorio.state

data class ContactoState(
    val nombre: String = "",
    val apellidoPaterno: String = "",
    val apellidoMaterno: String = "",
    val correo: String = "",
    val numero: String = "",
    val formularioValido: Boolean = false,
    val edicionActiva: Boolean = false,
    val contactoSeleccionado: Long? = null
)