package com.example.proyectodirectorio.room

import androidx.room.Database
import com.example.proyectodirectorio.models.Contacto

@Database(entities = [Contacto::class], version = 1, exportSchema = false)
abstract class ContactoDatabase {
    abstract fun contactoDao(): ContactoDatabaseDao
}