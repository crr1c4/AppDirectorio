package com.example.proyectodirectorio.repository

import com.example.proyectodirectorio.models.Contacto
import com.example.proyectodirectorio.room.ContactoDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContactoRepository @Inject constructor(private val contactoDatabaseDao: ContactoDatabaseDao) {
    suspend fun addContacto(contacto: Contacto) = contactoDatabaseDao.insert(contacto)
    suspend fun updateCrono(contacto: Contacto) = contactoDatabaseDao.update(contacto)
    suspend fun deleteCrono(contacto: Contacto) = contactoDatabaseDao.delete(contacto)
    fun getAllContactos(): Flow<List<Contacto>> = contactoDatabaseDao.getContactos().flowOn(Dispatchers.IO).conflate()
    fun getContactoById(id: Long): Flow<Contacto> = contactoDatabaseDao.getContactoById(id).flowOn(Dispatchers.IO).conflate()
}