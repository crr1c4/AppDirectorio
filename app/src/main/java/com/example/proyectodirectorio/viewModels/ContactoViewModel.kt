package com.example.proyectodirectorio.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectodirectorio.models.Contacto
import com.example.proyectodirectorio.repository.ContactoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactoViewModel @Inject constructor(private val repository: ContactoRepository): ViewModel() {
    private val _listaContactos = MutableStateFlow<List<Contacto>>(emptyList());
    val listaContactos = _listaContactos.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllContactos().collect { item ->
                if (item.isNullOrEmpty()) {
                    _listaContactos.value = emptyList()
                } else {
                    _listaContactos.value = item
                }

            }
        }
    }

    fun addContacto(contacto: Contacto) = viewModelScope.launch { repository.addContacto(contacto) }
    fun updateContacto(contacto: Contacto) = viewModelScope.launch { repository.updateCrono(contacto) }
    fun deleteContacto(contacto: Contacto) = viewModelScope.launch { repository.deleteCrono(contacto) }
}